package com.ssafy.bridgetalkback.comments.dto.request;

import com.ssafy.bridgetalkback.reports.domain.Language;

public record CommentsRequestDto(
        Long boardsId,
        String commentsContent,
        Language language
) {
}
