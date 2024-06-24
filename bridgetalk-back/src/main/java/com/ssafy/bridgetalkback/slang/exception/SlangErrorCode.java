package com.ssafy.bridgetalkback.slang.exception;

import com.ssafy.bridgetalkback.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SlangErrorCode implements ErrorCode {
    SLANGLIST_NOT_FOUND(HttpStatus.NOT_FOUND, "SLANG_001", "조회한 SlangList의 값이 비어있습니다.");

    private final HttpStatus status;
    private final String errorCode;
    private final String message;

}
