package com.ssafy.bridgetalkback.notification.service;

import com.ssafy.bridgetalkback.notification.domain.Notification;
import com.ssafy.bridgetalkback.notification.dto.request.NotificationRequestDto;
import com.ssafy.bridgetalkback.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {

    private final SseService sseService;
    private final NotificationRepository notificationRepository;
    private final ApplicationEventPublisher eventPublisher;


    /**
     * [DB 연동]전체 알림 조회
     */
    @Transactional(readOnly = true)
    public List<Notification> getAllNotificationByReceiverUuid(String receiverUuid) {
        List<Notification> notificationList = notificationRepository.findAllByReceiverUuidAndIsDeleted(receiverUuid, 0);
//        return notificationList.stream().map(Notification::createNotification).collect(Collectors.toList());
        return notificationList;
    }

    /**
     * [DB 연동]전체 알림 읽음 상태 업데이트
     */
    @Transactional
    public void updateNotificationReadStatusByReceiverUuid(String receiverUuid) {
        notificationRepository.bulkReadUpdate(receiverUuid);
    }

    /**
     * [DB 연동]단일 알림 삭제 상태 업데이트
     */
    @Transactional
    public void updateNotificationDeleteStatusById(String notificationId) {
        notificationRepository.bulkDeletedUpdate(notificationId);
    }

    /**
     * [DB 연동]개별 알림 조회
     */
    public String checkUsernameByEmitterId(String emitterId) {
        Notification selNotification = notificationRepository.findByEmitterId(emitterId);

        String receiverUuid = selNotification.getReceiverUuid();

        return receiverUuid;
    }


    /**
     * [DB 연동]단일 알림 전송
     */
    @Transactional
    public void sendNotification(NotificationRequestDto notificationRequestDto) {
//        Notification notificationResult = notificationRepository.save(notification); //DB 저장
//        sseService.send(notificationResult);
        sseService.send(notificationRequestDto);
    }

    /**
     * [DB 연동]단일 알림 삭제
     */
    @Transactional
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}