package com.ssafy.bridgetalkback.chatgpt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.bridgetalkback.chatgpt.config.ChatGptConfig;
import com.ssafy.bridgetalkback.chatgpt.config.ChatGptRequestCode;
import com.ssafy.bridgetalkback.chatgpt.dto.request.ChatGptRequestDto;
import com.ssafy.bridgetalkback.chatgpt.dto.response.Choice;
import com.ssafy.bridgetalkback.chatgpt.exception.ChatGptErrorCode;
import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.translation.service.TranslationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatGptService {

    private final ChatGptConfig chatGptConfig;
    private final TranslationService translationService;


    @Value("${OPENAI_URL}")
    private String legacyPromptUrl;

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String[]> createSummary(String originText) {
        String[] summary = new String[3];
        summary[0] = createPrompt(originText, ChatGptRequestCode.SUMMARY);
        summary[1] = translationService.translation(summary[0], "ko", "vi");
        String engText = translationService.translation(summary[0], "ko", "en");
        summary[2] = createPrompt(engText, ChatGptRequestCode.TRANSLATE_PH);
        return CompletableFuture.completedFuture(summary);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> createSummaryKor(String originText) {
        String summaryKor = createPrompt(originText, ChatGptRequestCode.SUMMARY);
        return CompletableFuture.completedFuture(summaryKor);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> createSummaryViet(String summary) {
        String summaryViet = translationService.translation(summary, "ko", "vi");
        return CompletableFuture.completedFuture(summaryViet);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> createSummaryPh(String summary) {
        String engText = translationService.translation(summary, "ko", "en");
        String summaryPh = createPrompt(engText, ChatGptRequestCode.TRANSLATE_PH);
        return CompletableFuture.completedFuture(summaryPh);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String[]> createKeywords(String originText) {
        String[] keywords = new String[3];
        keywords[0] = createPrompt(originText, ChatGptRequestCode.KEYWORD);
        keywords[1] = translationService.translation(keywords[0], "ko", "vi");
        keywords[2] = createPrompt(keywords[0], ChatGptRequestCode.TRANSLATE_PH_VER1);
        return CompletableFuture.completedFuture(keywords);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> createKeywordsKor(String originText) {
        String keywordsKor = createPrompt(originText, ChatGptRequestCode.KEYWORD);
        return CompletableFuture.completedFuture(keywordsKor);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> createKeywordsViet(String keywords) {
        String keywordsViet = translationService.translation(keywords, "ko", "vi");
        return CompletableFuture.completedFuture(keywordsViet);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> createKeywordsPh(String keywords) {
        String keywordsPh = createPrompt(keywords, ChatGptRequestCode.TRANSLATE_PH_VER1);
        return CompletableFuture.completedFuture(keywordsPh);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String[]> createSolution(String originText) {
        String[] solution = new String[3];
        solution[0] = createPrompt(originText, ChatGptRequestCode.SOLUTION);
        solution[1] = translationService.translation(solution[0], "ko", "vi");
        String engText = translationService.translation(solution[0], "ko", "en");
        solution[2] = createPrompt(engText, ChatGptRequestCode.TRANSLATE_PH);
        return CompletableFuture.completedFuture(solution);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> createSolutionKor(String originText) {
        String solutionKor = createPrompt(originText, ChatGptRequestCode.SOLUTION);
        return CompletableFuture.completedFuture(solutionKor);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> createSolutionViet(String solution) {
        String solutionViet = translationService.translation(solution, "ko", "vi");
        return CompletableFuture.completedFuture(solutionViet);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> createSolutionPh(String solution) {
        String engText = translationService.translation(solution, "ko", "en");
        String solutionPh = createPrompt(engText, ChatGptRequestCode.TRANSLATE_PH);
        return CompletableFuture.completedFuture(solutionPh);
    }


    // 비동기
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String[]> createLettersAsyncKeyword(String originText) {
        String[] keywords = new String[1];
        keywords[0] = createPrompt(originText, ChatGptRequestCode.LETTERS_KEYWORD);
        return CompletableFuture.completedFuture(keywords);
    }

    // 동기
    public String createLettersKeyword(String originText) {
        return createPrompt(originText, ChatGptRequestCode.LETTERS_KEYWORD);
    }
    public String convertKeywordImg(String originText) {
        return createPrompt(originText, ChatGptRequestCode.CONVERT_KEYWORD);

    }

    public String createPrompt(String originText, ChatGptRequestCode gptRequestCode) {
        log.info("{ ChatGptService } : {} 진입", gptRequestCode);
        ChatGptRequestDto chatGptRequestDto = ChatGptRequestDto.builder()
                .prompt(createText(originText, gptRequestCode))
                .build();

        HttpHeaders headers = chatGptConfig.httpHeaders();
        HttpEntity<ChatGptRequestDto> requestEntity = new HttpEntity<>(chatGptRequestDto, headers);

        Map<String, Object> resultMap = new HashMap<>();
        try {
            ResponseEntity<String> response = chatGptConfig
                    .restTemplate()
                    .exchange(legacyPromptUrl, HttpMethod.POST, requestEntity, String.class);
            ObjectMapper om = new ObjectMapper();
            resultMap = om.readValue(response.getBody(), new TypeReference<>() {
            });
        } catch (RestClientException e) {
            throw BaseException.type(ChatGptErrorCode.CHATGPT_FAILED);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        List<Choice> choices = (List<Choice>) resultMap.get("choices");
        Map<String, Object> textMap = (Map<String, Object>) choices.get(0);
        String result = (String) textMap.get("text");
        // 감정 추출은 trim() 만 실행
        if (gptRequestCode.equals(ChatGptRequestCode.EMOTION)) {
            result = result.trim();
        } else {
            result = result.substring(2);
        }

        log.info("{ ChatGptService } : {} 성공", gptRequestCode);
        log.info(">> result : {}", result);
        return result;
    }

    public String createText(String text, ChatGptRequestCode gptRequestCode) {
        if (gptRequestCode.equals(ChatGptRequestCode.SUMMARY)) {
            text += " 3줄 요약해서 한 줄로 나열해줘";
            log.info(">> prompt : {}", text);
        } else if (gptRequestCode.equals(ChatGptRequestCode.TRANSLATE)) {
            text += " 베트남어로 번역해줘";
            log.info(">> prompt : {}", text);
        } else if (gptRequestCode.equals(ChatGptRequestCode.CONVERSION)) {
            text += "\n 이 영어 문단을 한국어로 번역하고, 부드럽고 친근한 엄마가 아이에게 말하는 것처럼 다듬어줘";
            log.info(">> prompt : {}", text);
        } else if (gptRequestCode.equals(ChatGptRequestCode.KEYWORD)) {
            text += "\n 위 문단의 핵심 키워드 3개 추출해서 키워드만 한 줄로 나열해줘";
            log.info(">> prompt : {}", text);
        } else if (gptRequestCode.equals(ChatGptRequestCode.SOLUTION)) {
            text += "\n 위 문장들에 대해 한국인엄마로서 해줄 수 있는 말로 대답해줘";
            log.info(">> prompt : {}", text);
        } else if (gptRequestCode.equals(ChatGptRequestCode.ANSWER)) {
            text += "\n 위 문장들에 대해 공감하는 표현으로 두 문장으로 이어지게 친구처럼 대답해줘";
            log.info(">> prompt : {}", text);
        } else if (gptRequestCode.equals(ChatGptRequestCode.EMOTION)) {
            text += "\n위 문장에서 긍정, 부정, 슬픔, 행복, 화남 중 키워드만 하나 선택해줘";
        } else if (gptRequestCode.equals(ChatGptRequestCode.PARAGRAPH_TRANSLATE_ENG)) {
            text += "\n 위 문장들을 문단별로 영어로 번역해줘";
            log.info(">> prompt : {}", text);
        } else if (gptRequestCode.equals(ChatGptRequestCode.PARAGRAPH_TRANSLATE_VIET)) {
            text += "\n Translate these sentences into Vietnamese paragraph by paragraph";
            log.info(">> prompt : {}", text);
        } else if (gptRequestCode.equals(ChatGptRequestCode.PARAGRAPH_TRANSLATE_PH)) {
            text += "\n Translate these sentences into Filipino paragraph by paragraph";
            log.info(">> prompt : {}", text);
        } else if(gptRequestCode.equals(ChatGptRequestCode.LETTERS_KEYWORD)){
            text += "\n 위의 문장에서 핵심 단어 4개를 차례로 한줄로 나열해줘";
            log.info(">> prompt : {}", text);
        } else if (gptRequestCode.equals(ChatGptRequestCode.CONVERT_KEYWORD)) {
            text += "\n 위의 문장에서 핵심 단어 4개를 [\"친구\", \"학교\", \"성장\", \"호기심\", \"게임\", \"취미\", \"가족\", \"공원\", \"학습\", \"동기부여\", \"창의력\", \"감정\", \"스포츠\", \"음악\", \"독서\", \"영화\", \"자연\", \"애완동물\", \"모험\", \"과학\", \"행복\",\"놀이공원\",\"롤러코스터\"] 중에서 가장 밀접한 관련이 있는 단어로 바꿔서 설명없이 한 개의 list형태로 반환해줘";
        } else if (gptRequestCode.equals(ChatGptRequestCode.TRANSLATE_PH)) {
            text += "\n Translate the above sentence literally into Filipino.";
            log.info(">> prompt : {}", text);
        } else if (gptRequestCode.equals(ChatGptRequestCode.TRANSLATE_VIET)){
            text += "\n Translate the above sentence literally into Vietnamese.";
            log.info(">> prompt : {}", text);
        } else if (gptRequestCode.equals(ChatGptRequestCode.TRANSLATE_ENG)) {
            text += "\n 위 문장들을 영어로 번역해줘";
            log.info(">> prompt : {}", text);
        } else if(gptRequestCode.equals(ChatGptRequestCode.TRANSLATE_PH_VER1)){
            text += "\n 필리핀어로 번역하고, 한 줄로 나열해줘";
            log.info(">> prompt : {}", text);
        } else if(gptRequestCode.equals(ChatGptRequestCode.STT_TRANSLATION)){
            text += "\n이 문단을 내용 그대로, 부드럽고 친근한 엄마의 어조로 다듬어줘";
            log.info(">> prompt : {}", text);
        }
        return text;
    }
}