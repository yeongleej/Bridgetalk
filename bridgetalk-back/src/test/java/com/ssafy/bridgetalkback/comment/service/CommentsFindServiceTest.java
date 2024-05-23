package com.ssafy.bridgetalkback.comment.service;

import com.ssafy.bridgetalkback.boards.domain.Boards;
import com.ssafy.bridgetalkback.comments.domain.Comments;
import com.ssafy.bridgetalkback.comments.exception.CommentsErrorCode;
import com.ssafy.bridgetalkback.comments.service.CommentsFindService;
import com.ssafy.bridgetalkback.common.ServiceTest;
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
import static com.ssafy.bridgetalkback.fixture.CommentsFixture.COMMENTS_01;
import static com.ssafy.bridgetalkback.fixture.KidsFixture.JIYEONG;
import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SUNKYOUNG;
import static com.ssafy.bridgetalkback.fixture.ReportsFixture.REPORTS_01;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Comments [Service Layer] -> CommentsFindServiceTest 테스트")
public class CommentsFindServiceTest extends ServiceTest {

    private Parents parents;

    private Kids kids;

    private Reports reports;

    private Boards boards;

    private Comments comments;

    @Autowired
    private ReportsUpdateService reportsUpdateService;

    @Autowired
    private CommentsFindService commentsFindService;

    @BeforeEach
    void setup() throws ExecutionException, InterruptedException {
        parents = parentsRepository.save(SUNKYOUNG.toParents());
        kids = kidsRepository.save(JIYEONG.toKids(parents));
        reports = reportsRepository.save(REPORTS_01.toReports(kids));
        reportsUpdateService.createReportAsync(reports.getReportsId(), kids.getUuid().toString());
        boards = boardsRepository.save(BOARDS_01.toBoards(reports, parents));
        comments = commentsRepository.save(COMMENTS_01.toComments(parents, boards));
    }

    @Test
    @DisplayName("ID(PK)로 답변을 조회한다.")
    void findByCommentsIdAndIsDeletedIsFalse() {
        // when
        Comments findComments = commentsFindService.findByCommentsIdAndIsDeleted(comments.getCommentsId());
        Long inVaildCommentsId = -1L;

        // then
        assertThatThrownBy(() -> commentsFindService.findByCommentsIdAndIsDeleted(inVaildCommentsId))
                .isInstanceOf(BaseException.class)
                .hasMessage(CommentsErrorCode.COMMENTS_NOT_FOUND.getMessage());

        assertThat(findComments).isEqualTo(comments);
    }

}
