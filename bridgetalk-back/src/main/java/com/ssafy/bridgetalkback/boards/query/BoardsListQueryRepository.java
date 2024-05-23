package com.ssafy.bridgetalkback.boards.query;

import com.ssafy.bridgetalkback.boards.domain.BoardsSearchType;
import com.ssafy.bridgetalkback.boards.dto.response.BoardsListResponseDto;
import com.ssafy.bridgetalkback.boards.dto.response.CustomBoardsListResponseDto;
import com.ssafy.bridgetalkback.boards.query.dto.BoardsListDto;
import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.parents.domain.Parents;

public interface BoardsListQueryRepository {
    CustomBoardsListResponseDto<BoardsListDto> getBoardsListOrderByTime(int page, BoardsSearchType boardSearchType,
                                                                        String searchWord, Language language);
    CustomBoardsListResponseDto<BoardsListDto> getBoardsListOrderByLikes(int page, BoardsSearchType boardSearchType,
                                                                         String searchWord, Language language);

    BoardsListResponseDto getMyBoardsListOrderByTime(Parents parents, Language language);
    BoardsListResponseDto getMyBoardsListOrderByLikes(Parents parents, Language language);
}
