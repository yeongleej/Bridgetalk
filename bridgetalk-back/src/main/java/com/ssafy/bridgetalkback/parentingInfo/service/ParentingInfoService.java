package com.ssafy.bridgetalkback.parentingInfo.service;

import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.parentingInfo.domain.Category;
import com.ssafy.bridgetalkback.parentingInfo.domain.ParentingInfo;
import com.ssafy.bridgetalkback.parentingInfo.dto.response.ParentingInfoResponseDto;
import com.ssafy.bridgetalkback.parentingInfo.repository.ParentingInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ParentingInfoService {

    private final ParentingInfoRepository parentingInfoRepository;
    private final ParentingInfoFindService parentingInfoFindService;

    @Transactional
    public Long createParentingInfo(String titleKor, String titleViet, String titlePh, String contentKor, String contentViet,
                           String contentPh, String link, Category category) {
        log.info("{ ParentingInfoService } : 육아정보 등록 진입");

        ParentingInfo parentingInfo = ParentingInfo.createParentingInfo(titleKor, titleViet, titlePh, contentKor,
                contentViet, contentPh, link, category);
        ParentingInfo savedParentingInfo = parentingInfoRepository.save(parentingInfo);
        log.info("{ ParentingInfoService } : 육아정보 등록 성공");
        return savedParentingInfo.getParentingInfoId();
    }

    public ParentingInfoResponseDto getParentingInfoDetail(Long parentingInfoId, Language language) {
        log.info("{ ParentingInfoService } : 육아정보 상세조회 진입");
        ParentingInfo parentingInfo = parentingInfoFindService.findParentingInfoByParentingInfoIdAndIsDeleted(parentingInfoId);

        ParentingInfoResponseDto responseDto = ParentingInfoResponseDto.from(parentingInfo, language);
        log.info("{ ParentingInfoService } : 육아정보 상세조회 성공");
        return responseDto;
    }
}
