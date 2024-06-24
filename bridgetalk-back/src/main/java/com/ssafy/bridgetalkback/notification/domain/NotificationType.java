package com.ssafy.bridgetalkback.notification.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationType {
    SEND_DUMMY_DATA("클라이언트에게 더미데이터 전송"),
    // 부모에게 Parents
    KID_REPORTS_REGISTER("아이 속마음 레포트가 도착했습니다."),
    POST_COMMENTS_REGISTER("게시글에 댓글이 등록되었습니다."),
    POST_LIKE_REGISTER("회원님의 게시글을 좋아합니다."),

    // 아이에게 Kids
    PARENT_LETTERS_REGISTER("다이노에게 편지가 도착했습니다.");

    private final String word;

}