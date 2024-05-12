package com.ssafy.bridgetalkback.parentingInfo.query.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;

@Builder
public record ParentingInfoListDto(
        Long parentingInfoId,
        String title_kor,
        String title_viet,
        String content_kor,
        String content_viet,
        String link,
        String category
) {
    @QueryProjection
    public ParentingInfoListDto {

    }
}
