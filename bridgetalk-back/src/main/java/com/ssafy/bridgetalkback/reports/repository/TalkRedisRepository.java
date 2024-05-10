package com.ssafy.bridgetalkback.reports.repository;

import com.ssafy.bridgetalkback.reports.dto.request.ReportsTalkRequestDto;
import org.springframework.data.repository.CrudRepository;

public interface TalkRedisRepository extends CrudRepository<ReportsTalkRequestDto, String> {

}
