package com.ssafy.bridgetalkback.puzzle.service;

import com.ssafy.bridgetalkback.files.service.S3FileService;
import com.ssafy.bridgetalkback.puzzle.domain.Puzzle;
import com.ssafy.bridgetalkback.puzzle.dto.request.PuzzleRequestDto;
import com.ssafy.bridgetalkback.puzzle.dto.response.PuzzleListResponseDto;
import com.ssafy.bridgetalkback.puzzle.dto.response.PuzzleResponseDto;
import com.ssafy.bridgetalkback.puzzle.repository.PuzzleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PuzzleService {
    private final PuzzleRepository puzzleRepository;
    private final PuzzleFindService puzzleFindService;
    private final S3FileService s3FileService;

    public String savePuzzleFiles(MultipartFile puzzleFile){
        log.info("{ PuzzleService.uploadPuzzleFiles() } : 퍼즐 이미지 s3업로드 메서드");
        return s3FileService.uploadPuzzleFiles(puzzleFile);
    }

    @Transactional
    public Long createPuzzle(PuzzleRequestDto requestDto, String imageUrl) {
        log.info("{ PuzzleService } : 퍼즐 등록 진입");
        Puzzle puzzle = Puzzle.createPuzzle(requestDto.puzzleNation(), imageUrl, requestDto.puzzleLandmarkName(),
                requestDto.puzzleLandmarkContent());

        return puzzleRepository.save(puzzle).getPuzzleId();
    }

    public PuzzleResponseDto puzzleDetail(Long puzzleId) {
        log.info("{ PuzzleService } : 퍼즐 상세 조회 진입");
        Puzzle puzzle = puzzleFindService.findByPuzzleIdAndIsDeleted(puzzleId);

        PuzzleResponseDto puzzleResponseDto = PuzzleResponseDto.from(puzzle);
        log.info("{ PuzzleService } : 퍼즐 상세 조회 성공");
        return puzzleResponseDto;
    }

    public PuzzleListResponseDto puzzleList(String nation) {
        log.info("{ PuzzleService } : 퍼즐 리스트 조회 진입");

        List<Puzzle> puzzles = puzzleRepository.findAllByIsDeletedAndPuzzleNation(0, nation);
        List<PuzzleResponseDto> puzzleList = new ArrayList<>();

        for (Puzzle puzzle : puzzles){
            puzzleList.add(PuzzleResponseDto.from(puzzle));
        }

        PuzzleListResponseDto puzzleListResponseDto = new PuzzleListResponseDto(puzzleList);
        log.info("{ PuzzleService } : 퍼즐 리스트 조회 성공");
        return puzzleListResponseDto;
    }
}
