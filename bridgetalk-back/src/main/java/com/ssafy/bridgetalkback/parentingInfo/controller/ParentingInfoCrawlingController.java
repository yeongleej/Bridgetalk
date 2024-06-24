package com.ssafy.bridgetalkback.parentingInfo.controller;

import com.ssafy.bridgetalkback.global.annotation.ExtractPayload;
import com.ssafy.bridgetalkback.parentingInfo.service.ParentingInfoCrawlingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "ParentingInfoCrawling", description = "ParentingInfoCrawlingController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parentingInfo")
public class ParentingInfoCrawlingController {
    private final ParentingInfoCrawlingService crawlingService;

    @GetMapping("/crawling")
    public ResponseEntity<Void> crawling(@ExtractPayload String userId) throws Exception {
        log.info("{ ParentingInfoController } : 육아정보 크롤링 진입");
        crawlingService.startParentingInfoCrawling();
        return ResponseEntity.ok().build();
    }
}
