package com.ssafy.bridgetalkback.comments.domain;

import com.ssafy.bridgetalkback.comments.exception.CommentsErrorCode;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.global.utils.EnumStandard;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum CommentsSortCondition implements EnumStandard {
    TIME("최신순"),
    LIKES("좋아요순");

    private final String value;

    public static CommentsSortCondition from(String value) {
        return Arrays.stream(values())
                .filter(CommentsSortCondition -> CommentsSortCondition.value.equals(value))
                .findFirst()
                .orElseThrow(() -> BaseException.type(CommentsErrorCode.SORT_CONDITION_NOT_FOUND));
    }
}

