package com.ssafy.bridgetalkback.auth.service;

import com.ssafy.bridgetalkback.auth.domain.RefreshToken;
import com.ssafy.bridgetalkback.auth.dto.response.TokenResponseDto;
import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.auth.utils.JwtProvider;
import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Auth [Service Layer] -> TokenReissueService 테스트")
public class TokenReissueServiceTest extends ServiceTest {
    @Autowired
    private TokenReissueService tokenReissueService;

    @Autowired
    private JwtProvider jwtProvider;

    private final UUID USER_ID = UUID.fromString("7cfadd66-e491-4cb2-9d8f-6aa2e285dc46");
    private String refreshToken;
    private String fake_refreshToken;

    @BeforeEach
    void setup() {
        refreshToken = jwtProvider.createRefreshToken(USER_ID);
        fake_refreshToken = "fake_refreshToken";
    }

    @Nested
    @DisplayName("토큰 재발급")
    class reissueTokens {
        @Test
        @DisplayName("RefreshToken이 유효하지 않으면 예외가 발생한다")
        void throwExceptionByAuthInvalidToken() {
            // when - then
            assertThatThrownBy(() -> tokenReissueService.reissueTokens(USER_ID, fake_refreshToken))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(AuthErrorCode.AUTH_INVALID_TOKEN.getMessage());
        }

        @Test
        @DisplayName("RefreshToken을 통해서 AccessToken과 RefreshToken을 재발급받는데 성공한다")
        void success() {
            // given
            refreshTokenRedisRepository.save(RefreshToken.createRefreshToken(USER_ID, refreshToken));

            // when
            TokenResponseDto responseDto = tokenReissueService.reissueTokens(USER_ID, refreshToken);

            // then
            assertAll(
                    () -> assertThat(responseDto).isNotNull(),
                    () -> assertThat(responseDto).usingRecursiveComparison().isNotNull()
            );
        }
    }
}
