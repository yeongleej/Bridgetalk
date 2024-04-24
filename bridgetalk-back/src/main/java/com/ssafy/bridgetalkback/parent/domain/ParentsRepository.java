package com.ssafy.bridgetalkback.parent.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParentsRepository extends JpaRepository<Parents, UUID> {
}
