package com.ssafy.bridgetalkback.puzzle.service;

import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.puzzle.domain.Puzzle;
import com.ssafy.bridgetalkback.puzzle.dto.request.PuzzleRequestDto;
import com.ssafy.bridgetalkback.puzzle.dto.response.PuzzleListResponseDto;
import com.ssafy.bridgetalkback.puzzle.dto.response.PuzzleResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ssafy.bridgetalkback.fixture.PuzzleFixture.*;
import static com.ssafy.bridgetalkback.fixture.PuzzleFixture.PUZZLE_04;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Puzzle [Service Layer] -> PuzzleService 테스트")
public class PuzzleServiceTest extends ServiceTest {
    @Autowired
    private PuzzleFindService puzzleFindService;

    @Autowired
    private PuzzleService puzzleService;

    private Puzzle[] puzzles = new Puzzle[4];

    @BeforeEach
    void setup() {
        puzzles[0] = puzzleRepository.save(PUZZLE_01.toPuzzle());
        puzzles[1] = puzzleRepository.save(PUZZLE_02.toPuzzle());
        puzzles[2] = puzzleRepository.save(PUZZLE_03.toPuzzle());
        puzzles[3] = puzzleRepository.save(PUZZLE_04.toPuzzle());
    }

    @Test
    @DisplayName("퍼즐 등록에 성공한다")
    void createPuzzle() {
        // when
        Long puzzleId = puzzleService.createPuzzle(createPuzzleRequestDto(), "imageUrl");

        // then
        Puzzle findPuzzle = puzzleFindService.findByPuzzleIdAndIsDeleted(puzzleId);
        assertAll(
                () -> assertThat(findPuzzle.getPuzzleId()).isEqualTo(puzzleId),
                () -> assertThat(findPuzzle.getPuzzleNation()).isEqualTo(PUZZLE_01.getPuzzleNation()),
                () -> assertThat(findPuzzle.getPuzzleImageUrl()).isEqualTo(PUZZLE_01.getPuzzleImageUrl()),
                () -> assertThat(findPuzzle.getPuzzleLandmarkName()).isEqualTo(PUZZLE_01.getPuzzleLandmarkName()),
                () -> assertThat(findPuzzle.getPuzzleLandmarkContent()).isEqualTo(PUZZLE_01.getPuzzleLandmarkContent()),
                () -> assertThat(findPuzzle.getIsDeleted()).isEqualTo(0)
        );
    }

    @Test
    @DisplayName("퍼즐 상세조회에 성공한다")
    void findPuzzleDetail() {
        // when
        PuzzleResponseDto responseDto = puzzleService.puzzleDetail(puzzles[0].getPuzzleId());

        // then
        assertAll(
                () -> assertThat(responseDto.puzzleId()).isEqualTo(puzzles[0].getPuzzleId()),
                () -> assertThat(responseDto.puzzleNation()).isEqualTo(puzzles[0].getPuzzleNation()),
                () -> assertThat(responseDto.puzzleImageUrl()).isEqualTo(puzzles[0].getPuzzleImageUrl()),
                () -> assertThat(responseDto.puzzleLandmarkName()).isEqualTo(puzzles[0].getPuzzleLandmarkName()),
                () -> assertThat(responseDto.puzzleLandmarkContent()).isEqualTo(puzzles[0].getPuzzleLandmarkContent())
        );
    }

    @Test
    @DisplayName("퍼즐 리스트 조회에 성공한다")
    void findPuzzleList() {
        // when
        PuzzleListResponseDto responseDto = puzzleService.puzzleList("viet");

        // then
        assertAll(
                () -> assertThat(responseDto.puzzleList().size()).isEqualTo(4),
                () -> assertThat(responseDto.puzzleList().get(0).puzzleId()).isEqualTo(puzzles[0].getPuzzleId()),
                () -> assertThat(responseDto.puzzleList().get(0).puzzleNation()).isEqualTo(puzzles[0].getPuzzleNation()),
                () -> assertThat(responseDto.puzzleList().get(0).puzzleImageUrl()).isEqualTo(puzzles[0].getPuzzleImageUrl()),
                () -> assertThat(responseDto.puzzleList().get(0).puzzleLandmarkName()).isEqualTo(puzzles[0].getPuzzleLandmarkName()),
                () -> assertThat(responseDto.puzzleList().get(0).puzzleLandmarkContent()).isEqualTo(puzzles[0].getPuzzleLandmarkContent())
        );
    }

    private PuzzleRequestDto createPuzzleRequestDto() {
        return new PuzzleRequestDto(PUZZLE_01.getPuzzleNation(), PUZZLE_01.getPuzzleLandmarkName(), PUZZLE_01.getPuzzleLandmarkContent());
    }
}
