package com.ssafy.bridgetalkback.notification.controller;

import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.UUID;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static com.ssafy.bridgetalkback.fixture.TokenFixture.BEARER_TOKEN;
import static com.ssafy.bridgetalkback.fixture.TokenFixture.REFRESH_TOKEN;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Sse [Controller Layer] -> SseController 테스트")
class SseControllerTest extends ControllerTest {
    @Nested
    @DisplayName("알림 구독 API [GET /api/sse/subscribe]")
    class subscribe {
        private static final String BASE_URL = "/api/sse/subscribe";

        @Test
        @DisplayName("알림 구독하기에 성공한다.")
        void success() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            SseEmitter mockSseEmitter = mock(SseEmitter.class);
            String lastEventId = "";
            doReturn(mockSseEmitter)
                    .when(sseService)
                    .subscribe(String.valueOf(UUID.randomUUID()), lastEventId);

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN)
                    .header(CONTENT_TYPE, MediaType.TEXT_EVENT_STREAM_VALUE);
            // then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());

        }
    }

}