package com.ssafy.bridgetalkback.comments.query.dto;

import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

public record CommentsListDto(
        Long commentsId,
        String parentsNickname,
        String commentsContent,
        int likes,
        LocalDateTime createdAt
) {
    @QueryProjection
    public CommentsListDto {

    }
}
