package com.ssafy.bridgetalkback.comments.service;

import com.ssafy.bridgetalkback.boards.domain.Boards;
import com.ssafy.bridgetalkback.boards.repository.BoardsRepository;
import com.ssafy.bridgetalkback.boards.service.BoardsFindService;
import com.ssafy.bridgetalkback.comments.domain.CommentsSortCondition;
import com.ssafy.bridgetalkback.comments.dto.response.CommentsListResponseDto;
import com.ssafy.bridgetalkback.comments.dto.response.CustomCommentsListResponseDto;
import com.ssafy.bridgetalkback.comments.query.dto.CommentsListDto;
import com.ssafy.bridgetalkback.comments.repository.CommentsRepository;
import com.ssafy.bridgetalkback.global.Language;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentsListService {
    private final CommentsRepository commentsRepository;
    private final BoardsFindService boardsFindService;
    private final BoardsRepository boardsRepository;

    public CustomCommentsListResponseDto<CommentsListDto> getCustomCommentsList(Long boardId, int page, String sort, Language language) {
        log.info("{ CommentsListService } : Comments 리스트조회 진입");
        Boards boards = boardsFindService.findByBoardsIdAndIsDeleted(boardId);
        CommentsSortCondition commentsSortCondition = CommentsSortCondition.from(sort);
        CustomCommentsListResponseDto<CommentsListDto> commentsList = null;
        switch (commentsSortCondition) {
            case TIME -> commentsList = commentsRepository.getCommentsListOrderByTime(boards, page, language);
            case LIKES -> commentsList = commentsRepository.getCommentsListOrderByLikes(boards, page, language);
        }

        log.info("{ CommentsListService } : Comments 리스트조회 성공");
        return new CustomCommentsListResponseDto<>(commentsList.pageInfo(), commentsList.commentsList());
    }

    public CommentsListResponseDto getReportsCommentsList(Long reportsId, Language language) {
        log.info("{ CommentsListService } : Reports Comments 리스트조회 진입");
        CommentsListResponseDto reportsCommentsList = null;
        if(boardsRepository.existsBoardsByReportsReportsIdAndIsDeleted(reportsId, 0)) {
            log.info("{ CommentsListService } : Reports를 사용한 게시글 존재");
            Boards boards = boardsFindService.findBoardsByReportsAndIsDeleted(reportsId);
            reportsCommentsList = commentsRepository.getReportsCommentsListOrderByTime(boards, language);
        }
        else {
            reportsCommentsList = new CommentsListResponseDto(Collections.emptyList());
        }

        log.info("{ CommentsListService } : Reports Comments 리스트조회 성공");
        return reportsCommentsList;
    }
}

