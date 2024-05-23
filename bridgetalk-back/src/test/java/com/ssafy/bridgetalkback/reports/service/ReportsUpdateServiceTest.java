package com.ssafy.bridgetalkback.reports.service;

import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.reports.domain.Reports;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutionException;

import static com.ssafy.bridgetalkback.fixture.KidsFixture.JIYEONG;
import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SUNKYOUNG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Reports [Service Layer] -> ReportsUpdateServiceTest 테스트")
public class ReportsUpdateServiceTest extends ServiceTest {

    private Parents parents;
    private Kids kids;
    private Reports reports;

    @Autowired
    private ReportsUpdateService reportsService;

    @Autowired
    private ReportsFindService reportsFindService;

    @BeforeEach
    void setup() {
        parents = parentsRepository.save(SUNKYOUNG.toParents());
        kids = kidsRepository.save(JIYEONG.toKids(parents));
        String originText = "디노야, 오늘은 정말 놀이동산에 가고 싶어! 아침부터 엄마에게 계속해서 말했는데, 엄마는 항상 바쁘다며 내 얘기를 잘 못 알아들어줘. 나는 놀이동산에서 내가 좋아하는 모든 놀이기구를 타고 싶고, 큰 풍선도 사고 싶고, 푸른 하늘을 날고 싶어! 미끄럼틀에서 빠르게 내려가면서 내 친구들과 함께 즐거운 시간을 보내고 싶어! 그리고 맛있는 아이스크림도 먹고 싶어! 엄마야, 내가 놀이동산에 가고 싶은 이유를 알아줘! 나는 너무나도 설레고 기대돼. 그냥 빨리 놀이동산에 가고 싶어서 말이야!";
        reports = reportsRepository.save(Reports.createReports(kids, originText));
    }

    @Test
    @DisplayName("아이 속마음 레포트 저장에 성공한다.")
    void updateReports() {
        //given
        Long reportsId = reports.getReportsId();

        //when
        reportsService.createReport(reportsId);
        Reports newReports = reportsFindService.findByReportsIdAndIsDeleted(reportsId);

        //then
        assertAll(
                () -> assertThat(newReports.getReportsId()).isEqualTo(reports.getReportsId()),
                () -> assertThat(newReports.getKids()).isEqualTo(reports.getKids()),
                () -> assertThat(newReports.getReportsOriginContent()).isEqualTo(reports.getReportsOriginContent()),
                () -> assertThat(newReports.getReportsSummaryKor()).isEqualTo(reports.getReportsSummaryKor()),
                () -> assertThat(newReports.getReportsSummaryViet()).isEqualTo(reports.getReportsSummaryViet()),
                () -> assertThat(newReports.getReportsSummaryPh()).isEqualTo(reports.getReportsSummaryPh()),
                () -> assertThat(newReports.getReportsKeywordsKor()).isEqualTo(reports.getReportsKeywordsKor()),
                () -> assertThat(newReports.getReportsKeywordsViet()).isEqualTo(reports.getReportsKeywordsViet()),
                () -> assertThat(newReports.getReportsKeywordsPh()).isEqualTo(reports.getReportsKeywordsPh()),
                () -> assertThat(newReports.getReportsSolutionKor()).isEqualTo(reports.getReportsSolutionKor()),
                () -> assertThat(newReports.getReportsSolutionViet()).isEqualTo(reports.getReportsSolutionViet()),
                () -> assertThat(newReports.getReportsSolutionPh()).isEqualTo(reports.getReportsSolutionPh())
        );
    }

    @Test
    @DisplayName("(비동기) 아이 속마음 레포트 저장에 성공한다.")
    void updateReportsAsync() throws ExecutionException, InterruptedException {
        //given
        Long reportsId = reports.getReportsId();

        //when
        reportsService.createReportAsync(reportsId, kids.getUuid().toString());
        Reports newReports = reportsFindService.findByReportsIdAndIsDeleted(reportsId);

        //then
        assertAll(
                () -> assertThat(newReports.getReportsId()).isEqualTo(reports.getReportsId()),
                () -> assertThat(newReports.getKids()).isEqualTo(reports.getKids()),
                () -> assertThat(newReports.getReportsOriginContent()).isEqualTo(reports.getReportsOriginContent()),
                () -> assertThat(newReports.getReportsSummaryKor()).isEqualTo(reports.getReportsSummaryKor()),
                () -> assertThat(newReports.getReportsSummaryViet()).isEqualTo(reports.getReportsSummaryViet()),
                () -> assertThat(newReports.getReportsSummaryPh()).isEqualTo(reports.getReportsSummaryPh()),
                () -> assertThat(newReports.getReportsKeywordsKor()).isEqualTo(reports.getReportsKeywordsKor()),
                () -> assertThat(newReports.getReportsKeywordsViet()).isEqualTo(reports.getReportsKeywordsViet()),
                () -> assertThat(newReports.getReportsKeywordsPh()).isEqualTo(reports.getReportsKeywordsPh()),
                () -> assertThat(newReports.getReportsSolutionKor()).isEqualTo(reports.getReportsSolutionKor()),
                () -> assertThat(newReports.getReportsSolutionViet()).isEqualTo(reports.getReportsSolutionViet()),
                () -> assertThat(newReports.getReportsSolutionPh()).isEqualTo(reports.getReportsSolutionPh())
        );
    }

    @Test
    @DisplayName("(비동기) 아이 속마음 레포트 저장에 성공한다.")
    void updateReportsAsync2() throws ExecutionException, InterruptedException {
        //given
        Long reportsId = reports.getReportsId();

        //when
        reportsService.createReportAsync2(reportsId, kids.getUuid().toString());
        Reports newReports = reportsFindService.findByReportsIdAndIsDeleted(reportsId);

        //then
        assertAll(
                () -> assertThat(newReports.getReportsId()).isEqualTo(reports.getReportsId()),
                () -> assertThat(newReports.getKids()).isEqualTo(reports.getKids()),
                () -> assertThat(newReports.getReportsOriginContent()).isEqualTo(reports.getReportsOriginContent()),
                () -> assertThat(newReports.getReportsSummaryKor()).isEqualTo(reports.getReportsSummaryKor()),
                () -> assertThat(newReports.getReportsSummaryViet()).isEqualTo(reports.getReportsSummaryViet()),
                () -> assertThat(newReports.getReportsSummaryPh()).isEqualTo(reports.getReportsSummaryPh()),
                () -> assertThat(newReports.getReportsKeywordsKor()).isEqualTo(reports.getReportsKeywordsKor()),
                () -> assertThat(newReports.getReportsKeywordsViet()).isEqualTo(reports.getReportsKeywordsViet()),
                () -> assertThat(newReports.getReportsKeywordsPh()).isEqualTo(reports.getReportsKeywordsPh()),
                () -> assertThat(newReports.getReportsSolutionKor()).isEqualTo(reports.getReportsSolutionKor()),
                () -> assertThat(newReports.getReportsSolutionViet()).isEqualTo(reports.getReportsSolutionViet()),
                () -> assertThat(newReports.getReportsSolutionPh()).isEqualTo(reports.getReportsSolutionPh())
        );
    }
}