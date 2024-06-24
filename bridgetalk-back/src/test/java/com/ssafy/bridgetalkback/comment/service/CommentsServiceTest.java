package com.ssafy.bridgetalkback.comment.service;

import com.ssafy.bridgetalkback.boards.domain.Boards;
import com.ssafy.bridgetalkback.boards.service.BoardsService;
import com.ssafy.bridgetalkback.comments.domain.Comments;
import com.ssafy.bridgetalkback.comments.dto.request.CommentsRequestDto;
import com.ssafy.bridgetalkback.comments.dto.request.CommentsUpdateRequestDto;
import com.ssafy.bridgetalkback.comments.dto.response.CommentsResponseDto;
import com.ssafy.bridgetalkback.comments.exception.CommentsErrorCode;
import com.ssafy.bridgetalkback.comments.service.CommentsFindService;
import com.ssafy.bridgetalkback.comments.service.CommentsService;
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

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static com.ssafy.bridgetalkback.fixture.BoardsFixture.BOARDS_01;
import static com.ssafy.bridgetalkback.fixture.CommentsFixture.COMMENTS_01;
import static com.ssafy.bridgetalkback.fixture.KidsFixture.JIYEONG;
import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SUNKYOUNG;
import static com.ssafy.bridgetalkback.fixture.ReportsFixture.REPORTS_01;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Comments [Service Layer] -> CommentsServiceTest 테스트")
public class CommentsServiceTest extends ServiceTest {
    private Parents parents;

    private Kids kids;

    private Reports reports;

    private Boards boards;

    @Autowired
    private ReportsUpdateService reportsUpdateService;

    @Autowired
    private CommentsFindService commentsFindService;

    @Autowired
    private CommentsService commentsService;

    @BeforeEach
    void setup() throws ExecutionException, InterruptedException {
        parents = parentsRepository.save(SUNKYOUNG.toParents());
        kids = kidsRepository.save(JIYEONG.toKids(parents));
        reports = reportsRepository.save(REPORTS_01.toReports(kids));
        reportsUpdateService.createReportAsync(reports.getReportsId(), kids.getUuid().toString());
        boards = boardsRepository.save(BOARDS_01.toBoards(reports, parents));
    }

    @Test
    @DisplayName("(한국어) 답변 등록에 성공한다.")
    void createCommentsKor() {
        // when
        CommentsResponseDto commentsResponseDto = commentsService.createComments(parents.getUuid(), createCommentsRequestDto(Language.kor));
        Long commentsId = commentsResponseDto.commentsId();

        // then
        Comments findComments = commentsFindService.findByCommentsIdAndIsDeleted(commentsId);

        assertAll(
                () -> assertThat(findComments.getCommentsId()).isEqualTo(commentsId),
                () -> assertThat(findComments.getCommentsContentKor()).isEqualTo(COMMENTS_01.getCommentsContentKor()),
                () -> assertThat(findComments.getCommentsContentViet()).isNotNull(),
                () -> assertThat(findComments.getCommentsContentPh()).isNotNull(),
                () -> assertThat(findComments.getIsDeleted()).isEqualTo(0)
        );
    }

    @Test
    @DisplayName("(베트남어) 답변 등록에 성공한다.")
    void createCommentsViet() {
        // when
        CommentsResponseDto commentsResponseDto = commentsService.createComments(parents.getUuid(), createCommentsRequestDto(Language.viet));
        Long commentsId = commentsResponseDto.commentsId();

        // then
        Comments findComments = commentsFindService.findByCommentsIdAndIsDeleted(commentsId);

        assertAll(
                () -> assertThat(findComments.getCommentsId()).isEqualTo(commentsId),
                () -> assertThat(findComments.getCommentsContentKor()).isNotNull(),
                () -> assertThat(findComments.getCommentsContentViet()).isEqualTo(COMMENTS_01.getCommentsContentViet()),
                () -> assertThat(findComments.getCommentsContentPh()).isNotNull(),
                () -> assertThat(findComments.getIsDeleted()).isEqualTo(0)
        );
    }

    @Test
    @DisplayName("(필리핀어) 답변 등록에 성공한다.")
    void createCommentsPh() {
        // when
        CommentsResponseDto commentsResponseDto = commentsService.createComments(parents.getUuid(), createCommentsRequestDto(Language.ph));
        Long commentsId = commentsResponseDto.commentsId();

        // then
        Comments findComments = commentsFindService.findByCommentsIdAndIsDeleted(commentsId);

        assertAll(
                () -> assertThat(findComments.getCommentsId()).isEqualTo(commentsId),
                () -> assertThat(findComments.getCommentsContentKor()).isNotNull(),
                () -> assertThat(findComments.getCommentsContentViet()).isNotNull(),
                () -> assertThat(findComments.getCommentsContentPh()).isEqualTo(COMMENTS_01.getCommentsContentPh()),
                () -> assertThat(findComments.getIsDeleted()).isEqualTo(0)
        );
    }

