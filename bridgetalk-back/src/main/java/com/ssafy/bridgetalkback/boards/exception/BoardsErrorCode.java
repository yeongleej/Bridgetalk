package com.ssafy.bridgetalkback.boards.exception;

import com.ssafy.bridgetalkback.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BoardsErrorCode implements ErrorCode {
    BOARDS_NOT_FOUND(HttpStatus.NOT_FOUND, "BOARDS_001", "게시글 정보를 찾을 수 없습니다."),
    USER_IS_NOT_PARENTS(HttpStatus.NOT_FOUND, "BOARDS_002", "부모가 아닙니다."),
    SEARCH_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "BOARD_003", "지원하지 않는 검색 조건입니다."),
    SORT_CONDITION_NOT_FOUND(HttpStatus.NOT_FOUND, "BOARD_004", "지원하지 않는 정렬 방식입니다."),
    USER_IS_NOT_BOARD_WRITER(HttpStatus.BAD_REQUEST, "BOARDS_005", "게시글 작성자가 아닙니다."),
    ALREADY_BOARD_LIKE(HttpStatus.CONFLICT, "BOARD_006","이미 좋아요를 누른 게시글입니다."),
    SELF_BOARD_LIKE_NOT_ALLOWED(HttpStatus.CONFLICT, "BOARD_007", "본인 게시글은 좋아요를 누를 수 없습니다."),
    BOARD_LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "BOARD_008", "좋아요를 누르지 않은 게시글은 좋아요 취소를 할 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
