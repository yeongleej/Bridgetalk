package com.ssafy.bridgetalkback.letters.service;

import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.tts.service.TtsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Clova Speech Service [Service Layer] -> Speech-To-Text 서비스 테스트")
public class ClovaSpeechServiceTest extends ServiceTest {

    @Autowired
    private ClovaSpeechService clovaSpeechService;

    @Test
    @DisplayName("Clova Stt 변환 성공")
    public void clova_stt() throws IOException {
        // Given
        String fileUrl = "https://bridge-talk.s3.ap-northeast-2.amazonaws.com/test/test-k1.mp3";

        // When
        String response = clovaSpeechService.stt(fileUrl);

        // Then
        assertThat(response).isInstanceOf(String.class);
    }

}