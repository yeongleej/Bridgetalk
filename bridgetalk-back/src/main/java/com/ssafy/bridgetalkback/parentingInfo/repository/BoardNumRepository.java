package com.ssafy.bridgetalkback.parentingInfo.repository;

import com.ssafy.bridgetalkback.parentingInfo.domain.Category;
import com.ssafy.bridgetalkback.parentingInfo.domain.BoardNum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardNumRepository extends JpaRepository<BoardNum, Long> {
    List<BoardNum> findBoardNumByAge(Category age);
}
