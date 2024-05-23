package com.ssafy.bridgetalkback.boards.dto.request;


import com.ssafy.bridgetalkback.global.Language;
import jakarta.validation.constraints.NotBlank;

public record BoardsRequestDto (
        Long reportsId,
        @NotBlank(message = "게시글 제목은 필수입니다.")
        String boardsTitle,
        @NotBlank(message = "게시글 내용은 필수입니다.")
        String boardsContent,
        Language language
) {
}
