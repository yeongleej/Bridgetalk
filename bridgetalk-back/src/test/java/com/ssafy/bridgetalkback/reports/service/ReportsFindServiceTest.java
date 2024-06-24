package com.ssafy.bridgetalkback.reports.service;

import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.reports.domain.Reports;
import com.ssafy.bridgetalkback.reports.exception.ReportsErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static com.ssafy.bridgetalkback.fixture.KidsFixture.JIYEONG;
import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SUNKYOUNG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Reports [Service Layer] -> ReportsFindService 테스트")
public class ReportsFindServiceTest extends ServiceTest {

    private Parents parents;
    private Kids kids;
    private Reports reports;

    @Autowired
    private  ReportsFindService reportsFindService;

    @BeforeEach
    void setup() {
        parents = parentsRepository.save(SUNKYOUNG.toParents());
        kids = kidsRepository.save(JIYEONG.toKids(parents));
        String originText = "디노야, 오늘은 정말 놀이동산에 가고 싶어! 아침부터 엄마에게 계속해서 말했는데, 엄마는 항상 바쁘다며 내 얘기를 잘 못 알아들어줘. 나는 놀이동산에서 내가 좋아하는 모든 놀이기구를 타고 싶고, 큰 풍선도 사고 싶고, 푸른 하늘을 날고 싶어! 미끄럼틀에서 빠르게 내려가면서 내 친구들과 함께 즐거운 시간을 보내고 싶어! 그리고 맛있는 아이스크림도 먹고 싶어! 엄마야, 내가 놀이동산에 가고 싶은 이유를 알아줘! 나는 너무나도 설레고 기대돼. 그냥 빨리 놀이동산에 가고 싶어서 말이야!";
        reports = reportsRepository.save(Reports.createReports(kids, originText));
    }

    @Test
    @DisplayName("ID(PK)로 레포트를 조회한다")
    void findByReportsIdAndIsDeletedIsFalse() {
        // when
        Reports findReports = reportsFindService.findByReportsIdAndIsDeleted(reports.getReportsId());
        Long inVaildReportsId = -1L;

        // then
        assertThatThrownBy(() -> reportsFindService.findByReportsIdAndIsDeleted(inVaildReportsId))
                .isInstanceOf(BaseException.class)
                .hasMessage(ReportsErrorCode.REPORTS_NOT_FOUND.getMessage());

        assertThat(findReports).isEqualTo(reports);
    }

}
