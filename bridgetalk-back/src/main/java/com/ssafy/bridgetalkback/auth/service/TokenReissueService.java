package com.ssafy.bridgetalkback.auth.service;

import com.ssafy.bridgetalkback.auth.dto.response.TokenResponseDto;
import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.auth.utils.JwtProvider;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenReissueService {
    private final TokenService tokenService;
    private final JwtProvider jwtProvider;

    @Transactional
    public TokenResponseDto reissueTokens(UUID userId, String refreshToken) {
        log.info("{ TokenReissueService } : 토큰 재발급 진입");
        // 사용자가 보유하고 있는 RefreshToken인지
        if (!tokenService.isRefreshTokenExists(userId, refreshToken)) {
            log.info("{ TokenReissueService } : 유저가 보유하고 있는 토큰이 아님");
            throw BaseException.type(AuthErrorCode.AUTH_INVALID_TOKEN);
        }

        String newAccessToken = jwtProvider.createAccessToken(userId);
        String newRefreshToken = jwtProvider.createRefreshToken(userId);

        // RTR 정책에 의해 사용자가 보유하고 있는 Refresh Token 업데이트
        tokenService.reissueRefreshTokenByRtrPolicy(userId, newRefreshToken);

        return new TokenResponseDto(newAccessToken, newRefreshToken);
    }
}