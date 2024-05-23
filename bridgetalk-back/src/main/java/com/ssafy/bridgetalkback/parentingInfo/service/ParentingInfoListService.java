package com.ssafy.bridgetalkback.parentingInfo.service;

import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.parentingInfo.domain.Category;
import com.ssafy.bridgetalkback.parentingInfo.dto.response.CustomParentingInfoListResponseDto;
import com.ssafy.bridgetalkback.parentingInfo.query.dto.ParentingInfoListDto;
import com.ssafy.bridgetalkback.parentingInfo.repository.ParentingInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ParentingInfoListService {
    private final ParentingInfoRepository parentingInfoRepository;

    public CustomParentingInfoListResponseDto<ParentingInfoListDto> getCustomParentingInfoList(int page, String searchCategory, Language language) {
        log.info("{ ParentingInfoListService } : 육아정보 카테고리별 리스트조회 진입");

        Category category = Category.from(searchCategory);
        CustomParentingInfoListResponseDto<ParentingInfoListDto> parentingInfoList = parentingInfoRepository.getParentingInfoListOrderById(page, String.valueOf(category), language);

        log.info("{ ParentingInfoListService } : 육아정보 카테고리별 리스트조회 성공");
        return new CustomParentingInfoListResponseDto<>(parentingInfoList.pageInfo(), parentingInfoList.parentingInfoList());
    }
}
