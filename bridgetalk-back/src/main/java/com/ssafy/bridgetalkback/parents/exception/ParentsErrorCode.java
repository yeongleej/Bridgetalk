package com.ssafy.bridgetalkback.parents.exception;

import com.ssafy.bridgetalkback.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ParentsErrorCode implements ErrorCode {
    INVALID_PASSWORD_PATTERN(HttpStatus.BAD_REQUEST, "PARENTS_001", "비밀번호 형식에 맞지 않습니다."),
    INVALID_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, "PARENTS_002", "이메일 형식에 맞지 않습니다."),
    PARENTS_NOT_FOUND(HttpStatus.NOT_FOUND, "PARENTS_003", "부모 정보를 찾을 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
