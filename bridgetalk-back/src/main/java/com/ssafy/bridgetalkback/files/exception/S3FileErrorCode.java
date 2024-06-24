package com.ssafy.bridgetalkback.files.exception;

import com.ssafy.bridgetalkback.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum S3FileErrorCode implements ErrorCode {
    EMPTY_FILE(HttpStatus.BAD_REQUEST, "UPLOAD_001", "전송된 파일이 없습니다."),
    S3_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "UPLOAD_002", "파일 업로드에 실패했습니다."),
    INVALID_DIR(HttpStatus.BAD_REQUEST, "UPLOAD_003", "유효하지 않은 경로입니다."),
    NOT_AN_AUDIO(HttpStatus.BAD_REQUEST, "UPLOAD_005", "오디오 파일만 첨부할 수 있습니다."),
    NOT_AN_IMAGE(HttpStatus.BAD_REQUEST, "UPLOAD_004", "이미지 파일만 첨부할 수 있습니다."),
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}

