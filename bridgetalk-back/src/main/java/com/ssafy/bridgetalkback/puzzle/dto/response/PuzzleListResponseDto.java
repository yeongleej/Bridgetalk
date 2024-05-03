package com.ssafy.bridgetalkback.puzzle.dto.response;

import java.util.List;

public record PuzzleListResponseDto(
        List<PuzzleResponseDto> puzzleList
) {
}
