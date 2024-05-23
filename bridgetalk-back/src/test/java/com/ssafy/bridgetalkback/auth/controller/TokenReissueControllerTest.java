package com.ssafy.bridgetalkback.auth.controller;

import com.ssafy.bridgetalkback.auth.dto.response.TokenResponseDto;
import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.common.ControllerTest;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static com.ssafy.bridgetalkback.fixture.TokenFixture.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Auth [Controller Layer] -> TokenReissueController 테스트")
public class TokenReissueControllerTest extends ControllerTest {
    @Nested
    @DisplayName("토큰 재발급 API 테스트 [POST /api/token/reissue]")
    class reissueTokens {
        private static final String BASE_URL = "/api/token/reissue";

        @Test
        @DisplayName("Authorization_Header에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL);

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
        @DisplayName("만료된 RefreshToken으로 인해 토큰 재발급에 실패한다")
        void throwExceptionByAuthExpiredToken() throws Exception {
            // given
            given(jwtProvider.getId(anyString()))
                    .willThrow(BaseException.type(AuthErrorCode.AUTH_EXPIRED_TOKEN));

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            final AuthErrorCode expectedError = AuthErrorCode.AUTH_EXPIRED_TOKEN;
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isUnauthorized(),
                            jsonPath("$.status").exists(),
                            jsonPath("$.status").value(expectedError.getStatus().value()),
                            jsonPath("$.errorCode").exists(),
                            jsonPath("$.errorCode").value(expectedError.getErrorCode()),
                            jsonPath("$.message").exists(),
                            jsonPath("$.message").value(expectedError.getMessage())
                    );
        }

        @Test
        @DisplayName("이미 사용한 RefreshToken이거나 조작된 RefreshToken이면 재발급에 실패한다")
        void throwExceptionByAuthInvalidToken() throws Exception {
            // given
            given(jwtProvider.getId(anyString()))
                    .willThrow(BaseException.type(AuthErrorCode.AUTH_INVALID_TOKEN));

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            final AuthErrorCode expectedError = AuthErrorCode.AUTH_INVALID_TOKEN;
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isUnauthorized(),
                            jsonPath("$.status").exists(),
                            jsonPath("$.status").value(expectedError.getStatus().value()),
                            jsonPath("$.errorCode").exists(),
                            jsonPath("$.errorCode").value(expectedError.getErrorCode()),
                            jsonPath("$.message").exists(),
                            jsonPath("$.message").value(expectedError.getMessage())
                    );
        }

        @Test
        @DisplayName("RefreshToken으로 AccessToken과 RefreshToken을 재발급받는다.")
        void success() throws Exception {
            // given
            TokenResponseDto tokenResponseDto = createTokenResponseDto();
            given(jwtProvider.getId(REFRESH_TOKEN)).willReturn(String.valueOf(UUID.randomUUID()));
            given(tokenReissueService.reissueTokens(UUID.randomUUID(), REFRESH_TOKEN)).willReturn(tokenResponseDto);


            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isOk()
                    );
        }

    }

    private TokenResponseDto createTokenResponseDto() {
        return new TokenResponseDto(ACCESS_TOKEN, REFRESH_TOKEN);
    }
}
