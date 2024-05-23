package com.ssafy.bridgetalkback.reports.service;

import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.reports.domain.Reports;
import com.ssafy.bridgetalkback.reports.exception.ReportsErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.MultiValueMap;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;

import static com.ssafy.bridgetalkback.fixture.KidsFixture.*;
import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SUNKYOUNG;
import static com.ssafy.bridgetalkback.fixture.ReportsFixture.REPORTS_01;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Talk [Service Layer] -> TalkService 테스트")
public class TalkServiceTest extends ServiceTest {
    @Autowired
    private TalkService talkService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private Parents parents;
    private Kids kids;
    private HttpURLConnection mockConnection;
    private Reports reports;

    @BeforeEach
    void setup() {
        parents = parentsRepository.save(SUNKYOUNG.toParents());
        kids = kidsRepository.save(JIYEONG.toKids(parents));
        mockConnection = mock(HttpURLConnection.class);
        reports = reportsRepository.save(REPORTS_01.toReports(kids));
        stringRedisTemplate.opsForValue().set(kids.getKidsEmail(), " ", 310);
    }

    @Test
    @DisplayName("대화 종료 멘트 tts 변환에 성공한다")
    void stopTalk() throws IOException {
        // given
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);

        // when
        Resource response = talkService.stopTalk(kids.getUuid());

        // Then
        assertThat(response).isInstanceOf(FileSystemResource.class);
    }

    @Test
    @DisplayName("대화 종료 멘트 tts 변환 및 자막, 감정 생성")
    void stopTalkMultipart() throws IOException {
        // given
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);

        // when
        MultiValueMap<String, Object> response = talkService.stopTalkMultipart(kids.getUuid());

        // Then
        assertThat(response).isInstanceOf(MultiValueMap.class);
    }

    @Test
    @DisplayName("대화 시작 멘트 tts 변환에 성공한다")
    void startCommentTts() throws IOException {
        // given
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);

        // when
        Resource response = talkService.startTalk(kids.getUuid());

        // Then
        assertThat(response).isInstanceOf(FileSystemResource.class);
    }

    @Test
    @DisplayName("대화 시작 멘트 tts 변환 및 자막, 감정 생성")
    void startCommentTtsMultipart() throws IOException {
        // given
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);

        // when
//        Resource response = talkService.startTalk(kids.getUuid());
        MultiValueMap<String, Object> response = talkService.startTalkByMultiPart(kids.getUuid());

        // Then
        assertThat(response).isInstanceOf(MultiValueMap.class);
    }

    @Test
    @DisplayName("chatgpt api 호출 시, 올바른 텍스트 입력 확인")
    void throwExceptionByEmptyFile() {
        // given
        String originalText = "";

        // when-then
        assertThatThrownBy(() -> talkService.createAnswer(originalText))
                .isInstanceOf(BaseException.class)
                .hasMessage(ReportsErrorCode.CHATGPT_EMPTY_TEXT.getMessage());
    }

    @Test
    @DisplayName("아이 음성 텍스트에 대한 답장을 생성한다")
    void createAnswer() {
        // given
        String talkText = "그래서 화가 났어";

        // when
        String answer = talkService.createAnswer(talkText);

        // then
        assertThat(answer).isNotNull();
    }

    @Test
    @DisplayName("답장 멘트 tts 변환에 성공한다")
    void sendTalk() throws IOException {
        // given
        String answer = "정말 속상했겠다";
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);

        // when
        Resource response = talkService.sendTalk(kids.getUuid(), answer);

        // Then
        assertThat(response).isInstanceOf(FileSystemResource.class);
    }

    @Test
    @DisplayName("답장 멘트 tts 변환 및 및 자막, 감정 생성")
    void sendTalkMultipart() throws IOException {
        // given
        String answer = "정말 속상했겠다";
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);

        // when
        MultiValueMap<String, Object> response = talkService.sendTalkByMultiPart(kids.getUuid(), answer);

        // Then
        assertThat(response).isInstanceOf(MultiValueMap.class);
    }
}
