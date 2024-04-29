package com.ssafy.bridgetalkback.chatgpt.exception;

import com.ssafy.bridgetalkback.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ChatGptErrorCode implements ErrorCode {
    CHATGPT_FAILED(HttpStatus.BAD_REQUEST, "GPT_001", "GPT API 호출 실패입니다.")
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
