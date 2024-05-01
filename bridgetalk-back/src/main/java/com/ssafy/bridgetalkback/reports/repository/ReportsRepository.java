package com.ssafy.bridgetalkback.reports.repository;

import com.ssafy.bridgetalkback.reports.domain.Reports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReportsRepository extends JpaRepository<Reports, Long> {

    List<Reports> findAllByKidsUuidAndIsDeleted(UUID kids_uuid, int isDeleted);

    Optional<Reports> findReportsByReportsIdAndIsDeleted(Long reportsId, int isDeleted);
}
