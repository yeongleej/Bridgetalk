package com.ssafy.bridgetalkback.auth.service;

import com.ssafy.bridgetalkback.auth.domain.RefreshToken;
import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.auth.repository.RefreshTokenRedisRepository;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TokenService {
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;

    @Transactional
    public void synchronizeRefreshToken(UUID userId, String refreshToken) {
        log.info("{ TokenService } : 토큰 발급 혹은 재발급 진입");
        refreshTokenRedisRepository.findById(userId)
                .ifPresentOrElse(
                        token -> {
                            token.updateRefreshToken(refreshToken);
                            refreshTokenRedisRepository.save(token);
                        },
                        () -> refreshTokenRedisRepository.save(RefreshToken.createRefreshToken(userId, refreshToken))
                );
        log.info("{ TokenService } : 토큰 발급 성공");
    }

    public RefreshToken findRefreshTokenById(UUID userId) {
        return refreshTokenRedisRepository.findById(userId)
                .orElseThrow(() -> BaseException.type(AuthErrorCode.REFRESH_TOKEN_NOT_FOUND));
    }

    @Transactional
    public void reissueRefreshTokenByRtrPolicy(UUID userId, String newRefreshToken) {
        RefreshToken refreshToken = findRefreshTokenById(userId);
        refreshToken.updateRefreshToken(newRefreshToken);
        refreshTokenRedisRepository.save(refreshToken);
    }

    @Transactional
    public void deleteRefreshTokenByUserId(UUID userId) {
        refreshTokenRedisRepository.deleteById(userId);
    }

    public boolean isRefreshTokenExists(UUID userId, String refreshToken) {
        return refreshTokenRedisRepository.findById(userId)
                .map(token -> token.getRefreshToken().equals(refreshToken))
                .orElse(false);
    }
}
