package com.ssafy.bridgetalkback.comments.repository;

import com.ssafy.bridgetalkback.comments.domain.Comments;
import com.ssafy.bridgetalkback.comments.query.CommentsListQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentsRepository extends JpaRepository<Comments, Long>, CommentsListQueryRepository {
    Optional<Comments> findByCommentsIdAndIsDeleted(Long commentsId, int i);
}
