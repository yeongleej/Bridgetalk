package com.ssafy.bridgetalkback.parentingInfo.repository;

import com.ssafy.bridgetalkback.parentingInfo.domain.Category;
import com.ssafy.bridgetalkback.parentingInfo.domain.ParentingInfoBoardNum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParentingInfoBoardNumRepository extends JpaRepository<ParentingInfoBoardNum, Long> {
    List<ParentingInfoBoardNum> findBoardNumByAge(Category age);
}
