package com.ssafy.bridgetalkback.boards.dto.request;


import com.ssafy.bridgetalkback.global.Language;

public record BoardsUpdateRequestDto(
        String boardsTitle,
        String boardsContent,
        Language language
) {
}
