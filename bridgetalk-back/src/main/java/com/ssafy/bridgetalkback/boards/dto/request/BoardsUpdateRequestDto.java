package com.ssafy.bridgetalkback.boards.dto.request;

import com.ssafy.bridgetalkback.reports.domain.Language;

public record BoardsUpdateRequestDto(
        String boardsTitle,
        String boardsContent,
        Language language
) {
}
