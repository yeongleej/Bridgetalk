package com.ssafy.bridgetalkback.comments.dto.request;

import com.ssafy.bridgetalkback.reports.domain.Language;

public record CommentsUpdateRequestDto(
        String commentsContent,
        Language language

) {
}
