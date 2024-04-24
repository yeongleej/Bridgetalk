package com.ssafy.bridgetalkback.letters.domain.repository;

import com.ssafy.bridgetalkback.letters.domain.Letters;
import com.ssafy.bridgetalkback.parent.domain.Parents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LettersRepository extends JpaRepository<Letters, Long> {

}
