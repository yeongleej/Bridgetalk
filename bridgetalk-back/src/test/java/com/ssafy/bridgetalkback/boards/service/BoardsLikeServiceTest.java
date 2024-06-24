package com.ssafy.bridgetalkback.boards.service;

import com.ssafy.bridgetalkback.boards.domain.Boards;
import com.ssafy.bridgetalkback.boards.domain.BoardsLike;
import com.ssafy.bridgetalkback.boards.exception.BoardsErrorCode;
import com.ssafy.bridgetalkback.common.ServiceTest;
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
import static com.ssafy.bridgetalkback.fixture.KidsFixture.JIYEONG;
import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SOYOUNG;
import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SUNKYOUNG;
import static com.ssafy.bridgetalkback.fixture.ReportsFixture.REPORTS_01;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Boards [Service Layer] -> BoardsLikeService 테스트")
public class BoardsLikeServiceTest extends ServiceTest {
    @Autowired
    private BoardsLikeService boardsLikeService;

    private Parents[] parents = new Parents[2];
    private Kids kids;
    private Reports reports;
    private Boards boards;

    @BeforeEach
    void setup() {
        parents[0] = parentsRepository.save(SUNKYOUNG.toParents());
        parents[1] = parentsRepository.save(SOYOUNG.toParents());
        kids = kidsRepository.save(JIYEONG.toKids(parents[0]));
        reports = reportsRepository.save(REPORTS_01.toReports(kids));
        boards = boardsRepository.save(BOARDS_01.toBoards(reports, parents[0]));
    }

    @Nested
    @DisplayName("게시글좋아요 등록")
    class register {
        @Test
        @DisplayName("본인의 게시글은 좋아요를 누를 수 없다")
        void throwExceptionBySelfBoardLikeNotAllowed() {
            assertThatThrownBy(() -> boardsLikeService.register(parents[0].getUuid(), boards.getBoardsId()))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(BoardsErrorCode.SELF_BOARD_LIKE_NOT_ALLOWED.getMessage());
        }

        @Test
        @DisplayName("한 게시글에 두 번 이상 좋아요를 누를 수 없다")
        void throwExceptionByAlreadyBoardLike() {
            // given
            boardsLikeService.register(parents[1].getUuid(), boards.getBoardsId());

            // when - then
            assertThatThrownBy(() -> boardsLikeService.register(parents[1].getUuid(), boards.getBoardsId()))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(BoardsErrorCode.ALREADY_BOARD_LIKE.getMessage());
        }

        @Test
        @DisplayName("게시글좋아요 등록에 성공한다")
        void success() {
            // when
            Long likeBoardId = boardsLikeService.register(parents[1].getUuid(), boards.getBoardsId());

            // then
            BoardsLike findBoardsLike = boardsLikeRepository.findById(likeBoardId).orElseThrow();
            assertAll(
                    () -> assertThat(boardsLikeRepository.countByBoards(boards)).isEqualTo(1),
                    () -> assertThat(findBoardsLike.getParents().getUuid()).isEqualTo(parents[1].getUuid()),
                    () -> assertThat(findBoardsLike.getBoards().getBoardsId()).isEqualTo(boards.getBoardsId())
            );
        }
    }

    @Nested
    @DisplayName("게시글좋아요 취소")
    class cancel {
        @Test
        @DisplayName("좋아요를 누르지 않은 게시글의 좋아요는 취소할 수 없다")
        void throwExceptionByBoardLikeNotFound() {
            // when - then
            assertThatThrownBy(() -> boardsLikeService.cancel(parents[1].getUuid(), boards.getBoardsId()))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(BoardsErrorCode.BOARD_LIKE_NOT_FOUND.getMessage());
        }

        @Test
        @DisplayName("게시글좋아요 취소에 성공한다")
        void success() {
            // given
            boardsLikeService.register(parents[1].getUuid(), boards.getBoardsId());

            // when
            boardsLikeService.cancel(parents[1].getUuid(), boards.getBoardsId());

            // then
            assertThat(boardsLikeRepository.existsByParentsUuidAndBoardsBoardsId(parents[1].getUuid(), boards.getBoardsId())).isFalse();
        }
    }

    @Test
    @DisplayName("게시글좋아요 삭제에 성공한다")
    void success() {
        // given
        boardsLikeService.register(parents[1].getUuid(), boards.getBoardsId());

        // when
        boardsLikeService.deleteByParents(parents[1]);

        // then
        assertThat(boardsLikeRepository.existsByParentsUuidAndBoardsBoardsId(parents[1].getUuid(), boards.getBoardsId())).isFalse();
    }

    @Nested
    @DisplayName("게시글 좋아요 여부 확인")
    class checkLike {
        @Test
        @DisplayName("게시글 좋아요 여부 확인에 성공한다")
        void success() {
            // given
            boardsLikeService.register(parents[1].getUuid(), boards.getBoardsId());

            // when
            boolean checkLike1 = boardsLikeService.checkLike(parents[1].getUuid(), boards.getBoardsId());
            boolean checkLike2 = boardsLikeService.checkLike(parents[0].getUuid(), boards.getBoardsId());

            // then
            assertAll(
                    () -> assertThat(checkLike1).isTrue(),
                    () -> assertThat(checkLike2).isFalse()
            );
        }
    }
}
