package com.ssafy.bridgetalkback.notification.dto.request;

import com.ssafy.bridgetalkback.notification.domain.NotificationType;
import lombok.Builder;

@Builder
public record NotificationRequestDto(
        String receiverUuid,
        String content,
        String url,
        NotificationType notificationType
) {
}
