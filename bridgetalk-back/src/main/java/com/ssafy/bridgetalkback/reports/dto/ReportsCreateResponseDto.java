package com.ssafy.bridgetalkback.reports.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReportsCreateResponseDto {

    private final Long reportsId;

    @Builder
    public ReportsCreateResponseDto(Long reportsId) {
        this.reportsId = reportsId;
    }

}
