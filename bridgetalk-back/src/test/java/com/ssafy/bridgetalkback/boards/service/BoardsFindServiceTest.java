package com.ssafy.bridgetalkback.boards.service;

import com.ssafy.bridgetalkback.boards.domain.Boards;
import com.ssafy.bridgetalkback.boards.exception.BoardsErrorCode;
import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.reports.domain.Reports;
import com.ssafy.bridgetalkback.reports.service.ReportsUpdateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutionException;

import static com.ssafy.bridgetalkback.fixture.BoardsFixture.BOARDS_01;
import static com.ssafy.bridgetalkback.fixture.KidsFixture.JIYEONG;
import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SUNKYOUNG;
import static com.ssafy.bridgetalkback.fixture.ReportsFixture.REPORTS_01;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Boards [Service Layer] -> BoardsFindServiceTest 테스트")
public class BoardsFindServiceTest extends ServiceTest {

    private Boards boards;

    @Autowired
    private ReportsUpdateService reportsUpdateService;

    @Autowired
    private BoardsFindService boardsFindService;


    @BeforeEach
    void setup() throws ExecutionException, InterruptedException {
        Parents parents = parentsRepository.save(SUNKYOUNG.toParents());
        Kids kids = kidsRepository.save(JIYEONG.toKids(parents));
        Reports reports = reportsRepository.save(REPORTS_01.toReports(kids));
        reportsUpdateService.createReportAsync(reports.getReportsId(), kids.getUuid().toString());
        boards = boardsRepository.save(BOARDS_01.toBoards(reports, parents));
    }


    @Test
    @DisplayName("ID(PK)로 게시글을 조회한다.")
    void findByBoardsIdAndIsDeletedIsFalse() {
        // when
        Boards findBoards = boardsFindService.findByBoardsIdAndIsDeleted(boards.getBoardsId());
        Long inValidBoardsId = -1L;

        // then
        assertThatThrownBy(() -> boardsFindService.findByBoardsIdAndIsDeleted(inValidBoardsId))
                .isInstanceOf(BaseException.class)
                .hasMessage(BoardsErrorCode.BOARDS_NOT_FOUND.getMessage());

        assertThat(findBoards).isEqualTo(boards);
    }
}
