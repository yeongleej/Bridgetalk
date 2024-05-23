package com.ssafy.bridgetalkback.comments.query;

import com.ssafy.bridgetalkback.boards.domain.Boards;
import com.ssafy.bridgetalkback.comments.dto.response.CommentsListResponseDto;
import com.ssafy.bridgetalkback.comments.dto.response.CustomCommentsListResponseDto;
import com.ssafy.bridgetalkback.comments.query.dto.CommentsListDto;
import com.ssafy.bridgetalkback.global.Language;

public interface CommentsListQueryRepository {
    CustomCommentsListResponseDto<CommentsListDto> getCommentsListOrderByTime(Boards boards, int page, Language language);

    CustomCommentsListResponseDto<CommentsListDto> getCommentsListOrderByLikes(Boards boards, int page, Language language);

    CommentsListResponseDto getReportsCommentsListOrderByTime(Boards boards, Language language);
}
