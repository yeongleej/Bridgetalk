package com.ssafy.bridgetalkback.puzzle.repository;

import com.ssafy.bridgetalkback.puzzle.domain.Puzzle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PuzzleRepository extends JpaRepository<Puzzle, Long> {
    Optional<Puzzle> findByPuzzleIdAndIsDeleted(Long puzzleId, int isDeleted);

    List<Puzzle> findAllByIsDeleted(int isDeleted);
}
