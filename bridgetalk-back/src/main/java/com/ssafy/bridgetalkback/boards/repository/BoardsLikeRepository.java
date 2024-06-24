package com.ssafy.bridgetalkback.boards.repository;

import com.ssafy.bridgetalkback.boards.domain.Boards;
import com.ssafy.bridgetalkback.boards.domain.BoardsLike;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface BoardsLikeRepository extends JpaRepository<BoardsLike, Long> {
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("DELETE FROM BoardsLike b WHERE b.parents.uuid = :parentsId AND b.boards.boardsId = :boardsId")
    void deleteByParentsIdAndBoardsId(@Param("parentsId") UUID parentsId, @Param("boardsId") Long boardsId);

    boolean existsByParentsUuidAndBoardsBoardsId(UUID parentsId, Long boardsId);

    int countByBoards(Boards boards);

    void deleteByParents(Parents parents);
}
