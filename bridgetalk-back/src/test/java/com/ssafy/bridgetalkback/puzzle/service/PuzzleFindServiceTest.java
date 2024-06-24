package com.ssafy.bridgetalkback.puzzle.service;

import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.puzzle.domain.Puzzle;
import com.ssafy.bridgetalkback.puzzle.exception.PuzzleErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ssafy.bridgetalkback.fixture.PuzzleFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Puzzle [Service Layer] -> PuzzleFindService 테스트")
public class PuzzleFindServiceTest extends ServiceTest {
    @Autowired
    private PuzzleFindService puzzleFindService;

    private Puzzle[] puzzles = new Puzzle[4];

    @BeforeEach
    void setup() {
        puzzles[0] = puzzleRepository.save(PUZZLE_01.toPuzzle());
        puzzles[1] = puzzleRepository.save(PUZZLE_02.toPuzzle());
        puzzles[2] = puzzleRepository.save(PUZZLE_03.toPuzzle());
        puzzles[3] = puzzleRepository.save(PUZZLE_04.toPuzzle());
    }

    @Test
    @DisplayName("ID(PK)로 퍼즐 정보를 조회한다")
    void findParentsByUuidAndIsDeleted() {
        // when
        Puzzle findPuzzle = puzzleFindService.findByPuzzleIdAndIsDeleted(puzzles[0].getPuzzleId());

        // then
        assertThatThrownBy(() -> puzzleFindService.findByPuzzleIdAndIsDeleted(puzzles[0].getPuzzleId() + 100L))
                .isInstanceOf(BaseException.class)
                .hasMessage(PuzzleErrorCode.PUZZLE_NOT_FOUND.getMessage());

        assertThat(findPuzzle).isEqualTo(puzzles[0]);
    }
}
