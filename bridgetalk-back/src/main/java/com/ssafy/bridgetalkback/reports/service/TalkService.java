package com.ssafy.bridgetalkback.reports.service;

import com.ssafy.bridgetalkback.chatgpt.config.ChatGptRequestCode;
import com.ssafy.bridgetalkback.chatgpt.service.ChatGptService;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.kids.service.KidsFindService;
import com.ssafy.bridgetalkback.reports.exception.ReportsErrorCode;
import com.ssafy.bridgetalkback.tts.service.TtsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TalkService {
    private final KidsFindService kidsFindService;
    private final TtsService ttsService;
    private final ChatGptService chatGptService;
    private final StringRedisTemplate stringRedisTemplate;
    private final ReportsService reportsService;
    private final String[] stopComment = {
            "이야기해서 너무 좋았어! 나는 이만 가볼게! 오늘도 좋은 하루 보내",
            "오늘 이야기도 너무 즐거웠어! 다음에 또 보자!",
            "나랑 같이 추억을 쌓아줘서 고마워! 다음에 또 만날 때까지 건강하게 지내",
            "너와 함께할 수 있어서 즐거웠어. 다음에 또 만나자",
            "벌써 마칠 시간이네. 언제 어디서든 너를 응원할 게! 다음에 또 만나"
    };
    private final String[] startComment = {
            "안녕, 반가워!",
            "안녕, 무슨일이야? ",
            "안녕, 언제 봐도 반가워",
            "안녕, 너랑 이야기할 게 기대돼",
            "안녕, 기다리고 있었어!"

    };

    @Transactional
    public Resource stopTalk(UUID userId) {
        log.info("{ TalkService } : 대화 그만하기 진입");

        Kids kids = kidsFindService.findKidsByUuidAndIsDeleted(userId);
        String endGreetingText = kids.getKidsNickname() + ", " + stopComment[randomIdx()];
        log.info("{ TalkService } : 대화 종료 text - " + endGreetingText);

        Resource endGreeting = ttsService.textToSpeech(endGreetingText);
        log.info("{ TalkService } : endGreeting - " + endGreeting.toString());
        return endGreeting;
    }
    @Transactional
    public MultiValueMap<String, Object> stopTalkMultipart(UUID userId) {
        log.info("{ TalkService } : 대화 그만하기 진입 - multipart");

        Kids kids = kidsFindService.findKidsByUuidAndIsDeleted(userId);
        String endGreetingText = kids.getKidsNickname() + ", " + stopComment[randomIdx()];
        log.info("{ TalkService } : 대화 종료 text - " + endGreetingText);

        Resource endGreeting = ttsService.textToSpeech(endGreetingText);
        log.info("{ TalkService } : endGreeting - " + endGreeting.toString());
        return createMultpartResponse(endGreetingText, endGreeting);
    }

    @Transactional
    public Resource startTalk(UUID userId) {
        log.info("{ TalkService } : 대화 시작하기 진입");
        Kids kids = kidsFindService.findKidsByUuidAndIsDeleted(userId);
        String startGreetingText = kids.getKidsNickname() + ", " + startComment[randomIdx()];
        log.info("{ TalkService } : 대화 시작 text - " + startGreetingText);
        Resource startGreeting = ttsService.textToSpeech(startGreetingText);
        log.info("{ TalkService } : startGreeting - " + startGreeting.toString());

        return startGreeting;

    }

    @Transactional
    public MultiValueMap<String, Object> startTalkByMultiPart(UUID userId) {
        log.info("{ TalkService } : 대화 시작하기 진입 - multipart");
        Kids kids = kidsFindService.findKidsByUuidAndIsDeleted(userId);
        String startGreetingText = kids.getKidsNickname() + ", " + startComment[randomIdx()];
        log.info("{ TalkService } : 대화 시작 text - " + startGreetingText);
        Resource startGreeting = ttsService.textToSpeech(startGreetingText);
        log.info(">> startGreeting - " + startGreeting.toString());

        return createMultpartResponse(startGreetingText, startGreeting);
    }

    @Transactional
    public Resource sendTalk(UUID userId, String talkText) {
        log.info("{ TalkService } : 대화 하기 (답장) 진입");

        Kids kids = kidsFindService.findKidsByUuidAndIsDeleted(userId);
        String userEmail = kids.getKidsEmail();
        updateTalkText(userEmail, talkText);
        String answer = createAnswer(talkText);
        log.info("{ TalkService } : 아이 음성 텍스트에 대한 답변 - " + answer);

        Resource sendTalk = ttsService.textToSpeech(answer);
        log.info("{ TalkService } : sendTalk - " + sendTalk.toString());
        return sendTalk;
    }

    @Transactional
    public MultiValueMap<String, Object> sendTalkByMultiPart(UUID userId, String talkText) {
        log.info("{ TalkService } : 대화 하기 (답장) 진입 - multipart");

        Kids kids = kidsFindService.findKidsByUuidAndIsDeleted(userId);
        String userEmail = kids.getKidsEmail();
        updateTalkText(userEmail, talkText);
        String answer = createAnswer(talkText);
        log.info("{ TalkService } : 아이 음성 텍스트에 대한 답변 - " + answer);

        Resource sendTalk = ttsService.textToSpeech(answer);
        log.info("{ TalkService } : sendTalk - " + sendTalk.toString());

        return createMultpartResponse(answer, sendTalk);
    }

    // 0 ~ 4
    private int randomIdx() {
        return (int) (Math.random() * 5);
    }

    public String createAnswer(String talkText) {
        log.info("{ TalkService.createAnswer }");
        String transformedText = "";
        if (talkText.isEmpty()) {
            log.error("!! 아이 음성 텍스트가 비어었습니다.");
            throw BaseException.type(ReportsErrorCode.CHATGPT_EMPTY_TEXT);
        }
        transformedText = chatGptService.createPrompt(talkText, ChatGptRequestCode.ANSWER);
        log.info(">> transformedText : {}", transformedText);

        return transformedText;
    }

    // RedisTemplate 적용

    // update
    public void updateTalkText(String userEmail, String newText) {
        String value = stringRedisTemplate.opsForValue().get(userEmail);
        if (value == null) {
            throw BaseException.type(ReportsErrorCode.TALK_NOT_FOUD);
        } else {
            stringRedisTemplate.opsForValue().append(userEmail, "\n" + newText);
        }
    }

    public void createTalk(UUID userId) {
        log.info("{TalkService} : 대화 임시 저장 진입");
        Kids kids = kidsFindService.findKidsByUuidAndIsDeleted(userId);
        String userEmail = kids.getKidsEmail();
        String value = stringRedisTemplate.opsForValue().get(userEmail);
        if (value == null) {
            stringRedisTemplate.opsForValue().set(userEmail, " ", 310);
        } else {
            log.info("{TalkService} : 진행중인 대화가 있습니다.");
            throw BaseException.type(ReportsErrorCode.TALK_DUPLICATED);
        }

    }

    /**
     * createMultipartResponse() : 감정 생성 및 multipart response 생성
     * @param text : 자막
     * @param resource : tts 음성 파일
     * @return : MultiPartForm Response Data
     */
    private MultiValueMap<String, Object> createMultpartResponse(String text, Resource resource){
        String emotion = chatGptService.createPrompt(text, ChatGptRequestCode.EMOTION);
        log.info(">> extractEmotion : {}", emotion);

        MultiValueMap<String, Object> responseParts = new LinkedMultiValueMap<>();
        responseParts.add("subtitles", text);
        responseParts.add("emotion", emotion);
        responseParts.add("audio", resource);
        return responseParts;
    }

}
