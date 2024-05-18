package com.ssafy.bridgetalkback.parentingInfo.dto;

import com.ssafy.bridgetalkback.parentingInfo.domain.Category;

public record ParentingInfoCrawlingDto(
        String titleKor,
        String titleViet,
        String titlePh,
        String contentKor,
        String contentViet,
        String contentPh,
        String link,
        Category category
) {
}
