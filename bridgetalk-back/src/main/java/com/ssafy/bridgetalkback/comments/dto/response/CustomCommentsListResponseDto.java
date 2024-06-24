package com.ssafy.bridgetalkback.comments.dto.response;

import org.springframework.data.domain.Page;

import java.util.List;

public record CustomCommentsListResponseDto<T>(
        CustomCommentsListResponseDto.CustomPageable pageInfo,// pageable
        List<T> commentsList // content
) {
    public CustomCommentsListResponseDto(Page<T> page) {
        this(
                new CustomCommentsListResponseDto.CustomPageable(
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

