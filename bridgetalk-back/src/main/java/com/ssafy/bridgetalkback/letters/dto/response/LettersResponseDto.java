package com.ssafy.bridgetalkback.letters.dto.response;

import com.ssafy.bridgetalkback.letters.domain.Letters;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LettersResponseDto(
        Long lettersId,
        String lettersOriginalContent,
        String lettersTranslationContent,
        LocalDateTime lettersRegDate,

        Integer isChecked

) {
    public static LettersResponseDto of(Letters letters) {
        return LettersResponseDto.builder()
                .lettersId(letters.getLettersId())
                .lettersOriginalContent(letters.getLettersOriginalContent())
                .lettersTranslationContent(letters.getLettersTranslationContent())
                .lettersRegDate(letters.getCreatedAt())
                .isChecked(letters.getIsChecked())
                .build();
    }
}
