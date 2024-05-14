package com.ssafy.bridgetalkback.boards.query.dto;

import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;
import java.util.List;

public record BoardsListDto(
        Long boardId,
        String boardsTitle,
        String boardsContent,
        int likes,
        LocalDateTime createdAt,
        String reportsSummary,
        List<String> reportsKeywords,
        String writer
) {
    @QueryProjection
    public BoardsListDto {

    }
}
