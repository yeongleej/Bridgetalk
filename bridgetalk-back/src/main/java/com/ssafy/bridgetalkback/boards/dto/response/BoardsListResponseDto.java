package com.ssafy.bridgetalkback.boards.dto.response;

import com.ssafy.bridgetalkback.boards.query.dto.BoardsListDto;

import java.util.List;

public record BoardsListResponseDto(
        List<BoardsListDto> boardsList
) {
}
