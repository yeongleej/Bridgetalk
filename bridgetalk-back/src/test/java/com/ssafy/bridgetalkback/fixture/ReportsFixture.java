package com.ssafy.bridgetalkback.fixture;

import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.reports.domain.Reports;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReportsFixture {
    REPORTS_01("오늘 모둠활동 시간에 내가 그린 그림을 친구가 망쳤어"),
    REPORTS_02("content2"),
    REPORTS_03("content3"),
    REPORTS_04("content4"),
    REPORTS_05("content5"),
    REPORTS_06("content6"),
    REPORTS_07("content7"),
    REPORTS_08("content8"),
    REPORTS_09("content9"),
    REPORTS_10("content10"),
    REPORTS_11("content11"),
    REPORTS_12("content12")
    ;

    private final String reportsOriginContent;

    public Reports toReports(Kids kids) {
        return Reports.createReports(kids, reportsOriginContent);
    }
}