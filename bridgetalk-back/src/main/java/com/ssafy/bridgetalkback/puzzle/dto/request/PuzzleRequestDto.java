package com.ssafy.bridgetalkback.puzzle.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PuzzleRequestDto(
        @NotBlank(message = "나라는 필수입니다.")
        String puzzleNation,
        @NotBlank(message = "랜드마크 이름은 필수입니다.")
        String puzzleLandmarkName,
        @NotBlank(message = "랜드마크 내용은 필수입니다.")
        String puzzleLandmarkContent
) {
}
