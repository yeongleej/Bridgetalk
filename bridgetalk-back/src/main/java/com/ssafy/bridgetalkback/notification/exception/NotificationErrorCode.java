package com.ssafy.bridgetalkback.notification.exception;

import com.ssafy.bridgetalkback.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum NotificationErrorCode implements ErrorCode {
    SUBSCRIBE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "NOTIFICATION_001", "SSE 구독 오류"),
    SEND_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "NOTIFICATION_002", "SSE 알림 전달 오류");

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
