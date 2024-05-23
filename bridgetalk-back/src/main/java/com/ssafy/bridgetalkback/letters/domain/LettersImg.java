package com.ssafy.bridgetalkback.letters.domain;

import com.ssafy.bridgetalkback.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "letters_img")
public class LettersImg extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lettersImgId;

    @Column(nullable = false)
    private String lettersImgUrl;

    @Column(nullable = false)
    private String keyword;

    private LettersImg(String keyword, String lettersImgUrl) {
        this.keyword = keyword;
        this.lettersImgUrl = lettersImgUrl;
    }

    public static LettersImg createLettersImg(String keyword, String lettersImgUrl) {
        return new LettersImg(keyword, lettersImgUrl);
    }


}