    @Test
    @DisplayName("(한국어) 답변 수정에 성공한다.")
    void updateCommentsKor() {
        // given
        CommentsResponseDto commentsResponseDto = commentsService.createComments(parents.getUuid(), createCommentsRequestDto(Language.kor));
        Long commentsId = commentsResponseDto.commentsId();

        // when
        CommentsResponseDto updateCommentsResponseDto = commentsService.updateComments(parents.getUuid(), commentsId, updateCommentsRequestDto(Language.kor));
        Long updateCommentsId = updateCommentsResponseDto.commentsId();

        // then
        Comments findComments = commentsFindService.findByCommentsIdAndIsDeleted(updateCommentsId);
        assertAll(
                () -> assertThat(findComments.getCommentsId()).isEqualTo(updateCommentsId),
                () -> assertThat(findComments.getCommentsContentKor()).isEqualTo(COMMENTS_01.getCommentsContentKor()),
                () -> assertThat(findComments.getCommentsContentViet()).isNotNull(),
                () -> assertThat(findComments.getCommentsContentPh()).isNotNull(),
                () -> assertThat(findComments.getIsDeleted()).isEqualTo(0)
        );
    }
    @Test
    @DisplayName("(베트남어) 답변 수정에 성공한다.")
    void updateCommentsViet() {
        // given
        CommentsResponseDto commentsResponseDto = commentsService.createComments(parents.getUuid(), createCommentsRequestDto(Language.viet));
        Long commentsId = commentsResponseDto.commentsId();

        // when
        CommentsResponseDto updateCommentsResponseDto = commentsService.updateComments(parents.getUuid(), commentsId, updateCommentsRequestDto(Language.viet));
        Long updateCommentsId = updateCommentsResponseDto.commentsId();

        // then
        Comments findComments = commentsFindService.findByCommentsIdAndIsDeleted(updateCommentsId);
        assertAll(
                () -> assertThat(findComments.getCommentsId()).isEqualTo(updateCommentsId),
                () -> assertThat(findComments.getCommentsContentKor()).isNotNull(),
                () -> assertThat(findComments.getCommentsContentViet()).isEqualTo(COMMENTS_01.getCommentsContentViet()),
                () -> assertThat(findComments.getCommentsContentPh()).isNotNull(),
                () -> assertThat(findComments.getIsDeleted()).isEqualTo(0)
        );
    }

    @Test
    @DisplayName("(필리핀어) 답변 수정에 성공한다.")
    void updateCommentsPh() {
        // given
        CommentsResponseDto commentsResponseDto = commentsService.createComments(parents.getUuid(), createCommentsRequestDto(Language.ph));
        Long commentsId = commentsResponseDto.commentsId();

        // when
        CommentsResponseDto updateCommentsResponseDto = commentsService.updateComments(parents.getUuid(), commentsId, updateCommentsRequestDto(Language.ph));
        Long updateCommentsId = updateCommentsResponseDto.commentsId();

        // then
        Comments findComments = commentsFindService.findByCommentsIdAndIsDeleted(updateCommentsId);
        assertAll(
                () -> assertThat(findComments.getCommentsId()).isEqualTo(updateCommentsId),
                () -> assertThat(findComments.getCommentsContentKor()).isNotNull(),
                () -> assertThat(findComments.getCommentsContentViet()).isNotNull(),
                () -> assertThat(findComments.getCommentsContentPh()).isEqualTo(COMMENTS_01.getCommentsContentPh()),
                () -> assertThat(findComments.getIsDeleted()).isEqualTo(0)
        );
    }

    @Test
    @DisplayName("게시글 삭제에 성공한다.")
    void deleteBoards(){
        // given
        CommentsResponseDto commentsResponseDto= commentsService.createComments(parents.getUuid(), createCommentsRequestDto(Language.viet));
        Long commentsId = commentsResponseDto.commentsId();

        // when
        commentsService.deleteComments(parents.getUuid(), commentsId);

        //then
        assertThatThrownBy(()-> commentsFindService.findByCommentsIdAndIsDeleted(commentsId))
                .isInstanceOf(BaseException.class)
                .hasMessage(CommentsErrorCode.COMMENTS_NOT_FOUND.getMessage());

        Optional<Comments> findComments = commentsRepository.findById(commentsId);
        assertAll(
                ()->assertThat(findComments.orElseThrow().getIsDeleted()).isEqualTo(1)
        );
    }

    private CommentsUpdateRequestDto updateCommentsRequestDto(Language language) {
        CommentsUpdateRequestDto requestDto = null;
        switch (language) {
            case kor -> requestDto = new CommentsUpdateRequestDto(COMMENTS_01.getCommentsContentKor(), language);
            case viet -> requestDto = new CommentsUpdateRequestDto(COMMENTS_01.getCommentsContentViet(), language);
            case ph -> requestDto = new CommentsUpdateRequestDto(COMMENTS_01.getCommentsContentPh(), language);
        }
        return requestDto;
    }

    private CommentsRequestDto createCommentsRequestDto(Language language) {
        CommentsRequestDto requestDto = null;
        switch (language) {
            case kor -> requestDto = new CommentsRequestDto(1L, COMMENTS_01.getCommentsContentKor(), language);
            case viet -> requestDto = new CommentsRequestDto(1L, COMMENTS_01.getCommentsContentViet(), language);
            case ph -> requestDto = new CommentsRequestDto(1L, COMMENTS_01.getCommentsContentPh(), language);
        }
        return requestDto;
    }
}
