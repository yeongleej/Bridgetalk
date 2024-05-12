package com.ssafy.bridgetalkback.parentingInfo.query;

import com.ssafy.bridgetalkback.parentingInfo.dto.response.CustomParentingInfoListResponseDto;
import com.ssafy.bridgetalkback.parentingInfo.query.dto.ParentingInfoListDto;

public interface ParentingInfoQueryRepository {
    CustomParentingInfoListResponseDto<ParentingInfoListDto> getParentingInfoListOrderById(int page, String category);
}
