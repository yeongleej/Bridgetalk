package com.ssafy.bridgetalkback.notification.repository;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@NoArgsConstructor
@Repository
public class SseRepositoryImpl implements SseRepository {
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final Map<String, Object> eventCache = new ConcurrentHashMap<>();

    /**
     * emitter 저장
     * : 알림 발송 전, Notification 객체를 emitters에 저장
     */
    @Override
    public SseEmitter save(String emitterId, SseEmitter sseEmitter) {
        emitters.put(emitterId, sseEmitter);
        return sseEmitter;
    }

    /**
     * event 저장
     * : 알림 발송 후, Notification 객체를 EventCache에 저장
     */
    @Override
    public void saveEventCache(String eventCacheId, Object event) {
        eventCache.put(eventCacheId, event);
    }

    /**
     * specific user 관련 모든 emitter 조회
     */
    @Override
    public Map<String, SseEmitter> findAllEmitterStartsWithReceiverUuid(String receiverUuid) {
        return emitters.entrySet().stream() //다수의 emitter가 존재할 수 있기에 stream 사용
                .filter(entry -> entry.getKey().startsWith(receiverUuid))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * specific user 관련 모든 event 조회
     */
    @Override
    public Map<String, Object> findAllEventCacheStartsWithReceiverUuid(String receiverUuid) {
        return emitters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(receiverUuid))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<String, SseEmitter> findAllEmitter() {
        return emitters.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * specific user 관련 단일 emitter 삭제
     */
    @Override
    public void deleteEmitterById(String id) {
        emitters.remove(id);
    }

    /**
     * specific user 관련 모든 emitter 삭제
     */
    @Override
    public void deleteAllEmitterStartsWithId(String id) {
        emitters.forEach((key, emitter) -> {
            if(key.startsWith(id)) {
                emitters.remove(key);
            }
        });
    }

    /**
     * specific user 관련 모든 event 삭제
     */
    @Override
    public void deleteAllEventCacheStartsWithId(String id) {
        emitters.forEach((key, emitter) -> {
            if(key.startsWith(id)) {
                emitters.remove(key);
            }
        });
    }

}
