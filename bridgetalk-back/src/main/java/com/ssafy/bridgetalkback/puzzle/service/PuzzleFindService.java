package com.ssafy.bridgetalkback.puzzle.service;

import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.puzzle.domain.Puzzle;
import com.ssafy.bridgetalkback.puzzle.exception.PuzzleErrorCode;
import com.ssafy.bridgetalkback.puzzle.repository.PuzzleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PuzzleFindService {
    private final PuzzleRepository puzzleRepository;

    public Puzzle findByPuzzleIdAndIsDeleted(Long puzzleId) {
        log.info("{ PuzzleFindService } : Id(Pk)로 퍼즐(랜드마크) 정보 조회 - "+puzzleId);
        return puzzleRepository.findByPuzzleIdAndIsDeleted(puzzleId, 0)
                .orElseThrow(() -> BaseException.type(PuzzleErrorCode.PUZZLE_NOT_FOUND));
    }
}
