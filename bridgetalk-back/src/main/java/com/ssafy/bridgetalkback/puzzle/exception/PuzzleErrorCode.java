package com.ssafy.bridgetalkback.puzzle.exception;

import com.ssafy.bridgetalkback.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PuzzleErrorCode implements ErrorCode {
    PUZZLE_NOT_FOUND(HttpStatus.NOT_FOUND, "PUZZLE_001", "퍼즐(랜드마크) 정보를 찾을 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}

