package com.ssafy.bridgetalkback.letters.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.bridgetalkback.chatgpt.config.ChatGptRequestCode;
import com.ssafy.bridgetalkback.chatgpt.service.ChatGptService;
import com.ssafy.bridgetalkback.files.service.S3FileService;
import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.global.exception.GlobalErrorCode;
import com.ssafy.bridgetalkback.kids.service.KidsFindService;
import com.ssafy.bridgetalkback.letters.domain.Letters;
import com.ssafy.bridgetalkback.letters.domain.LettersImg;
import com.ssafy.bridgetalkback.letters.dto.response.LettersImgResponseDto;
import com.ssafy.bridgetalkback.letters.dto.response.LettersResponseDto;
import com.ssafy.bridgetalkback.letters.dto.response.TranscriptionDto;
import com.ssafy.bridgetalkback.letters.exception.LettersErrorCode;
import com.ssafy.bridgetalkback.letters.repository.LettersImgRepository;
import com.ssafy.bridgetalkback.letters.repository.LettersRepository;
import com.ssafy.bridgetalkback.notification.domain.NotificationType;
import com.ssafy.bridgetalkback.notification.dto.request.NotificationRequestDto;
import com.ssafy.bridgetalkback.notification.service.SseService;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.parents.service.ParentsFindService;
import com.ssafy.bridgetalkback.reports.domain.Reports;
import com.ssafy.bridgetalkback.reports.service.ReportsService;
import com.ssafy.bridgetalkback.translation.service.TranslationService;
import com.ssafy.bridgetalkback.tts.service.TtsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class LettersService {

    private final S3FileService s3FileService;
    private final ChatGptService chatGptService;
    private final LettersTranscribeService lettersTranscribeService;
    private final AmazonS3 s3Client;
    private final ObjectMapper objectMapper;
    private final LettersRepository lettersRepository;
    private final TtsService ttsService;
    private final ReportsService reportsService;
    private final ParentsFindService parentsFindService;
    private final KidsFindService kidsFindService;
    private final LettersImgRepository lettersImgRepository;
    private final SseService sseService;
    private final TranslationService translationService;

    @Value("${S3_BUCKET_NAME}")
    private String bucketName;

    @Value("${PAPAGO_CLIENTID}")
    private String clientId;

    @Value("${PAPAGO_CLIENT_SECRET}")
    private String clientSecret;


    /**
     * saveVoiceFile() : s3에 음성파일 저장 메서드
     *
     * @param lettersFile : 입력된 음성 파일
     * @return String : 저장된 s3 url
     */
    public String saveVoiceFile(MultipartFile lettersFile) {
        log.info("{ LetterService.saveVoiceFile() } : 부모 음성 편지 s3업로드 메서드");
        return s3FileService.uploadLettersFiles(lettersFile);
    }

    /**
     * createText() : 음성파일 텍스트화 메서드
     *
     * @param voiceUrl      : 입력된 음성 파일
     * @param parentsUserId : 사용자 userId(UUID)
     * @return LettersResponseDTO : 변환된 텍스트 responseDTO
     */
    public LettersResponseDto createText(String voiceUrl, String parentsUserId, Long reportsId) {
        log.info("{ LetterService.createText() } : 텍스트화 메서드");
        String[] vrr = voiceUrl.split("/");
        System.out.println(Arrays.toString(vrr));
        int len = vrr.length;
        String fileName = vrr[len - 2] + "/" + vrr[len - 1];
        log.info(">> fileName : {}", fileName);

        // db에 저장
        Parents parents = parentsFindService.findParentsByUuidAndIsDeleted(UUID.fromString(parentsUserId));

        // stt api 호출
        String extractOriginText = stt(fileName, parents.getLanguage());

//        // 번역 api 호출
//        //      1. 베트남어(vi) -> 영어(en)
//        String extractTranslationTextIntoEn = translation(extractOriginText, "vi", "en");
//        //      2. 영어(en) -> 한국어(ko)
//        String extractTranslationTextIntoKo = translation(extractOriginText, "en", "ko");

        // chatgpt api 호출 => 번역 & 대화체로 수정
        String transformedText = changeToConversation(extractOriginText);

        Reports reports = reportsService.findByIdAndIsDeleted(reportsId);
        if (lettersRepository.findByReports(reports).isPresent()) {
            throw BaseException.type(LettersErrorCode.LETTERS_DUPLICATED);
        }
        Letters newLetter = Letters.createLetters(parents, reports, extractOriginText, transformedText);

        lettersRepository.save(newLetter);

        log.info(">>>> (아이에게) SSE 알림 전송 시작");
        NotificationRequestDto notificationRequestDto = NotificationRequestDto.builder()
                .receiverUuid(reports.getKids().getUuid().toString())
                .url("https://bridgetalk.co.kr/api/letters/"+newLetter.getLettersId())
                .content(NotificationType.PARENT_LETTERS_REGISTER.getWord())
                .notificationType(NotificationType.PARENT_LETTERS_REGISTER)
                .build();
        sseService.send(notificationRequestDto);
        log.info(">>>> (아이에게) SSE 알림 전송 완료");

        return LettersResponseDto.of(newLetter);
    }

    /**
     * stt() : 음성파일 텍스트화 api 호출 메서드
     *
     * @param fileName : 파일명
     * @param language : 국가
     * @return String : 변환된 텍스트
     */
    public String stt(String fileName, Language language) {
        log.info("{ LetterService.stt() } : stt api 호출 메서드");
        String jobName = lettersTranscribeService.transcribe(bucketName, fileName, language);
        String transcriptFileName = jobName + ".json";
        log.info(">> trancriptionFileName : {}", transcriptFileName);
        String extractText = "";
        try {
            S3Object s3Object = s3Client.getObject(bucketName, transcriptFileName);
            log.info(">> s3Object : {}", s3Object);
            S3ObjectInputStream objectContent = s3Object.getObjectContent();
            // Transcript json -> DTO
            TranscriptionDto jsonData = objectMapper.readValue(objectContent, TranscriptionDto.class);
            log.info(">> jsonToObject : {}", jsonData);
            Map<String, Object> results = jsonData.results();
            List<Map<String, String>> transcriptList = (List<Map<String, String>>) results.get("transcripts");
            extractText = transcriptList.get(0).get("transcript");
            log.info(">> extractText : {}", extractText);

            // 스트림 및 객체 닫기
            objectContent.close();
            s3Object.close();
        } catch (NotFoundException ne) {
            log.error(ne.getMessage());
            throw BaseException.type(LettersErrorCode.LETTERS_NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw BaseException.type(GlobalErrorCode.INTERNAL_SERVER_ERROR);
        }

        return extractText;
    }


    /**
     * changeToConversation() : chatgpt api를 호출하여 대화체로 변환하여 저장
     *
     * @param orginalText : 원본 텍스트
     * @return transformedText : 변환된 텍스트
     */
    public String changeToConversation(String orginalText) {
        log.info("{ LettersService.changeToConversation }");
        String transformedText = "";
        if (orginalText.isEmpty()) {
            log.error("!! 변환할 원본 텍스트가 비어었습니다.");
            throw BaseException.type(LettersErrorCode.CHATGPT_EMPTY_TEXT);
        }
//        String engText = chatGptService.createPrompt(orginalText, ChatGptRequestCode.TRANSLATE_ENG);
//        transformedText = chatGptService.createPrompt(engText, ChatGptRequestCode.CONVERSION);
        String translationText = translationService.translation(orginalText, "vi", "ko");
        log.info(">> translationText : {}", translationText);
        transformedText = chatGptService.createPrompt(translationText, ChatGptRequestCode.STT_TRANSLATION);
        log.info(">> transformedText : {}", transformedText);

        return transformedText;
    }


    /**
     * pk로 편지 정보 조회 후, 데이터 삭제여부까지 확인하는 메서드
     *
     * @param lettersId
     * @return pk에 해당하는 Letters 객체
     */
    public Letters findById(Long lettersId) {
        log.info("{LettersService} : Id(Pk)로 편지 정보 조회");
        return lettersRepository.findByLettersIdAndIsDeleted(lettersId, 0)
                .orElseThrow(() -> BaseException.type(LettersErrorCode.LETTERS_NOT_FOUND));
    }

    /**
     * pk에 해당하는 편지의 번역된 한국어 text를 음성데이터로 반환하는 메서드
     *
     * @param lettersId
     * @return 번역문의 음성데이터
     */
    public Resource findLettersVoice(Long lettersId) {
        Letters letter = findById(lettersId);
        letter.updateIsChecked();
        String inputText = letter.getLettersTranslationContent();
        return ttsService.textToSpeech(inputText);
    }

    public void deleteLetters(Long lettersId) {
        Letters letters = findById(lettersId);
        letters.updateIsDeleted();
    }

    public LettersResponseDto findLettersText(Long lettersId) {
        log.info("{ LetterService.findLettersText() } : 편지 텍스트 조회 메서드");
        Letters letters = findById(lettersId);
        return LettersResponseDto.of(letters);
    }

    /**
     * 편지 키워드에 img가져오는 메소드
     *
     * @param lettersId
     * @return keyword, imgUrl list 반환
     */

    public List<LettersImgResponseDto> findLettersImg(Long lettersId) {
        log.info("{LettersService.findLetterImg() : 편지 키워드 이미지 메서드");
        Letters letters = findById(lettersId);
        // keyword 추출
        String keywords = chatGptService.convertKeywordImg(letters.getLettersTranslationContent());
        String processed = keywords.replaceAll("[\\[\\]\"]", ""); // 대괄호와 따옴표 제거
        String[] keyword_arr = processed.split(", ");
        System.out.println(Arrays.toString(keyword_arr));
        List<LettersImgResponseDto> list = new ArrayList<>();
        for (String s : keyword_arr) {
            System.out.println(s);
            LettersImg lettersImgByKeyword = findLettersImgByKeyword(s);

            LettersImgResponseDto lettersImgResponseDto = LettersImgResponseDto.of(lettersImgByKeyword);
            list.add(lettersImgResponseDto);
        }
        return list;
    }

    public LettersImg findLettersImgByKeyword(String keyword) {
        log.info("{LettersService} : keyword에 해당하는 삭제되지 않은 이미지 url 반환 서비스 진입");

        return lettersImgRepository.findLettersImgByKeywordAndIsDeleted(keyword, 0)
                .orElseGet(() -> {
                    return lettersImgRepository.findLettersImgByKeywordAndIsDeleted("사랑", 0)
                            .orElseThrow(() -> BaseException.type(LettersErrorCode.LETTERS_IMG_NOT_FOUND));
                });

    }

    public List<LettersResponseDto> findAllKidsLetters(UUID kidsUuid) {
        log.info("{LettersService} :Kids에 해당하는 모든 편지의 번역본, id, 등록날짜 반환 서비스 진입");
        List<Letters> lettersByKidsList = findAllByKids(kidsUuid);
        List<LettersResponseDto> lettersTextList = lettersByKidsList.stream().map(letters -> LettersResponseDto.of(letters)).collect(Collectors.toList());
        return lettersTextList;
    }

    public List<Letters> findAllByKids(UUID kidsUuid) {
        log.info("{LettersService} :Kids에 해당하는 삭제되지 않은 모든 편지 조회 서비스 진입");
        kidsFindService.findKidsByUuidAndIsDeleted(kidsUuid);
        List<Letters> lettersByKidsList = lettersRepository.findAllByReportsKidsUuid(kidsUuid);
        return lettersByKidsList;
    }


}
