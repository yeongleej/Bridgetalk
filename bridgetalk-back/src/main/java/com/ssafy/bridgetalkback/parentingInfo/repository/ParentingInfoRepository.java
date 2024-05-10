package com.ssafy.bridgetalkback.parentingInfo.repository;

import com.ssafy.bridgetalkback.parentingInfo.domain.ParentingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParentingInfoRepository extends JpaRepository<ParentingInfo, Long> {
    Optional<ParentingInfo> findParentingInfoByParentingInfoIdAndIsDeleted(Long parentingInfoId, int isDeleted);
}
