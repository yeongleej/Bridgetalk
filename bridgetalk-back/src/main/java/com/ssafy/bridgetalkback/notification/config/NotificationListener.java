package com.ssafy.bridgetalkback.notification.config;

import com.ssafy.bridgetalkback.notification.dto.request.NotificationRequestDto;
import com.ssafy.bridgetalkback.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final NotificationService notificationService;

    @TransactionalEventListener
    @Async
    public void handleNotification(NotificationRequestDto notificationRequestDto) {
        notificationService.sendNotification(notificationRequestDto);
    }
}