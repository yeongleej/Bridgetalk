package com.ssafy.bridgetalkback.reports.exception;

import com.ssafy.bridgetalkback.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReportsErrorCode implements ErrorCode {
    KIDS_NOT_FOUND(HttpStatus.NOT_FOUND, "REPORTS_001", "아이 프로필 정보가 없습니다."),
    REPORTS_NOT_FOUND(HttpStatus.NOT_FOUND, "REPORTS_002", "REPORTS 정보를 찾을 수 없습니다."),
    EMPTY_FILE(HttpStatus.BAD_REQUEST, "REPORTS_003", "전송된 파일이 없습니다."),
    CHATGPT_EMPTY_TEXT(HttpStatus.BAD_REQUEST, "REPORTS_004", "[chatgpt api 호출] 아이 음성 텍스트가 비어있습니다."),
    TALK_DUPLICATED(HttpStatus.BAD_REQUEST, "REPORTS_005", "이미 생성된 대화가 있습니다."),
    TALK_NOT_FOUD(HttpStatus.NOT_FOUND, "REPORT_006", "조회된 대화가 없습니다."),
    YOUTUBE_API_FAILED(HttpStatus.BAD_REQUEST, "REPORTS_007", "YOUTUBE API 호출 실패입니다."),
    UNABLE_TO_CONVERT_STRING_TO_LIST(HttpStatus.BAD_REQUEST, "REPORTS_008", "")
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
