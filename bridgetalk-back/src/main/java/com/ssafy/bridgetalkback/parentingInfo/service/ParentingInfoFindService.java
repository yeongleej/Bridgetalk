package com.ssafy.bridgetalkback.parentingInfo.service;

import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.parentingInfo.domain.ParentingInfo;
import com.ssafy.bridgetalkback.parentingInfo.exception.ParentingInfoErrorCode;
import com.ssafy.bridgetalkback.parentingInfo.repository.ParentingInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ParentingInfoFindService {
    private final ParentingInfoRepository parentingInfoRepository;

    public ParentingInfo findParentingInfoByParentingInfoIdAndIsDeleted(Long id) {
        log.info("{ ParentingInfoFindService } : Id(Pk)로 육아 정보 조회 - "+id);
        return parentingInfoRepository.findParentingInfoByParentingInfoIdAndIsDeleted(id, 0)
                .orElseThrow(() -> BaseException.type(ParentingInfoErrorCode.PARENTINGINFO_NOT_FOUND));
    }
}
