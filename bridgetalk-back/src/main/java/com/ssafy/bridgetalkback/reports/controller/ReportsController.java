package com.ssafy.bridgetalkback.reports.controller;

import com.ssafy.bridgetalkback.global.annotation.ExtractPayload;
import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.reports.dto.ReportsCreateResponseDto;
import com.ssafy.bridgetalkback.reports.dto.response.ReportsDetailResponseDto;
import com.ssafy.bridgetalkback.reports.dto.response.ReportsListResponseDto;
import com.ssafy.bridgetalkback.reports.service.ReportsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@Tag(name = "Reports", description = "ReportsController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class ReportsController {

    private final ReportsService reportsService;

    @PostMapping
    public ResponseEntity<?> createReports(@ExtractPayload String userId) {
        log.info("{ ReportsController } : Reports 생성 진입");
        ReportsCreateResponseDto reports = reportsService.createReports(UUID.fromString(userId));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reports);
    }

    @GetMapping("/{kidsId}/{language}")
    public ResponseEntity<List<ReportsListResponseDto>> reportsList(
            @ExtractPayload String userId, @PathVariable String kidsId, @PathVariable Language language) {
//        log.info("====================== kidsId : {}", kidsId);
        log.info("{ ReportsController } : 아이속마음 리스트 조회 진입");
        return ResponseEntity.ok(reportsService.reportsList(UUID.fromString(userId), UUID.fromString(kidsId), language));
    }

    @GetMapping("/{kidsId}/{reportsId}/{language}")
    public ResponseEntity<ReportsDetailResponseDto> reportsDetail(
            @ExtractPayload String userId, @PathVariable String kidsId, @PathVariable Long reportsId, @PathVariable Language language) {
        log.info("{ ReportsController } : 아이속마음 상세 조회 진입");
        return ResponseEntity.ok(reportsService.reportsDetail(UUID.fromString(userId), UUID.fromString(kidsId), reportsId, language));
    }

}
