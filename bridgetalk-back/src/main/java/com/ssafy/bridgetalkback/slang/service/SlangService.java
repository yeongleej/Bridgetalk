package com.ssafy.bridgetalkback.slang.service;

import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.slang.domain.Slang;
import com.ssafy.bridgetalkback.slang.dto.response.SlangListResponseDto;
import com.ssafy.bridgetalkback.slang.exception.SlangErrorCode;
import com.ssafy.bridgetalkback.slang.repository.SlangRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SlangService {

    private final SlangRepository slangRepository;

    @Cacheable(cacheNames = "slangs", key = "'slangList-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<SlangListResponseDto> findAllSlang(Pageable pageable) {
        log.info("{SlangService} : 삭제되지 않은 모든 slang 리스트 반환");
        Page<Slang> slangByIsDeletedPage = findAllSlangByIsDeleted(pageable);

        return slangByIsDeletedPage.map(SlangListResponseDto::from);
    }

    public Page<Slang> findAllSlangByIsDeleted(Pageable pageable) {
        log.info("{Slang Service} : 삭제되지 않은 Slang 목록 조회");
        Page<Slang> slangPage = slangRepository.findAllByIsDeleted(0, pageable);
        if (slangPage.isEmpty()) {
            throw new BaseException(SlangErrorCode.SLANGLIST_NOT_FOUND);
        }
        return slangPage;
    }
}
