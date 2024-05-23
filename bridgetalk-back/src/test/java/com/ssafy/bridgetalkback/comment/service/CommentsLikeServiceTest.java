package com.ssafy.bridgetalkback.comment.service;

import com.ssafy.bridgetalkback.boards.domain.Boards;
import com.ssafy.bridgetalkback.comments.domain.Comments;
import com.ssafy.bridgetalkback.comments.domain.CommentsLike;
import com.ssafy.bridgetalkback.comments.exception.CommentsErrorCode;
import com.ssafy.bridgetalkback.comments.service.CommentsLikeService;
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
import static com.ssafy.bridgetalkback.fixture.CommentsFixture.COMMENTS_01;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Comments [Service Layer] -> CommentsLikeService 테스트")
public class CommentsLikeServiceTest extends ServiceTest {
    @Autowired
    private CommentsLikeService commentsLikeService;

    private Parents[] parents = new Parents[2];
    private Kids kids;
    private Reports reports;
    private Boards boards;
    private Comments comments;

    @BeforeEach
    void setup() {
        parents[0] = parentsRepository.save(SUNKYOUNG.toParents());
        parents[1] = parentsRepository.save(SOYOUNG.toParents());
        kids = kidsRepository.save(JIYEONG.toKids(parents[0]));
        reports = reportsRepository.save(REPORTS_01.toReports(kids));
        boards = boardsRepository.save(BOARDS_01.toBoards(reports, parents[0]));
        comments = commentsRepository.save(COMMENTS_01.toComments(parents[0], boards));
    }

    @Nested
    @DisplayName("답글 좋아요 등록")
    class register {
        @Test
        @DisplayName("본인의 게시글은 좋아요를 누를 수 없다")
        void throwExceptionBySelfBoardLikeNotAllowed() {
            assertThatThrownBy(() -> commentsLikeService.register(parents[0].getUuid(), comments.getCommentsId()))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(CommentsErrorCode.SELF_COMMENT_LIKE_NOT_ALLOWED.getMessage());
        }

        @Test
        @DisplayName("한 답글에 두 번 이상 좋아요를 누를 수 없다")
        void throwExceptionByAlreadyBoardLike() {
            // given
            commentsLikeService.register(parents[1].getUuid(), comments.getCommentsId());

            // when - then
            assertThatThrownBy(() -> commentsLikeService.register(parents[1].getUuid(), comments.getCommentsId()))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(CommentsErrorCode.ALREADY_COMMENT_LIKE.getMessage());
        }

        @Test
        @DisplayName("답글 좋아요 등록에 성공한다")
        void success() {
            // when
            Long likeCommentId = commentsLikeService.register(parents[1].getUuid(), comments.getCommentsId());

            // then
            CommentsLike findCommentsLike = commentsLikeRepository.findById(likeCommentId).orElseThrow();
            assertAll(
                    () -> assertThat(commentsLikeRepository.countByComments(comments)).isEqualTo(1),
                    () -> assertThat(findCommentsLike.getParents().getUuid()).isEqualTo(parents[1].getUuid()),
                    () -> assertThat(findCommentsLike.getComments().getCommentsId()).isEqualTo(comments.getCommentsId())
            );
        }
    }

    @Nested
    @DisplayName("답글 좋아요 취소")
    class cancel {
        @Test
        @DisplayName("좋아요를 누르지 않은 답글의 좋아요는 취소할 수 없다")
        void throwExceptionByBoardLikeNotFound() {
            // when - then
            assertThatThrownBy(() -> commentsLikeService.cancel(parents[1].getUuid(), comments.getCommentsId()))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(CommentsErrorCode.COMMENT_LIKE_NOT_FOUND.getMessage());
        }

        @Test
        @DisplayName("답글 좋아요 취소에 성공한다")
        void success() {
            // given
            commentsLikeService.register(parents[1].getUuid(), comments.getCommentsId());

            // when
            commentsLikeService.cancel(parents[1].getUuid(), comments.getCommentsId());

            // then
            assertThat(commentsLikeRepository.existsByParentsUuidAndCommentsCommentsId(parents[1].getUuid(), comments.getCommentsId())).isFalse();
        }
    }

    @Test
    @DisplayName("답글 좋아요 삭제에 성공한다")
    void success() {
        // given
        commentsLikeService.register(parents[1].getUuid(), comments.getCommentsId());

        // when
        commentsLikeService.deleteByParents(parents[1]);

        // then
        assertThat(commentsLikeRepository.existsByParentsUuidAndCommentsCommentsId(parents[1].getUuid(), comments.getCommentsId())).isFalse();
    }

    @Nested
    @DisplayName("답글 좋아요 여부 확인")
    class checkLike {
        @Test
        @DisplayName("답글 좋아요 여부 확인에 성공한다")
        void success() {
            // given
            commentsLikeService.register(parents[1].getUuid(), comments.getCommentsId());

            // when
            boolean checkLike1 = commentsLikeService.checkLike(parents[1].getUuid(), comments.getCommentsId());
            boolean checkLike2 = commentsLikeService.checkLike(parents[0].getUuid(), comments.getCommentsId());

            // then
            assertAll(
                    () -> assertThat(checkLike1).isTrue(),
                    () -> assertThat(checkLike2).isFalse()
            );
        }
    }
}
