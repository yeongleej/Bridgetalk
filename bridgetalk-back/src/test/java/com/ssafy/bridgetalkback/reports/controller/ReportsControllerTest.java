package com.ssafy.bridgetalkback.reports.controller;

import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.common.ControllerTest;
import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.reports.dto.ReportsCreateResponseDto;
import com.ssafy.bridgetalkback.reports.dto.response.ReportsDetailResponseDto;
import com.ssafy.bridgetalkback.reports.dto.response.ReportsListResponseDto;
import com.ssafy.bridgetalkback.reports.dto.response.VideoResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static com.ssafy.bridgetalkback.fixture.TokenFixture.BEARER_TOKEN;
import static com.ssafy.bridgetalkback.fixture.TokenFixture.REFRESH_TOKEN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Reports [Controller Layer] -> ReportsControllerTest 테스트")
public class ReportsControllerTest extends ControllerTest {

    private ReportsDetailResponseDto createReportsDetailResponseDto() {
        List<String> keywordsList1 = new ArrayList<>();
        keywordsList1.add("놀이기구");
        keywordsList1.add("사탕");
        keywordsList1.add("엄마");
        List<VideoResponseDto> reportsVideoList = new ArrayList<>();
        return new ReportsDetailResponseDto(1L, "요약 내용1", keywordsList1, "솔루션1", reportsVideoList, LocalDateTime.now());
    }

    private List<ReportsListResponseDto> createReportsListResponseDto() {
        List<ReportsListResponseDto> reportsListResponseDtoList = new ArrayList<>();
        List<String> keywordsList1 = new ArrayList<>();
        keywordsList1.add("놀이기구");
        keywordsList1.add("사탕");
        keywordsList1.add("엄마");
        List<String> keywordsList2 = new ArrayList<>();
        keywordsList2.add("탕후루");
        keywordsList2.add("친구");
        keywordsList2.add("학교");
        reportsListResponseDtoList.add(new ReportsListResponseDto(1L, "요약 내용1", keywordsList1, LocalDateTime.now()));
        reportsListResponseDtoList.add(new ReportsListResponseDto(2L, "요약 내용2", keywordsList2, LocalDateTime.now()));
        return reportsListResponseDtoList;
    }

    @Nested
    @DisplayName("아이 속마음의 레포트 리스트 조회 API [GET /api/reports/{kidsId}/{language}]")
    class reportsList {
        private static final String BASE_URL = "/api/reports/";

        @Test
        @DisplayName("Authrization_Header에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            //given
            String kidsId = String.valueOf(UUID.randomUUID());
            String language = "kor";

            //when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL + kidsId + "/" + language);

            //then
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
        @DisplayName("(한국어) 레포트 리스트 조회에 성공한다")
        void successKor() throws Exception {
            //given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(createReportsListResponseDto())
                    .when(reportsService)
                    .reportsList(UUID.randomUUID(), UUID.randomUUID(), Language.valueOf("kor"));
            String kidsId = String.valueOf(UUID.randomUUID());
            String language = "kor";

            //when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL + kidsId + "/" + language)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            //then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("(베트남어) 레포트 리스트 조회에 성공한다")
        void successViet() throws Exception {
            //given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(createReportsListResponseDto())
                    .when(reportsService)
                    .reportsList(UUID.randomUUID(), UUID.randomUUID(), Language.valueOf("viet"));
            String kidsId = String.valueOf(UUID.randomUUID());
            String language = "viet";

            //when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL + kidsId + "/" + language)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            //then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("아이 속마음의 레포트 상세조회 API [GET /api/reports/{kidsId}/{reportsId}/{language}]")
    class reportsDetail {
        private static final String BASE_URL = "/api/reports/";

        @Test
        @DisplayName("Authrization_Header에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            //given
            String kidsId = String.valueOf(UUID.randomUUID());
            Long reportsId = 1L;
            String language = "kor";

            //when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL + kidsId + "/" + reportsId + "/" + language);

            //then
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
        @DisplayName("(한국어) 레포트 상세조회에 성공한다")
        void successKor() throws Exception {
            //given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(createReportsDetailResponseDto())
                    .when(reportsService)
                    .reportsDetail(UUID.randomUUID(), UUID.randomUUID(), 1L, Language.valueOf("kor"));
            String kidsId = String.valueOf(UUID.randomUUID());
            Long reportsId = 1L;
            String language = "kor";

            //when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL + kidsId + "/" + reportsId + "/" + language)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            //then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("(베트남어) 레포트 상세조회에 성공한다")
        void successViet() throws Exception {
            //given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(createReportsDetailResponseDto())
                    .when(reportsService)
                    .reportsDetail(UUID.randomUUID(), UUID.randomUUID(), 1L, Language.valueOf("viet"));
            String kidsId = String.valueOf(UUID.randomUUID());
            Long reportsId = 1L;
            String language = "viet";

            //when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL + kidsId + "/" + reportsId + "/" + language)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            //then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("Reports 생성 API [GET /api/reports]")
    class createReports {

        private static final String BASE_URL = "/api/reports";

        @Test
        @DisplayName("Authorization_Header에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL );

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
        @DisplayName("reports 생성에 성공한다.")
        void success() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));

            ReportsCreateResponseDto reports = ReportsCreateResponseDto.builder()
                    .reportsId(any(Long.class))
                    .build();
            when(reportsService.createReports(UUID.randomUUID())).thenReturn(reports);

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isCreated());


        }

    }
}
