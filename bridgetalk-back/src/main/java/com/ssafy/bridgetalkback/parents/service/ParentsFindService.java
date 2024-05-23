package com.ssafy.bridgetalkback.parents.service;

import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.parents.domain.Email;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.parents.exception.ParentsErrorCode;
import com.ssafy.bridgetalkback.parents.repository.ParentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ParentsFindService {
    private final ParentsRepository parentsRepository;

    public Parents findParentsByUuidAndIsDeleted(UUID uuid) {
        log.info("{ ParentsFindService } : Id(Pk)로 부모 정보 조회 - "+uuid);
        return parentsRepository.findParentsByUuidAndIsDeleted(uuid, 0)
                .orElseThrow(() -> BaseException.type(ParentsErrorCode.PARENTS_NOT_FOUND));
    }

    public Parents findParentsByParentsEmailAndIsDeleted(String email) {
        log.info("{ ParentsFindService } : email로 부모 정보 조회 - "+email);
        return parentsRepository.findParentsByParentsEmailAndIsDeleted(Email.from(email), 0)
                .orElseThrow(() -> BaseException.type(ParentsErrorCode.PARENTS_NOT_FOUND));
    }

    public boolean existsParentsByUuidAndIsDeleted(UUID uuid) {
        return parentsRepository.existsParentsByUuidAndIsDeleted(uuid, 0);
    }
}
