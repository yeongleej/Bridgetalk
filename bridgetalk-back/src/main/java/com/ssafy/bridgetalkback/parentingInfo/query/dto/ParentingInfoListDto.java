package com.ssafy.bridgetalkback.parentingInfo.query.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;

@Builder
public record ParentingInfoListDto(
        Long parentingInfoId,
        String title,
        String content,
        String link,
        String category
) {
    @QueryProjection
    public ParentingInfoListDto {

    }
}
