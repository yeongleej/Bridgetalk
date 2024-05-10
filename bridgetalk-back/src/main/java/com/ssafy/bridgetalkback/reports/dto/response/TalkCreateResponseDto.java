package com.ssafy.bridgetalkback.reports.dto.response;

import lombok.Builder;

@Builder
public record TalkCreateResponseDto(
        String userEmail
) {
}
