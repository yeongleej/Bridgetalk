package com.ssafy.bridgetalkback.reports.dto.response;

import lombok.Builder;

@Builder
public record TalkResponseDto(
        String subtitles,
        String emotion
) {
}
