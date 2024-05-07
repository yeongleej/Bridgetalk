package com.ssafy.bridgetalkback.slang.repository;

import com.ssafy.bridgetalkback.slang.domain.Slang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlangRepository extends JpaRepository<Slang, Long> {
    Page<Slang> findAllByIsDeleted(int isDeleted, Pageable pageable);
}
