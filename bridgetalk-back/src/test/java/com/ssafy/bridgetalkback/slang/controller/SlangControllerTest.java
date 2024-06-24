package com.ssafy.bridgetalkback.slang.controller;

import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.common.ControllerTest;
import com.ssafy.bridgetalkback.slang.dto.response.SlangListResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static com.ssafy.bridgetalkback.fixture.TokenFixture.BEARER_TOKEN;
import static com.ssafy.bridgetalkback.fixture.TokenFixture.REFRESH_TOKEN;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Slang [Controller Layer] -> SlangController 테스트")
public class SlangControllerTest extends ControllerTest {

    @Nested
    @DisplayName("Slang 전체 조회 API [GET /api/slang]")
    class findAllSlang {
        private static final String BASE_URL = "/api/slang";

        @Test
        @DisplayName("Authorization_Header에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL);

            // then
            final AuthErrorCode expectedError = AuthErrorCode.INVALID_PERMISSION;
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isForbidden(),
                            jsonPath("$.status").exists(),
                            jsonPath("$.status").value(expectedError.getStatus().value()),
                            jsonPath("$.errorCode").exists(),
                            jsonPath("$.errorCode").value(expectedError.getErrorCode()),
                            jsonPath("$.message").exists(),
                            jsonPath("$.message").value(expectedError.getMessage())
                    );

        }

        @Test
        @DisplayName("Slang 전체 조회에 성공한다.")
        void success() throws Exception {
            // given
            List<SlangListResponseDto> mockResponse = Collections.singletonList(
                    new SlangListResponseDto(1L, "slangWord", "originalWord", "meaning", "vietnamesePronunciation", "vietnameseTranslation")
            );

            Pageable sortedByKorean = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "slangWord"));
            Page<SlangListResponseDto> pageResponse = new PageImpl<>(mockResponse, sortedByKorean, mockResponse.size());

            given(slangService.findAllSlang(sortedByKorean)).willReturn(pageResponse);

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json;charset=UTF-8"))
                    .andExpect(jsonPath("$.currentPage").value(1))
                    .andExpect(jsonPath("$.startPage").value(0))
                    .andExpect(jsonPath("$.endPage").value(1))
                    .andExpect(jsonPath("$.list[0].slangId").value(1L))
                    .andExpect(jsonPath("$.list[0].slangWord").value("slangWord"))
                    .andExpect(jsonPath("$.list[0].originalWord").value("originalWord"))
                    .andExpect(jsonPath("$.list[0].meaning").value("meaning"))
                    .andExpect(jsonPath("$.list[0].vietnamesePronunciation").value("vietnamesePronunciation"))
                    .andExpect(jsonPath("$.list[0].vietnameseTranslation").value("vietnameseTranslation"))
                    .andExpect(jsonPath("$.list.length()").value(1));
        }


        @Test
        @DisplayName("SlangWord가 한글 ㄱ,ㄴ,ㄷ 순으로 정렬되어 조회된다.")
        void successOrderedByKorean() throws Exception {
            // given
            List<SlangListResponseDto> mockResponse = Arrays.asList(
                    new SlangListResponseDto(1L, "다다다", "originalWord3", "meaning3", "vietnamesePronunciation3", "vietnameseTranslation3"),
                    new SlangListResponseDto(2L, "가가가", "originalWord1", "meaning1", "vietnamesePronunciation1", "vietnameseTranslation1"),
                    new SlangListResponseDto(3L, "나나나", "originalWord2", "meaning2", "vietnamesePronunciation2", "vietnameseTranslation2")
            );

            Pageable sortedByKorean = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "slangWord"));

            List<SlangListResponseDto> sortedMockResponse = mockResponse.stream()
                    .sorted(Comparator.comparing(SlangListResponseDto::slangWord))
                    .collect(Collectors.toList());
            Page<SlangListResponseDto> pageResponse = new PageImpl<>(sortedMockResponse, sortedByKorean, sortedMockResponse.size());


            given(slangService.findAllSlang(sortedByKorean)).willReturn(pageResponse);

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);
            // then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json;charset=UTF-8"))
                    .andExpect(jsonPath("$.currentPage").value(1))
                    .andExpect(jsonPath("$.startPage").value(0))
                    .andExpect(jsonPath("$.endPage").value(1))
                    .andExpect(jsonPath("$.list[0].slangId").value(2L))
                    .andExpect(jsonPath("$.list[0].slangWord").value("가가가"))
                    .andExpect(jsonPath("$.list[1].slangId").value(3L))
                    .andExpect(jsonPath("$.list[1].slangWord").value("나나나"))
                    .andExpect(jsonPath("$.list[2].slangId").value(1L))
                    .andExpect(jsonPath("$.list[2].slangWord").value("다다다"))
                    .andExpect(jsonPath("$.list.length()").value(3));
        }

    }


}