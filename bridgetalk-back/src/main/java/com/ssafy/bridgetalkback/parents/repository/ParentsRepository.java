package com.ssafy.bridgetalkback.parents.repository;

import com.ssafy.bridgetalkback.parents.domain.Email;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ParentsRepository extends JpaRepository<Parents, UUID> {
    // 중복 이메일 검증
    boolean existsParentsByParentsEmail(Email email);

    Optional<Parents> findParentsByUuidAndIsDeleted(UUID uuid, int isDeleted);

    Optional<Parents> findParentsByParentsEmailAndIsDeleted(Email parentsEmail, int isDeleted);

    boolean existsParentsByUuidAndIsDeleted(UUID uuid, int isDeleted);

    boolean existsParentsByParentsNicknameAndIsDeleted(String parentsNickname, int isDeleted);
}
