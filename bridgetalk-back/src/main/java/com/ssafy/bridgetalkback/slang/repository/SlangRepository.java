package com.ssafy.bridgetalkback.slang.repository;

import com.ssafy.bridgetalkback.slang.domain.Slang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlangRepository extends JpaRepository<Slang, Long> {
    List<Slang> findAllByIsDeleted(int isDeleted);
}
