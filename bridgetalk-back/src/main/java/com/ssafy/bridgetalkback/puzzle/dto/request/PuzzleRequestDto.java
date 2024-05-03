package com.ssafy.bridgetalkback.puzzle.dto.request;

public record PuzzleRequestDto(
        String puzzleNation,
        String puzzleLandmarkName,
        String puzzleLandmarkContent
) {
}
