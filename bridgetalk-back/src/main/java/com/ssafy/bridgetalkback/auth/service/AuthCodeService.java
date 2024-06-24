package com.ssafy.bridgetalkback.auth.service;

import com.ssafy.bridgetalkback.auth.domain.AuthCode;
import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.auth.repository.AuthCodeRedisRepository;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.parents.domain.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthCodeService {
    private final AuthCodeRedisRepository authCodeRedisRepository;

    @Transactional
    public String saveAuthCode(Email email) {
        log.info("{ AuthCodeService } : 인증번호 발급 혹은 재발급 진입");
        String authCode = createAuthCode();
        authCodeRedisRepository.findById(email.getValue())
                .ifPresentOrElse(
                        code -> {
                            code.updateAuthCode(authCode);
                            authCodeRedisRepository.save(code);
                        },
                        () -> authCodeRedisRepository.save(AuthCode.createAuthCode(email.getValue(), authCode))
                );
        log.info("{ AuthCodeService } : 인증번호 발급 성공 - "+authCode);
        return authCode;
    }

    public AuthCode findAuthCodeByEmail(Email email) {
        return authCodeRedisRepository.findById(email.getValue())
                .orElseThrow(() -> BaseException.type(AuthErrorCode.AUTH_CODE_NOT_FOUND));
    }

    public String createAuthCode() {
        return String.valueOf((int)(Math.random() * 99999) + 10000);
    }
}
