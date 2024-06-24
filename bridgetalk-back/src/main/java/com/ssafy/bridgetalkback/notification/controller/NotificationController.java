package com.ssafy.bridgetalkback.notification.controller;

import com.ssafy.bridgetalkback.global.annotation.ExtractPayload;
import com.ssafy.bridgetalkback.notification.domain.Notification;
import com.ssafy.bridgetalkback.notification.domain.NotificationType;
import com.ssafy.bridgetalkback.notification.dto.request.NotificationRequestDto;
import com.ssafy.bridgetalkback.notification.service.NotificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "notification", description = "NotificationController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping()
    public ResponseEntity<List<Notification>> getAllNotificationByReceiverUuid(@ExtractPayload String userId) {
        log.info("{ NotificationController }: 사용자 알림 조회");
        return ResponseEntity.ok().body(notificationService.getAllNotificationByReceiverUuid(userId));
    }

    @PutMapping("/read")
    public ResponseEntity<List<Notification>> updateNotificationReadStatusByReceiverUuid(@ExtractPayload String userId) {
        log.info("{ NotificationController }: 알림 읽기");
        notificationService.updateNotificationReadStatusByReceiverUuid(userId);
        return ResponseEntity.ok().body(notificationService.getAllNotificationByReceiverUuid(userId)); //수정 후 새롭게 전달
    }

    @DeleteMapping("/{notificationId}/delete")
    public ResponseEntity<List<Notification>> updateNotificationDeleteStatusById(@ExtractPayload String userId, @PathVariable Long notificationId) {
        log.info("{ NotificationController }: 알림 삭제");
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.ok().body(notificationService.getAllNotificationByReceiverUuid(userId)); //수정 후 새롭게 전달
    }

    @GetMapping("/send-test/{receiverUuid}")
    public ResponseEntity<?> sendTest(@ExtractPayload String userId, @PathVariable String receiverUuid){
        log.info("{ NotificationController }: 알림 전송 테스트");
        NotificationRequestDto notificationRequestDto = NotificationRequestDto.builder()
                .receiverUuid(receiverUuid)
                .url("url")
                .content("send notification test")
                .notificationType(NotificationType.KID_REPORTS_REGISTER)
                .build();
        notificationService.sendNotification(notificationRequestDto);
        return ResponseEntity.ok().body("success to send notification");
    }
}
