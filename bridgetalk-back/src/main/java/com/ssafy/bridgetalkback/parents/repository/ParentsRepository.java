package com.ssafy.bridgetalkback.parents.repository;

import com.ssafy.bridgetalkback.parents.domain.Email;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParentsRepository extends JpaRepository<Parents, UUID> {
    boolean existsParentsByParentsEmail(Email email);
}
