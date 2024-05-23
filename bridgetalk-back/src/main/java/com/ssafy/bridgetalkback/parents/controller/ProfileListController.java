package com.ssafy.bridgetalkback.parents.controller;

import com.ssafy.bridgetalkback.global.annotation.ExtractPayload;
import com.ssafy.bridgetalkback.parents.dto.response.ProfileListResponseDto;
import com.ssafy.bridgetalkback.parents.service.ProfileListService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@Tag(name = "ProfileList", description = "ProfileListController")@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileListController {
    private final ProfileListService profileListService;

    @GetMapping
    public ResponseEntity<ProfileListResponseDto> profileList(@ExtractPayload String userId) {
        log.info("{ ProfileListController } : 프로필리스트 조회 진입");
        return ResponseEntity.ok(profileListService.profileList(UUID.fromString(userId)));
    }
}
