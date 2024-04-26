package com.ssafy.bridgetalkback.kids.exception;

import com.ssafy.bridgetalkback.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum KidsErrorCode implements ErrorCode {
    KIDS_NOT_FOUND(HttpStatus.NOT_FOUND, "KIDS_001", "아이 정보를 찾을 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
