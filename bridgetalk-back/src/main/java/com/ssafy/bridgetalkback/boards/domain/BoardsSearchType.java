package com.ssafy.bridgetalkback.boards.domain;

import com.ssafy.bridgetalkback.boards.exception.BoardsErrorCode;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.global.utils.EnumStandard;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum BoardsSearchType implements EnumStandard {
    TITLE("제목"),
    CONTENT_AND_REPORTS_SUMMARY("내용과레포트요약"),
    WRITER("작성자"),
    REPORTS_KEYWORD("레포트 키워드"),
    TITLE_AND_CONTENT_AND_REPORTS("제목과내용과레포트"),
    ;

    private final String value;

    public static BoardsSearchType from(String value) {
        return Arrays.stream(values())
                .filter(boardSearchType -> boardSearchType.value.equals(value))
                .findFirst()
                .orElseThrow(() -> BaseException.type(BoardsErrorCode.SEARCH_TYPE_NOT_FOUND));
    }
}
