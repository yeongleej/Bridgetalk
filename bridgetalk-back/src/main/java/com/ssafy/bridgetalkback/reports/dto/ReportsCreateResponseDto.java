package com.ssafy.bridgetalkback.reports.dto;

import com.ssafy.bridgetalkback.reports.domain.Reports;
import lombok.Builder;

@Builder
public record ReportsCreateResponseDto(Long reportsId) {

    public static ReportsCreateResponseDto fromReportsId(Reports reports) {
        return ReportsCreateResponseDto.builder()
                .reportsId(reports.getReportsId())
                .build();
    }


}

