package com.ssafy.bridgetalkback.comments.dto.request;


import com.ssafy.bridgetalkback.global.Language;
import jakarta.validation.constraints.NotBlank;

public record CommentsUpdateRequestDto(
        @NotBlank(message = "게시글 내용은 필수입니다.")
        String commentsContent,
        Language language

) {
}
