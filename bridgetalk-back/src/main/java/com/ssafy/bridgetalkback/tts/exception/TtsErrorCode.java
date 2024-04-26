package com.ssafy.bridgetalkback.tts.exception;

import com.ssafy.bridgetalkback.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TtsErrorCode implements ErrorCode {
    CLOVA_API_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "TTS_001", "CLOVA TTS 실행 중 에러 발생");

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
