package com.ssafy.bridgetalkback.reports.service;

import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.reports.domain.Reports;
import com.ssafy.bridgetalkback.reports.exception.ReportsErrorCode;
import com.ssafy.bridgetalkback.reports.repository.ReportsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReportsFindService {

    private final ReportsRepository reportsRepository;

    public Reports findByReportsIdAndIsDeleted(Long reportsId) {
        log.info("{ ReportsFindService } : Id(Pk)로 레포트 정보 조회 - " + reportsId);
        return reportsRepository.findByReportsIdAndIsDeleted(reportsId, 0)
                .orElseThrow(() -> BaseException.type(ReportsErrorCode.REPORTS_NOT_FOUND));
    }
}
