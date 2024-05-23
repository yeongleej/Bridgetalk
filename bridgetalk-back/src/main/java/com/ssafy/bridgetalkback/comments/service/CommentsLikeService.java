package com.ssafy.bridgetalkback.comments.service;

import com.ssafy.bridgetalkback.comments.domain.Comments;
import com.ssafy.bridgetalkback.comments.domain.CommentsLike;
import com.ssafy.bridgetalkback.comments.exception.CommentsErrorCode;
import com.ssafy.bridgetalkback.comments.repository.CommentsLikeRepository;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.parents.service.ParentsFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentsLikeService {
    private final ParentsFindService parentsFindService;
    private final CommentsFindService commentsFindService;
    private final CommentsLikeRepository commentsLikeRepository;


    @Transactional
    public Long register(UUID parentsId, Long CommentsId){
        validateSelfCommentsLike(parentsId, CommentsId);
        validateAlreadyCommentsLike(parentsId, CommentsId);

        Parents parents = parentsFindService.findParentsByUuidAndIsDeleted(parentsId);
        Comments comments = commentsFindService.findByCommentsIdAndIsDeleted(CommentsId);
        CommentsLike commentsLike = CommentsLike.registerCommentsLike(parents, comments);
        comments.increaseLikes();

        return commentsLikeRepository.save(commentsLike).getCommentsLikeId();
    }

    @Transactional
    public void cancel(UUID parentsId, Long commentsId){
        validateCancel(parentsId, commentsId);
        Comments comments = commentsFindService.findByCommentsIdAndIsDeleted(commentsId);
        comments.decreaseLikes();
        commentsLikeRepository.deleteByParentsIdAndCommentsId(parentsId, commentsId);
    }

    @Transactional
    public void deleteByParents(Parents parents) {
        commentsLikeRepository.deleteByParents(parents);
    }

    private void validateSelfCommentsLike(UUID parentsId, Long commentsId) {
        Comments comments = commentsFindService.findByCommentsIdAndIsDeleted(commentsId);
        if (comments.getParents().getUuid().equals(parentsId)) {
            throw BaseException.type(CommentsErrorCode.SELF_COMMENT_LIKE_NOT_ALLOWED);
        }
    }

    private void validateAlreadyCommentsLike(UUID parentsId, Long commentsId) {
        if (commentsLikeRepository.existsByParentsUuidAndCommentsCommentsId(parentsId, commentsId)) {
            throw BaseException.type(CommentsErrorCode.ALREADY_COMMENT_LIKE);
        }
    }

    private void validateCancel(UUID parentsId, Long commentsId) {
        if (!commentsLikeRepository.existsByParentsUuidAndCommentsCommentsId(parentsId, commentsId)) {
            throw BaseException.type(CommentsErrorCode.COMMENT_LIKE_NOT_FOUND);
        }
    }

    public boolean checkLike(UUID parentsId, Long commentsId) {
        return commentsLikeRepository.existsByParentsUuidAndCommentsCommentsId(parentsId, commentsId);
    }
}
