package com.ssafy.bridgetalkback.letters.repository;

import com.ssafy.bridgetalkback.letters.domain.LettersImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LettersImgRepository extends JpaRepository<LettersImg, Long> {
//    List<LettersImg> findAllByLettersAndIsDeleted(Letters letters, int isDeleted);

    Optional<LettersImg> findLettersImgByKeywordAndIsDeleted(String keyword, int isDeleted);

}
