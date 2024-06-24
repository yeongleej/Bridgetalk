package com.ssafy.bridgetalkback.auth.service;

import com.ssafy.bridgetalkback.auth.domain.RefreshToken;
import com.ssafy.bridgetalkback.common.ServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Auth [Service Layer] -> TokenService 테스트")
public class TokenServiceTest extends ServiceTest {
    @Autowired
    private TokenService tokenService;

    private final UUID USER_ID = UUID.fromString("7cfadd66-e491-4cb2-9d8f-6aa2e285dc46");
    private final String REFRESHTOKEN = "refresh_token";

    @Nested
    @DisplayName("Refresh Token 발급한다")
    class synchronizeRefreshToken {
        @Test
        @DisplayName("RefreshToken을 보유하지 않은 사용자라면 새로운 RefreshToken을 발급한다")
        void newUser() {
            // when
            tokenService.synchronizeRefreshToken(USER_ID, REFRESHTOKEN);

            // then
            RefreshToken findToken = refreshTokenRedisRepository.findById(USER_ID).orElseThrow();
            assertThat(findToken.getRefreshToken()).isEqualTo(REFRESHTOKEN);
        }

        @Test
        @DisplayName("RefreshToken을 보유하고 있는 사용자라면 RefreshToken을 업데이트한다")
        void oldUser() {
            // given
            refreshTokenRedisRepository.save(RefreshToken.createRefreshToken(USER_ID, REFRESHTOKEN));

            // when
            String newRefreshToken = REFRESHTOKEN + "new";
            tokenService.synchronizeRefreshToken(USER_ID, newRefreshToken);

            // then
            RefreshToken findToken = refreshTokenRedisRepository.findById(USER_ID).orElseThrow();
            assertThat(findToken.getRefreshToken()).isEqualTo(newRefreshToken);
        }
    }

    @Test
    @DisplayName("RTR정책에 의해서 RefreshToken을 재발급한다")
    void reissueRefreshTokenByRtrPolicy() {
        // given
        refreshTokenRedisRepository.save(RefreshToken.createRefreshToken(USER_ID, REFRESHTOKEN));

        // when
        final String newRefreshToken = REFRESHTOKEN + "new";
        tokenService.reissueRefreshTokenByRtrPolicy(USER_ID, newRefreshToken);

        // then
        RefreshToken findToken = refreshTokenRedisRepository.findById(USER_ID).orElseThrow();
        assertThat(findToken.getRefreshToken()).isEqualTo(newRefreshToken);
    }

    @Test
    @DisplayName("사용자가 보유하고 있는 RefreshToken을 삭제한다")
    void deleteRefreshTokenByMemberId() {
        // given
        refreshTokenRedisRepository.save(RefreshToken.createRefreshToken(USER_ID, REFRESHTOKEN));

        // when
        tokenService.deleteRefreshTokenByUserId(USER_ID);

        // then
        assertThat(refreshTokenRedisRepository.findById(USER_ID)).isEmpty();
    }

    @Test
    @DisplayName("해당 RefreshToken을 사용자가 보유하고 있는지 확인한다")
    void isRefreshTokenExists() {
        // given
        refreshTokenRedisRepository.save(RefreshToken.createRefreshToken(USER_ID, REFRESHTOKEN));

        // when
        final String fakeRefreshToken = REFRESHTOKEN + "fff";
        boolean actual1 = tokenService.isRefreshTokenExists(USER_ID, REFRESHTOKEN);
        boolean actual2 = tokenService.isRefreshTokenExists(USER_ID, fakeRefreshToken);

        // then
        assertAll(
                () -> assertThat(actual1).isTrue(),
                () -> assertThat(actual2).isFalse()
        );
    }
}
