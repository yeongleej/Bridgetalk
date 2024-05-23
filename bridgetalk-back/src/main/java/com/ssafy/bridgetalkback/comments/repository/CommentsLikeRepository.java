package com.ssafy.bridgetalkback.comments.repository;

import com.ssafy.bridgetalkback.boards.domain.Boards;
import com.ssafy.bridgetalkback.boards.domain.BoardsLike;
import com.ssafy.bridgetalkback.comments.domain.Comments;
import com.ssafy.bridgetalkback.comments.domain.CommentsLike;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CommentsLikeRepository extends JpaRepository<CommentsLike, Long> {
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("DELETE FROM CommentsLike b WHERE b.parents.uuid = :parentsId AND b.comments.commentsId = :commentsId")
    void deleteByParentsIdAndCommentsId(@Param("parentsId") UUID parentsId, @Param("commentsId") Long commentsId);

    boolean existsByParentsUuidAndCommentsCommentsId(UUID parentsId, Long commentsId);

    int countByComments(Comments comments);

    void deleteByParents(Parents parents);
}