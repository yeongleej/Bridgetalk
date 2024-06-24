package com.ssafy.bridgetalkback.notification.controller;

import com.ssafy.bridgetalkback.global.annotation.ExtractPayload;
import com.ssafy.bridgetalkback.notification.service.SseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@Tag(name = "sse", description = "SseController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sse")
public class SseController {
    private final SseService sseService;

    /**
     * 로그인 시 SSE 구독(연결)
     * Content-type : text/event-stream
     */
    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> subscribe(@ExtractPayload String userId,
                                                @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
        log.info("{ SseController } - sse 연결 시작");
        return ResponseEntity.ok(sseService.subscribe(userId, lastEventId));
    }
}