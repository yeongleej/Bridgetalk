package com.ssafy.bridgetalkback.letters.controller;

import com.ssafy.bridgetalkback.common.ControllerTest;
import com.ssafy.bridgetalkback.letters.dto.response.LettersResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static com.ssafy.bridgetalkback.fixture.TokenFixture.BEARER_TOKEN;
import static com.ssafy.bridgetalkback.fixture.TokenFixture.REFRESH_TOKEN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Letter [Controller Layer] -> LetterController 테스트")
class LettersControllerTest extends ControllerTest {

    private static final String BASE_URL = "/api/letters";
    private static final String FILE_PATH = "src/test/resources/files/";


    @Test
    @DisplayName("부모 음성 편지 업로드 테스트")
    public void uploadLetters() throws Exception {
        // given
        String fileName = "test.mp3";
        String contentType = "audio/mpeg";
        String dir = "letters";
        MultipartFile file = createMockMultipartFile(dir, fileName, contentType);

        given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
        doReturn(createUploadUrl())
                .when(lettersService)
                .saveVoiceFile(file);

        given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
        doReturn(createLettersResponseDto())
                .when(lettersService)
                .createText(createUploadUrl(), UUID.randomUUID().toString(), 1L);

        // when
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(BASE_URL+"/upload")
                .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

        // then
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
    private String createUploadUrl() {
        return "https://birdgetalk/uploadUrl.com";
    }
    private LettersResponseDto createLettersResponseDto() {
        return LettersResponseDto.builder()
                .lettersId(1L)
                .lettersOriginalContent("originContent")
                .lettersTranslationContent("translationContent")
                .build();
    }
    private MockMultipartFile createMockMultipartFile(String dir, String fileName, String contentType) throws IOException {
        try (FileInputStream stream = new FileInputStream(FILE_PATH + fileName)) {
            return new MockMultipartFile(dir, fileName, contentType, stream);
        }
    }


    @Test
    @DisplayName("음성데이터타입(audio) 값을 반환한다.")
    void shouldReturnAudioFile() throws Exception {
        // given
        given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
        Resource mockResource = mock(Resource.class);
        when(lettersService.findLettersVoice(any(Long.class))).thenReturn(mockResource);

        // when
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(BASE_URL + "/" + any(Long.class))
                .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN)
                .accept(MediaType.parseMediaType("audio/mpeg;charset=UTF-8"));

        // then
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.parseMediaType("audio/mpeg;charset=UTF-8")));
    }

    @Test
    @DisplayName("편지에 대한 음성 데이터 조회에 성공한다.")
    void success() throws Exception {
        // given
        given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
        Resource mockResource = mock(Resource.class);
        when(lettersService.findLettersVoice(any(Long.class))).thenReturn(mockResource);

        // when
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(BASE_URL + "/" + any(Long.class))
                .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN)
                .accept(MediaType.parseMediaType("audio/mpeg;charset=UTF-8"));

        // then
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("음성 편지 삭제에 성공한다.")
    void deleteSuccess() throws Exception {
        // given
        given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
        doNothing().when(lettersService).deleteLetters(any(Long.class));

        // when
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(BASE_URL + "/" + any(Long.class))
                .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN)
                .accept(MediaType.APPLICATION_JSON);

        // then
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
}

