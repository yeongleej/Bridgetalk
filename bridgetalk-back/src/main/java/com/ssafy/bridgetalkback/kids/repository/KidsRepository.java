package com.ssafy.bridgetalkback.kids.repository;

import com.ssafy.bridgetalkback.kids.domain.Kids;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KidsRepository extends JpaRepository<Kids, UUID> {
}
