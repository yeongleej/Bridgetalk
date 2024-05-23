package com.ssafy.bridgetalkback.tts.service;

import com.ssafy.bridgetalkback.common.ServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("TtsService [Service Layer] -> Text-to-Speech 서비스 테스트")
public class TtsServiceTest extends ServiceTest {

    @Autowired
    private TtsService ttsService;

    private HttpURLConnection mockConnection;
    private URL mockUrl;
    private InputStream mockInputStream;

    @BeforeEach
    void setup() throws Exception {
        mockUrl = mock(URL.class);
        mockConnection = mock(HttpURLConnection.class);
        mockInputStream = new ByteArrayInputStream("테스트 오디오 파일".getBytes());

        when(mockUrl.openConnection()).thenReturn(mockConnection);
        when(mockConnection.getInputStream()).thenReturn(mockInputStream);

        ReflectionTestUtils.setField(ttsService, "url", mockUrl);
    }

    @Test
    @DisplayName("Text-to-Speech 변환 성공")
    void testTextToSpeechSuccess() throws Exception {
        // Given
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);

        // When
        Resource response = ttsService.textToSpeech("테스트할 임시 텍스트 데이터입니다.");

        // Then
        assertThat(response).isInstanceOf(FileSystemResource.class);
    }

    @Test
    @DisplayName("변환된 파일이 mp3 형식인지 확인")
    void testFileIsMp3Format() throws Exception {
        // Given
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);

        // When
        Resource response = ttsService.textToSpeech("테스트할 임시 텍스트 데이터입니다.");

        // Then
        FileSystemResource fsr = (FileSystemResource) response;
        assertThat(fsr.getFilename()).endsWith(".mp3");
    }
}
