package com.ssafy.bridgetalkback.comments.service;

import com.ssafy.bridgetalkback.comments.domain.Comments;
import com.ssafy.bridgetalkback.comments.exception.CommentsErrorCode;
import com.ssafy.bridgetalkback.comments.repository.CommentsRepository;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentsFindService {

    private final CommentsRepository commentsRepository;

    public Comments findByCommentsIdAndIsDeleted(Long commentsId) {
        log.info("{ CommentsFindService } : Id(Pk)로 답글 정보 조회 - " + commentsId);
        return commentsRepository.findByCommentsIdAndIsDeleted(commentsId, 0)
                .orElseThrow(()-> BaseException.type(CommentsErrorCode.COMMENTS_NOT_FOUND));
    }

}
