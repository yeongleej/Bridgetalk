package com.ssafy.bridgetalkback.boards.repository;

import com.ssafy.bridgetalkback.boards.domain.Boards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardsRepository extends JpaRepository<Boards, Long> {

    Optional<Boards> findByBoardsIdAndIsDeleted(Long boardsId, int i);
}
