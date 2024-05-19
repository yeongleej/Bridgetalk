package com.ssafy.bridgetalkback.comments.dto.response;

import com.ssafy.bridgetalkback.comments.query.dto.CommentsListDto;

import java.util.List;

public record CommentsListResponseDto(
        List<CommentsListDto> reportsCommentsList
) {
}
