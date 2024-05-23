package com.ssafy.bridgetalkback.reports.dto.response;

import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.reports.domain.Reports;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ReportsListResponseDto(

        Long reportsId,
        String reportsSummary,
        List<String> reportsKeywords,
        LocalDateTime createdAt
) {
    public static ReportsListResponseDto fromReports(Reports report, Language language) {
        if (language.equals(Language.viet)){
            return ReportsListResponseDto.builder()
                    .reportsId(report.getReportsId())
                    .reportsSummary(report.getReportsSummaryViet())
                    .reportsKeywords(report.getReportsKeywordsViet())
                    .createdAt(report.getCreatedAt())
                    .build();
        } else if (language.equals(Language.ph)){
            return ReportsListResponseDto.builder()
                    .reportsId(report.getReportsId())
                    .reportsSummary(report.getReportsSummaryPh())
                    .reportsKeywords(report.getReportsKeywordsPh())
                    .createdAt(report.getCreatedAt())
                    .build();
        }
        return ReportsListResponseDto.builder()
                .reportsId(report.getReportsId())
                .reportsSummary(report.getReportsSummaryKor())
                .reportsKeywords(report.getReportsKeywordsKor())
                .createdAt(report.getCreatedAt())
                .build();
    }
}
