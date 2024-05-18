package com.ssafy.bridgetalkback.comments.repository;

import com.ssafy.bridgetalkback.comments.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    Optional<Comments> findByCommentsIdAndIsDeleted(Long commentsId, int i);
}
