package com.ssafy.bridgetalkback.boards.service;

import com.ssafy.bridgetalkback.boards.domain.Boards;
import com.ssafy.bridgetalkback.boards.domain.BoardsLike;
import com.ssafy.bridgetalkback.boards.exception.BoardsErrorCode;
import com.ssafy.bridgetalkback.boards.repository.BoardsLikeRepository;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.parents.service.ParentsFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardsLikeService {
    private final ParentsFindService parentsFindService;
    private final BoardsFindService boardsFindService;
    private final BoardsLikeRepository boardsLikeRepository;

    @Transactional
    public Long register(UUID parentsId, Long boardsId){
        validateSelfBoardLike(parentsId, boardsId);
        validateAlreadyBoardLike(parentsId, boardsId);

        Parents parents = parentsFindService.findParentsByUuidAndIsDeleted(parentsId);
        Boards boards = boardsFindService.findByBoardsIdAndIsDeleted(boardsId);
        BoardsLike likeBoards = BoardsLike.registerBoardsLike(parents, boards);
        boards.increaseLikes();

        return boardsLikeRepository.save(likeBoards).getBoardsLikeId();
    }

    @Transactional
    public void deleteByParents(Parents parents) {
        boardsLikeRepository.deleteByParents(parents);
    }

    @Transactional
    public void cancel(UUID parentsId, Long boardsId){
        validateCancel(parentsId, boardsId);
        Boards boards = boardsFindService.findByBoardsIdAndIsDeleted(boardsId);
        boards.decreaseLikes();
        boardsLikeRepository.deleteByParentsIdAndBoardsId(parentsId, boardsId);
    }

    public boolean checkLike(UUID parentsId, Long boardsId) {
        return boardsLikeRepository.existsByParentsUuidAndBoardsBoardsId(parentsId, boardsId);
    }

    private void validateSelfBoardLike(UUID parentsId, Long boardsId) {
        Boards board = boardsFindService.findByBoardsIdAndIsDeleted(boardsId);
        if (board.getParents().getUuid().equals(parentsId)) {
            throw BaseException.type(BoardsErrorCode.SELF_BOARD_LIKE_NOT_ALLOWED);
        }
    }

    private void validateAlreadyBoardLike(UUID parentsId, Long boardsId) {
        if (boardsLikeRepository.existsByParentsUuidAndBoardsBoardsId(parentsId, boardsId)) {
            throw BaseException.type(BoardsErrorCode.ALREADY_BOARD_LIKE);
        }
    }

    private void validateCancel(UUID parentsId, Long boardsId) {
        if (!boardsLikeRepository.existsByParentsUuidAndBoardsBoardsId(parentsId, boardsId)) {
            throw BaseException.type(BoardsErrorCode.BOARD_LIKE_NOT_FOUND);
        }
    }
}
