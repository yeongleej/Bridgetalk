package com.ssafy.bridgetalkback.boards.dto.response;

import org.springframework.data.domain.Page;

import java.util.List;

public record CustomBoardsListResponseDto<T>(
        CustomPageable pageInfo,// pageable
        List<T> boardsList // content
) {
    public CustomBoardsListResponseDto(Page<T> page) {
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
