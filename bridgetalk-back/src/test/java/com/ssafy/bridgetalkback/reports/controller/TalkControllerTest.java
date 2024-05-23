package com.ssafy.bridgetalkback.reports.controller;

import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static com.ssafy.bridgetalkback.fixture.TokenFixture.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Talk [Controller Layer] -> TalkController 테스트")
public class TalkControllerTest extends ControllerTest {
    @Nested
    @DisplayName("대화 종료 API [GET /api/reports/talk-stop]")
    class stopTalk {
        private static final String BASE_URL = "/api/reports/talk-stop";
        private static final Long REPORTS_ID = 1L;

        @Test
        @DisplayName("Authorization_Header에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL, REPORTS_ID);

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
        @DisplayName("대화 그만하기에 성공한다")
        void success() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            Resource mockResource = mock(Resource.class);
            doNothing()
                    .when(reportsUpdateService)
                    .createReportAsync2(REPORTS_ID, UUID.randomUUID().toString());
            doReturn(mockResource)
                    .when(talkService)
                    .stopTalk(UUID.randomUUID());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL, REPORTS_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());
        }
    }


    @Nested
    @DisplayName("대화 시작 API [GET /api/reports/talk-start]")
    class startTalk {
        private static final String BASE_URL = "/api/reports/talk-start";

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
        @DisplayName("대화 시작하기에 성공한다")
        void success() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            Resource mockResource = mock(Resource.class);
            doReturn(mockResource)
                    .when(talkService)
                    .startTalk(UUID.randomUUID());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("대화 하기 API [PATCH /api/reports/talk-send")
    class sendTalk {
        private static final String BASE_URL = "/api/reports/talk-send";
        private static final Long REPORTS_ID = 1L;

        @Test
        @DisplayName("Authorization_Header에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            // when
            MockMultipartFile reportsFile = new MockMultipartFile("reportsFile", null,
                    "audio/mpeg", new byte[]{});
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .multipart(HttpMethod.PATCH, BASE_URL, REPORTS_ID)
                    .file(reportsFile)
                    .with(request1 -> {
                        request1.setMethod("PATCH");
                        return request1;
                    });

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
        @DisplayName("대화 하기에 성공한다")
        void success() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            Resource mockResource = mock(Resource.class);
            MockMultipartFile file = new MockMultipartFile("reportsFile", null,
                    "audio/mpeg", new byte[]{});
            String talkText = "그래서 화가 났어";
            String updateTalkText = "동생이 내 그림에 물을 부었어 그래서 화가 났어";
            doReturn(createTalkText())
                    .when(reportsService)
                    .createText(anyString());
            doReturn(createUpdateTalkText())
                    .when(reportsService)
                    .updateOriginContent(UUID.randomUUID(), REPORTS_ID, talkText);
            doReturn(mockResource)
                    .when(talkService)
                    .sendTalk(UUID.randomUUID(), updateTalkText);

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .multipart(BASE_URL, REPORTS_ID)
                    .file(file)
                    .with(request1 -> {
                        request1.setMethod("PATCH");
                        return request1;
                    })
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());
        }
    }

    private String createTalkText() {
        return "그래서 화가 났어";
    }

    private String createUpdateTalkText() {
        return "동생이 내 그림에 물을 부었어 그래서 화가 났어";
    }
}
