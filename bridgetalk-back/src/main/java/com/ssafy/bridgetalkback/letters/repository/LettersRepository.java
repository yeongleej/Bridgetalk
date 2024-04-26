package com.ssafy.bridgetalkback.letters.repository;

import com.ssafy.bridgetalkback.letters.domain.Letters;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.reports.domain.Reports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LettersRepository extends JpaRepository<Letters, Long> {

    Optional<Letters> findByLettersIdAndIsDeletedFalse(Long lettersId);



}
