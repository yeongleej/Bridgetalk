package com.ssafy.bridgetalkback.fixture;

import com.ssafy.bridgetalkback.puzzle.domain.Puzzle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PuzzleFixture {
    PUZZLE_01("Vietnam","imageUrl", "하롱베이", "하롱베이 설명"),
    PUZZLE_02("Vietnam","imageUrl", "다낭 오행산", "다낭 오행산 설명"),
    PUZZLE_03("Vietnam","imageUrl", "호이안 내원교", "호이안 내원교 설명"),
    PUZZLE_04("Vietnam","imageUrl", "호치민 생가", "호치민 생가 설명")
    ;

    private final String puzzleNation;
    private final String puzzleImageUrl;
    private final String puzzleLandmarkName;
    private final String puzzleLandmarkContent;

    public Puzzle toPuzzle() {
        return Puzzle.createPuzzle(puzzleNation, puzzleImageUrl, puzzleLandmarkName, puzzleLandmarkContent);
    }
}
