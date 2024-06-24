package com.ssafy.bridgetalkback.puzzle.dto.response;

import com.ssafy.bridgetalkback.puzzle.domain.Puzzle;
import lombok.Builder;

@Builder
public record PuzzleResponseDto(
        Long puzzleId,
        String puzzleNation,
        String puzzleImageUrl,
        String puzzleLandmarkName,
        String puzzleLandmarkContent
) {
    public static PuzzleResponseDto from(Puzzle puzzle) {
        return PuzzleResponseDto.builder()
                .puzzleId(puzzle.getPuzzleId())
                .puzzleNation(puzzle.getPuzzleNation())
                .puzzleImageUrl(puzzle.getPuzzleImageUrl())
                .puzzleLandmarkName(puzzle.getPuzzleLandmarkName())
                .puzzleLandmarkContent(puzzle.getPuzzleLandmarkContent())
                .build();
    }
}
