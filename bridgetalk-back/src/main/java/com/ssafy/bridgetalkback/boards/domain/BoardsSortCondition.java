package com.ssafy.bridgetalkback.boards.domain;

import com.ssafy.bridgetalkback.boards.exception.BoardsErrorCode;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.global.utils.EnumStandard;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum BoardsSortCondition implements EnumStandard {
    TIME("최신순"),
    LIKES("좋아요순");

    private final String value;

    public static BoardsSortCondition from(String value) {
        return Arrays.stream(values())
                .filter(boardSortCondition -> boardSortCondition.value.equals(value))
                .findFirst()
                .orElseThrow(() -> BaseException.type(BoardsErrorCode.SORT_CONDITION_NOT_FOUND));
    }
}
