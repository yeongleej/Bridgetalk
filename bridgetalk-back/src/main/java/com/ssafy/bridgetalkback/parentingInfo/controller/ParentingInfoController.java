package com.ssafy.bridgetalkback.parentingInfo.controller;

import com.ssafy.bridgetalkback.global.annotation.ExtractPayload;
import com.ssafy.bridgetalkback.parentingInfo.dto.response.CustomParentingInfoListResponseDto;
import com.ssafy.bridgetalkback.parentingInfo.dto.response.ParentingInfoResponseDto;
import com.ssafy.bridgetalkback.parentingInfo.query.dto.ParentingInfoListDto;
import com.ssafy.bridgetalkback.parentingInfo.service.ParentingInfoListService;
import com.ssafy.bridgetalkback.parentingInfo.service.ParentingInfoService;
import com.ssafy.bridgetalkback.global.Language;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "ParentingInfo", description = "ParentingInfoController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parentingInfo")
public class ParentingInfoController {
    private final ParentingInfoService parentingInfoService;
    private final ParentingInfoListService parentingInfoListService;

    @GetMapping("/{parentingInfoId}/{language}")
    public ResponseEntity<ParentingInfoResponseDto> getParentingInfoDetail(@ExtractPayload String userId, @PathVariable Long parentingInfoId, @PathVariable Language language) {
        log.info("{ ParentingInfoController } : 육아정보 상세조회 진입");
        return ResponseEntity.ok(parentingInfoService.getParentingInfoDetail(parentingInfoId, language));
    }

    @GetMapping("/{language}")
    public ResponseEntity<CustomParentingInfoListResponseDto<ParentingInfoListDto>> getCustomParentingInfoList(@ExtractPayload String userId, @PathVariable Language language,
                                                                                                      @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                                                                      @RequestParam(value = "searchCategory", required = false, defaultValue = "prospective") String searchCategory) {
        log.info("{ ParentingInfoController } : 육아정보 카테고리별 리스트조회 진입 - category :"+searchCategory);
        return ResponseEntity.ok(parentingInfoListService.getCustomParentingInfoList(page, searchCategory, language));
    }
}
