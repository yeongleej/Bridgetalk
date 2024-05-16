package com.ssafy.bridgetalkback.comments.dto.request;


import com.ssafy.bridgetalkback.global.Language;

public record CommentsUpdateRequestDto(
        String commentsContent,
        Language language

) {
}
