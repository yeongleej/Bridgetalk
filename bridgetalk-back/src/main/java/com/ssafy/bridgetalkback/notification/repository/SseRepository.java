package com.ssafy.bridgetalkback.notification.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.UUID;

public interface SseRepository {
    SseEmitter save(String emitterId, SseEmitter sseEmitter);

    void saveEventCache(String eventCacheId, Object event);

    Map<String, SseEmitter> findAllEmitterStartsWithReceiverUuid(String receiverUuid);

    Map<String, Object> findAllEventCacheStartsWithReceiverUuid(String receiverUuid);

    Map<String, SseEmitter> findAllEmitter();

    void deleteEmitterById(String id);

    void deleteAllEmitterStartsWithId(String id);

    void deleteAllEventCacheStartsWithId(String id);
}
