package com.ssafy.bridgetalkback.parentingInfo.repository;

import com.ssafy.bridgetalkback.parentingInfo.domain.ParentingInfo;
import com.ssafy.bridgetalkback.parentingInfo.query.ParentingInfoQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParentingInfoRepository extends JpaRepository<ParentingInfo, Long>, ParentingInfoQueryRepository {
    Optional<ParentingInfo> findParentingInfoByParentingInfoIdAndIsDeleted(Long parentingInfoId, int isDeleted);
}
