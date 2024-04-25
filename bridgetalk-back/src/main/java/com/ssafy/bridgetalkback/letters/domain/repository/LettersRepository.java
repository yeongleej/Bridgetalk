package com.ssafy.bridgetalkback.letters.domain.repository;

import com.ssafy.bridgetalkback.letters.domain.Letters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LettersRepository extends JpaRepository<Letters, Long> {

}
