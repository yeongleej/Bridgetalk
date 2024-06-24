package com.ssafy.bridgetalkback.slang.controller;

import com.ssafy.bridgetalkback.global.annotation.ExtractPayload;
import com.ssafy.bridgetalkback.slang.dto.response.SlangListResponseDto;
import com.ssafy.bridgetalkback.slang.service.SlangService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Tag(name = "slang", description = "SlangController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/slang")
public class SlangController {

    private final SlangService slangService;

    @GetMapping
    public ResponseEntity<?> findAllSlang(@ExtractPayload String userId, @PageableDefault(page = 0, size = 10, sort = "slangWord", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("{SlangController} : 줄임말 전체조회 컨트롤러 진입");

        Page<SlangListResponseDto> slangList = slangService.findAllSlang(pageable);
        int nowPage = slangList.getPageable().getPageNumber() + 1;
        int startPage = 0;
        int endPage = slangList.getTotalPages();

        Map<String, Object> response = new HashMap<>();
        response.put("currentPage", nowPage);
        response.put("startPage", startPage);
        response.put("endPage", endPage);
        response.put("list", slangList.getContent());

        return ResponseEntity.ok(response);
    }
}
