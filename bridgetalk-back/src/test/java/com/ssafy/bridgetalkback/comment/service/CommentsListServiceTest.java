package com.ssafy.bridgetalkback.comment.service;

import com.ssafy.bridgetalkback.boards.domain.Boards;
import com.ssafy.bridgetalkback.boards.exception.BoardsErrorCode;
import com.ssafy.bridgetalkback.comments.domain.Comments;
import com.ssafy.bridgetalkback.comments.dto.response.CustomCommentsListResponseDto;
import com.ssafy.bridgetalkback.comments.query.dto.CommentsListDto;
import com.ssafy.bridgetalkback.comments.service.CommentsListService;
import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.reports.domain.Reports;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ssafy.bridgetalkback.fixture.BoardsFixture.BOARDS_01;
import static com.ssafy.bridgetalkback.fixture.CommentsFixture.*;
import static com.ssafy.bridgetalkback.fixture.KidsFixture.JIYEONG;
import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SUNKYOUNG;
import static com.ssafy.bridgetalkback.fixture.ReportsFixture.REPORTS_01;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Comments [Service Layer] -> CommentsListService 테스트")
public class CommentsListServiceTest extends ServiceTest {
    @Autowired
    private CommentsListService commentsListService;

    private Parents parents;
    private Kids kids;
    private Reports reports;
    private Boards boards;
    private Comments[] comments = new Comments[12];

    private static final int PAGE = 0;
    private static final int PAGE_SIZE = 10;
    private static final String SORT_CONDITION = "최신순";
    private static final String INVALID_SORT_CONDITION = "조회순";

    @BeforeEach
    void setup() {
        parents = parentsRepository.save(SUNKYOUNG.toParents());
        kids = kidsRepository.save(JIYEONG.toKids(parents));
        reports = reportsRepository.save(REPORTS_01.toReports(kids));
        boards = boardsRepository.save(BOARDS_01.toBoards(reports, parents));
        comments[0] = commentsRepository.save(COMMENTS_01.toComments(parents, boards));
        comments[1] = commentsRepository.save(COMMENTS_02.toComments(parents, boards));
        comments[2] = commentsRepository.save(COMMENTS_03.toComments(parents, boards));
        comments[3] = commentsRepository.save(COMMENTS_04.toComments(parents, boards));
        comments[4] = commentsRepository.save(COMMENTS_05.toComments(parents, boards));
        comments[5] = commentsRepository.save(COMMENTS_06.toComments(parents, boards));
        comments[6] = commentsRepository.save(COMMENTS_07.toComments(parents, boards));
        comments[7] = commentsRepository.save(COMMENTS_08.toComments(parents, boards));
        comments[8] = commentsRepository.save(COMMENTS_09.toComments(parents, boards));
        comments[9] = commentsRepository.save(COMMENTS_10.toComments(parents, boards));
        comments[10] = commentsRepository.save(COMMENTS_11.toComments(parents, boards));
        comments[11] = commentsRepository.save(COMMENTS_12.toComments(parents, boards));
    }

    @Nested
    @DisplayName("답글 리스트 조회")
    class customParentingInfoList {
        @Test
        @DisplayName("존재하지 않는 정렬 유형이라면 게시글 리스트 조회에 실패한다")
        void throwNotFoundSearchCondition() {
            // when - then
            assertThatThrownBy(() -> commentsListService.getCustomCommentsList(boards.getBoardsId(), PAGE, INVALID_SORT_CONDITION, Language.kor))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(BoardsErrorCode.SORT_CONDITION_NOT_FOUND.getMessage());
        }

        @Test
        @DisplayName("(한국어) 정렬 유형과 검색 조건에 따른 게시글 리스트 최신순 조회에 성공한다")
        void successKorean() {
            // when
            CustomCommentsListResponseDto<CommentsListDto> responseDto = commentsListService.getCustomCommentsList(boards.getBoardsId(), PAGE, SORT_CONDITION, Language.kor);

            // then
            assertThat(responseDto.commentsList().size()).isLessThanOrEqualTo(PAGE_SIZE);
            assertThat(responseDto.pageInfo().totalPages()).isLessThanOrEqualTo(PAGE_SIZE);

            assertAll(
                    () -> assertThat(responseDto.pageInfo().totalPages()).isEqualTo(2),
                    () -> assertThat(responseDto.pageInfo().totalElements()).isEqualTo(12),
                    () -> assertThat(responseDto.pageInfo().hasNext()).isTrue(),
                    () -> assertThat(responseDto.pageInfo().numberOfElements()).isEqualTo(PAGE_SIZE),
                    () -> assertThat(responseDto.commentsList().size()).isLessThanOrEqualTo(PAGE_SIZE),
                    () -> assertThat(responseDto.commentsList().get(0).commentsId()).isEqualTo(comments[11].getCommentsId()),
                    () -> assertThat(responseDto.commentsList().get(0).parentsNickname()).isEqualTo(comments[11].getParents().getParentsNickname()),
                    () -> assertThat(responseDto.commentsList().get(0).commentsContent()).isEqualTo(comments[11].getCommentsContentKor()),
                    () -> assertThat(responseDto.commentsList().get(0).likes()).isEqualTo(comments[11].getLikes()),
                    () -> assertThat(responseDto.commentsList().get(0).createdAt()).isNotNull()
            );
        }

