package com.ssafy.bridgetalkback.kid.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KidsRepository extends JpaRepository<Kids, UUID> {
}
