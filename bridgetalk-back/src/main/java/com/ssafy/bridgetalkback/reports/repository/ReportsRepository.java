package com.ssafy.bridgetalkback.reports.repository;

import com.ssafy.bridgetalkback.reports.domain.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportsRepository extends JpaRepository<Reports, Long> {
    Optional<Reports> findByReportsIdAndIsDeleted(Long reportsId, int isDeleted);
}
