package com.ssafy.bridgetalkback.auth.exception;

import com.ssafy.bridgetalkback.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "AUTH_001", "이미 가입된 이메일입니다.")
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
