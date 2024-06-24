package com.ssafy.bridgetalkback.comments.dto.response;

import com.ssafy.bridgetalkback.comments.domain.Comments;
import com.ssafy.bridgetalkback.global.Language;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentsResponseDto(
        Long commentsId,
        String parentsUuid,
        String parentsNickname,
        String commentsContent,
        int likes,
        LocalDateTime createdAt
) {
    public static CommentsResponseDto fromComments(Comments comments, Language language) {
        return CommentsResponseDto.builder()
                .commentsId(comments.getCommentsId())
                .parentsUuid(String.valueOf(comments.getParents().getUuid()))
                .parentsNickname(comments.getParents().getParentsNickname())
                .commentsContent(language.equals(Language.kor) ? comments.getCommentsContentKor() : comments.getCommentsContentViet())
                .likes(comments.getLikes())
                .createdAt(comments.getCreatedAt())
                .build();
    }
}
