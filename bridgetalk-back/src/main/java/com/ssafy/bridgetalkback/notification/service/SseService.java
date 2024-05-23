package com.ssafy.bridgetalkback.notification.service;

import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.notification.domain.NotificationType;
import com.ssafy.bridgetalkback.notification.dto.request.NotificationRequestDto;
import com.ssafy.bridgetalkback.notification.exception.NotificationErrorCode;
import com.ssafy.bridgetalkback.notification.repository.SseRepositoryImpl;
import com.ssafy.bridgetalkback.notification.domain.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class SseService {

    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60; //60분

    private final SseRepositoryImpl sseRepository;

    /**
     * [SSE 통신]연결
     * @param receiverUuid : 알림 수신자 uuid
     * @param lastEventId : 클라이언트가 마지막으로 수신한 알림 id(emitterId)
     */
    public SseEmitter subscribe(String receiverUuid, String lastEventId) {
        String emitterId = receiverUuid + "_" + System.currentTimeMillis();

        SseEmitter sseEmitter = sseRepository.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT));
        log.info("new emitter added : {}", sseEmitter);
        log.info("lastEventId : {}", lastEventId);

        /* 상황별 emitter 삭제 처리 */
        sseEmitter.onCompletion(() -> sseRepository.deleteEmitterById(emitterId)); //완료 시, 타임아웃 시, 에러 발생 시
        sseEmitter.onTimeout(() -> sseRepository.deleteEmitterById(emitterId));
        sseEmitter.onError((e) -> {
            log.error(e.getMessage());
            sseRepository.deleteEmitterById(emitterId);
            throw BaseException.type(NotificationErrorCode.SUBSCRIBE_ERROR);
        });

        /* 503 Service Unavailable 방지용 dummy event 전송 */
        sendStart(sseEmitter, emitterId, createDummyNotification(receiverUuid));

        /* client가 미수신한 event 목록이 존재하는 경우
        * 마지막 수신 이후의 알림들 전송
        */
        if(!lastEventId.isEmpty()) { //client가 미수신한 event가 존재하는 경우 이를 전송하여 유실 예방
            Map<String, Object> eventCaches = sseRepository.findAllEventCacheStartsWithReceiverUuid(receiverUuid); //id에 해당하는 eventCache 조회
            eventCaches.entrySet().stream() //미수신 상태인 event 목록 전송
                    .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                    .forEach(entry -> emitEventToClient(sseEmitter, entry.getKey(), entry.getValue()));
        }

        return sseEmitter;
    }

//    public void send(Notification notificationResult) {
//        /* 로그인한 client의 sseEmitter 전체 호출 */
//        Map<String, SseEmitter> sseEmitters = sseRepository.findAllEmitterStartsWithReceiverUuid(notificationResult.getReceiverUuid());
//        sseEmitters.forEach(
//                (key, sseEmitter) -> {
//                    //log.info("key, notification : {}, {}", key, notification);
//                    sseRepository.saveEventCache(key, notificationResult); //저장
//                    emitEventToClient(sseEmitter, key, notificationResult); //전송
//                }
//        );
//    }

    /**
     * [SSE 통신]specific user에게 알림 전송
     * @param notificationRequestDto : 요청 알림 dto
     */
    public void send(NotificationRequestDto notificationRequestDto) {
        Map<String, SseEmitter> sseEmitters = sseRepository.findAllEmitter();
        sseEmitters.forEach(
                (key, sseEmitter) -> {
                    Notification notification = Notification.createNotification(
                            notificationRequestDto.receiverUuid(), key, notificationRequestDto.content(), notificationRequestDto.url(), notificationRequestDto.notificationType()
                    );
                    log.info("key, notification : {}, {}", key, notification);
                    sseRepository.saveEventCache(key, notification); //저장
                    emitEventToClient(sseEmitter, key, notification); //전송
                });
    }

    /**
     * [SSE 통신]dummy data 생성
     * : 503 Service Unavailable 방지
     * @param receiverUuid : 수신자 uuid
     */
    private Notification createDummyNotification(String receiverUuid) {
        return Notification.createNotification(receiverUuid, receiverUuid+"_"+System.currentTimeMillis(), "send dummy data to client", "https://bridgetalk.co.kr/", NotificationType.SEND_DUMMY_DATA);
    }

    /**
     * [SSE 통신] notification type별 event 전송
     * @param sseEmitter : 전송 알림 sse 객체
     * @param emitterId : 전송 알림 id
     * @param data : 전송할 데이터
     */
    private void sendStart(SseEmitter sseEmitter, String emitterId, Object data) {
        try {
            log.info("{ SseService }: sendNotification-알림 전송 start");
            sseEmitter.send(SseEmitter.event()
                    .id(emitterId)
                    .name("sse")
                    .data(data, MediaType.APPLICATION_JSON));
        } catch(IOException exception) {
            log.error(exception.getMessage());
            sseRepository.deleteEmitterById(emitterId);
            sseEmitter.completeWithError(exception);
            throw BaseException.type(NotificationErrorCode.SEND_ERROR);
        }
    }

    /**
     * [SSE 통신]
     * @param sseEmitter : 전송 알림 sse 객체
     * @param emitterId : 전송 알림 id
     * @param data : 전송할 데이터
     */
    private void emitEventToClient(SseEmitter sseEmitter, String emitterId, Object data) {
        try {
            sendStart(sseEmitter, emitterId, data);
        } catch (Exception e) {
            sseRepository.deleteEmitterById(emitterId);
            throw new RuntimeException("Connection Failed.");
        }
    }

}