        @Test
        @DisplayName("(베트남어) 정렬 유형과 검색 조건에 따른 게시글 리스트 최신순 조회에 성공한다")
        void successViet() {
            // when
            CustomCommentsListResponseDto<CommentsListDto> responseDto = commentsListService.getCustomCommentsList(boards.getBoardsId(), PAGE, SORT_CONDITION, Language.viet);

            // then
            assertThat(responseDto.commentsList().size()).isLessThanOrEqualTo(PAGE_SIZE);
            assertThat(responseDto.pageInfo().totalPages()).isLessThanOrEqualTo(PAGE_SIZE);

            assertAll(
                    () -> assertThat(responseDto.pageInfo().totalPages()).isEqualTo(2),
                    () -> assertThat(responseDto.pageInfo().totalElements()).isEqualTo(12),
                    () -> assertThat(responseDto.pageInfo().hasNext()).isTrue(),
                    () -> assertThat(responseDto.pageInfo().numberOfElements()).isEqualTo(PAGE_SIZE),
                    () -> assertThat(responseDto.commentsList().size()).isLessThanOrEqualTo(PAGE_SIZE),
                    () -> assertThat(responseDto.commentsList().get(0).commentsId()).isEqualTo(comments[11].getCommentsId()),
                    () -> assertThat(responseDto.commentsList().get(0).parentsNickname()).isEqualTo(comments[11].getParents().getParentsNickname()),
                    () -> assertThat(responseDto.commentsList().get(0).commentsContent()).isEqualTo(comments[11].getCommentsContentViet()),
                    () -> assertThat(responseDto.commentsList().get(0).likes()).isEqualTo(comments[11].getLikes()),
                    () -> assertThat(responseDto.commentsList().get(0).createdAt()).isNotNull()
            );
        }

        @Test
        @DisplayName("(필리핀어) 정렬 유형과 검색 조건에 따른 게시글 리스트 최신순 조회에 성공한다")
        void successPh() {
            // when
            CustomCommentsListResponseDto<CommentsListDto> responseDto = commentsListService.getCustomCommentsList(boards.getBoardsId(), PAGE, SORT_CONDITION, Language.ph);

            // then
            assertThat(responseDto.commentsList().size()).isLessThanOrEqualTo(PAGE_SIZE);
            assertThat(responseDto.pageInfo().totalPages()).isLessThanOrEqualTo(PAGE_SIZE);

            assertAll(
                    () -> assertThat(responseDto.pageInfo().totalPages()).isEqualTo(2),
                    () -> assertThat(responseDto.pageInfo().totalElements()).isEqualTo(12),
                    () -> assertThat(responseDto.pageInfo().hasNext()).isTrue(),
                    () -> assertThat(responseDto.pageInfo().numberOfElements()).isEqualTo(PAGE_SIZE),
                    () -> assertThat(responseDto.commentsList().size()).isLessThanOrEqualTo(PAGE_SIZE),
                    () -> assertThat(responseDto.commentsList().get(0).commentsId()).isEqualTo(comments[11].getCommentsId()),
                    () -> assertThat(responseDto.commentsList().get(0).parentsNickname()).isEqualTo(comments[11].getParents().getParentsNickname()),
                    () -> assertThat(responseDto.commentsList().get(0).commentsContent()).isEqualTo(comments[11].getCommentsContentPh()),
                    () -> assertThat(responseDto.commentsList().get(0).likes()).isEqualTo(comments[11].getLikes()),
                    () -> assertThat(responseDto.commentsList().get(0).createdAt()).isNotNull()
            );
        }
    }
}
