package com.ssafy.bridgetalkback.reports.dto.response;

import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.reports.domain.Reports;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ReportsDetailResponseDto(

        Long reportsId,
        String reportsSummary,
        List<String> reportsKeywords,
        String reportsSolution,
        List<VideoResponseDto> reportsVideoList,
        LocalDateTime createdAt
) {
    public static ReportsDetailResponseDto fromReports(Reports report, List<VideoResponseDto> reportsVideoList, Language language) {
        if (language.equals(Language.viet)) {
            return ReportsDetailResponseDto.builder()
                    .reportsId(report.getReportsId())
                    .reportsSummary(report.getReportsSummaryViet())
                    .reportsKeywords(report.getReportsKeywordsViet())
                    .reportsSolution(report.getReportsSolutionViet())
                    .reportsVideoList(reportsVideoList)
                    .createdAt(report.getCreatedAt())
                    .build();
        } else if (language.equals(Language.ph)) {
            return ReportsDetailResponseDto.builder()
                    .reportsId(report.getReportsId())
                    .reportsSummary(report.getReportsSummaryPh())
                    .reportsKeywords(report.getReportsKeywordsPh())
                    .reportsSolution(report.getReportsSolutionPh())
                    .reportsVideoList(reportsVideoList)
                    .createdAt(report.getCreatedAt())
                    .build();
        }
        return ReportsDetailResponseDto.builder()
                .reportsId(report.getReportsId())
                .reportsSummary(report.getReportsSummaryKor())
                .reportsKeywords(report.getReportsKeywordsKor())
                .reportsSolution(report.getReportsSolutionKor())
                .reportsVideoList(reportsVideoList)
                .createdAt(report.getCreatedAt())
                .build();
    }
}
