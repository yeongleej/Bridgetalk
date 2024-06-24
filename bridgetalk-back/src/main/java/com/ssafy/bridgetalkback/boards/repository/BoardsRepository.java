package com.ssafy.bridgetalkback.boards.repository;

import com.ssafy.bridgetalkback.boards.domain.Boards;
import com.ssafy.bridgetalkback.boards.query.BoardsListQueryRepository;
import com.ssafy.bridgetalkback.reports.domain.Reports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardsRepository extends JpaRepository<Boards, Long>, BoardsListQueryRepository {
    Optional<Boards> findBoardsByBoardsIdAndIsDeleted(Long boardsId, int isDeleted);

    boolean existsBoardsByReportsReportsIdAndIsDeleted(Long reportsId, int isDeleted);

    Optional<Boards> findBoardsByReportsAndIsDeleted(Reports reports, int isDeleted);
}