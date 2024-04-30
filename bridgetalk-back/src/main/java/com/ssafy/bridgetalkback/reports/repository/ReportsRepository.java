package com.ssafy.bridgetalkback.reports.repository;

import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.reports.domain.Reports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReportsRepository extends JpaRepository<Reports, Long> {
}
