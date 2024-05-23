package com.ssafy.bridgetalkback.reports.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.bridgetalkback.files.service.S3FileService;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.global.exception.GlobalErrorCode;
import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.kids.exception.KidsErrorCode;
import com.ssafy.bridgetalkback.kids.repository.KidsRepository;
import com.ssafy.bridgetalkback.kids.service.KidsFindService;
import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.reports.domain.Reports;
import com.ssafy.bridgetalkback.reports.dto.ReportsCreateResponseDto;
import com.ssafy.bridgetalkback.reports.dto.response.ReportsDetailResponseDto;
import com.ssafy.bridgetalkback.reports.dto.response.ReportsListResponseDto;
import com.ssafy.bridgetalkback.reports.dto.response.TranscriptionDto;
import com.ssafy.bridgetalkback.reports.dto.response.VideoResponseDto;
import com.ssafy.bridgetalkback.reports.exception.ReportsErrorCode;
import com.ssafy.bridgetalkback.reports.repository.ReportsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReportsService {

    private final KidsFindService kidsFindService;
    private final ReportsRepository reportsRepository;
    private final ReportsFindService reportsFindService;
    private final KidsRepository kidsRepository;
    private final S3FileService s3FileService;
    private final AmazonS3 s3Client;
    private final ObjectMapper objectMapper;
    private final ReportsTranscribeService reportsTranscribeService;
    private final ReportsVideoService reportsVideoService;
    private final StringRedisTemplate stringRedisTemplate;

    @Value("${S3_BUCKET_NAME}")
    private String bucketName;


    @Transactional
    public ReportsCreateResponseDto createReports(UUID userId) {
        log.info("{ReportsService} :  reports 생성 ");
        Kids kids = kidsFindService.findKidsByUuidAndIsDeleted(userId);
        String userEmail = kids.getKidsEmail();
        // Redis의 대화 내용 가져오기
        String reportsOriginContent = stringRedisTemplate.opsForValue().get(userEmail);
        Reports reports = Reports.createReports(kids, reportsOriginContent);
        reportsRepository.save(reports);
        log.info(">> 새로 생성된 reports origin content: {}", reports.getReportsOriginContent());
        // Redis에서 삭제
        stringRedisTemplate.delete(userEmail);
        return ReportsCreateResponseDto.fromReportsId(reports);
    }

    public Reports findByIdAndIsDeleted(Long reportsId) {
        log.info("{ReportsService} : Id(Pk)로 reports 조회 - " + reportsId);
        return reportsRepository.findByReportsIdAndIsDeleted(reportsId, 0)
                .orElseThrow(() -> BaseException.type(ReportsErrorCode.REPORTS_NOT_FOUND));
    }

    public List<ReportsListResponseDto> reportsList(UUID userId, UUID kidsId, Language language) {
        if (kidsRepository.existsKidsByParentsUuidAndUuidAndIsDeleted(userId, kidsId, 0)) {
            log.info("{ ReportsService } : 부모 프로필의 아이 조회");
        } else throw BaseException.type(KidsErrorCode.KIDS_NOT_FOUND);

        List<Reports> reports = reportsRepository.findAllByKidsUuidAndIsDeleted(kidsId, 0);
        List<ReportsListResponseDto> reportsList = new ArrayList<>();

        for (Reports report : reports) {
            reportsList.add(ReportsListResponseDto.fromReports(report, language));
        }
        log.info("{ ReportsService } : 아이속마음 리스트 조회 성공, lang : {}", language);
        return reportsList;
    }

    public ReportsDetailResponseDto reportsDetail(UUID userId, UUID kidsId, Long reportsId, Language language) {
        if (kidsRepository.existsKidsByParentsUuidAndUuidAndIsDeleted(userId, kidsId, 0)) {
            log.info("{ ReportsService } : 부모 프로필의 아이 조회");
        } else throw BaseException.type(KidsErrorCode.KIDS_NOT_FOUND);

        Reports reports = reportsFindService.findByReportsIdAndIsDeleted(reportsId);
        log.info("{ ReportsService } : 아이속마음 상세 조회 성공, lang : {}", language);
        List<VideoResponseDto> reportsVideoList = new ArrayList<>();
//        reportsVideoList = reportsVideoService.searchVideo(reports.getReportsKeywordsKor());
        log.info("{ ReportsVideoService } : 레포트 유튜브리스트 성공");
        return ReportsDetailResponseDto.fromReports(reports, reportsVideoList, language);
    }

    @Transactional
    public String updateOriginContent(UUID userId, Long reportsId, String talkText) {
        log.info("{ ReportsService } : 아이 대화 원본 update 진입");

        Kids kids = kidsFindService.findKidsByUuidAndIsDeleted(userId);
        Reports reports = reportsFindService.findByReportsIdAndIsDeleted(reportsId);

        String updateReportsContent = reports.getReportsOriginContent() + "\n" + talkText;
        reports.updateReportsOriginContent(updateReportsContent);
        log.info("{ ReportsService } : 아이 대화 원본 update 성공 - " + updateReportsContent);

        return updateReportsContent;
    }

    public String saveReportsFiles(MultipartFile puzzleFile) {
        log.info("{ ReportsService.saveReportsFile() } : 아이 음성 s3업로드 메서드");
        return s3FileService.uploadReportsFiles(puzzleFile);
    }

    public String createText(String fileUrl) {
        log.info("{ ReportsService.createText() } : 텍스트화 메서드");
        String[] vrr = fileUrl.split("/");
        System.out.println(Arrays.toString(vrr));
        int len = vrr.length;
        String fileName = vrr[len - 2] + "/" + vrr[len - 1];
        log.info(">> fileName : {}", fileName);

        String talkText = stt(fileName);
        log.info(">> talkText : {}", talkText);

        s3FileService.deleteFiles(fileUrl);
        log.info("{ ReportsService } : tts 성공 및 원본 음성 파일 삭제 - " + fileUrl);

        return talkText;
    }

    public String stt(String fileName) {
        log.info("{ ReportsService.stt() } : stt api 호출 메서드");
        String jobName = reportsTranscribeService.transcribe(bucketName, fileName);
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
            throw BaseException.type(ReportsErrorCode.REPORTS_NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw BaseException.type(GlobalErrorCode.INTERNAL_SERVER_ERROR);
        }

        return extractText;
    }
}
