package com.ssafy.bridgetalkback.parentingInfo.dto.response;

import org.springframework.data.domain.Page;

import java.util.List;

public record CustomParentingInfoListResponseDto<T>(
        CustomPageable pageInfo, // pageable
        List<T> parentingInfoList // content
) {
    public CustomParentingInfoListResponseDto(Page<T> page) {
        this(
                new CustomPageable(
                        page.getTotalPages(),
                        page.getTotalElements(),
                        page.hasNext(),
                        page.getNumberOfElements()
                ),
                page.getContent()
        );
    }

    public record CustomPageable (
            long totalPages,
            long totalElements,
            boolean hasNext,
            long numberOfElements
    ) {
    }
}
