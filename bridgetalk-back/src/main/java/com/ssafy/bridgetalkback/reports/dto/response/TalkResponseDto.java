package com.ssafy.bridgetalkback.reports.dto.response;

import lombok.Builder;
import org.springframework.core.io.Resource;

@Builder
public record TalkResponseDto(
        String subtitles,
        String emotion
) {
}
