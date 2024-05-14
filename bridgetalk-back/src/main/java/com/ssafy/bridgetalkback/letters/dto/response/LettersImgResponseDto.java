package com.ssafy.bridgetalkback.letters.dto.response;

import com.ssafy.bridgetalkback.letters.domain.LettersImg;
import lombok.Builder;

@Builder
public record LettersImgResponseDto(

        String keyword,
        String lettersImgUrl
) {
    public static LettersImgResponseDto of(LettersImg lettersImg) {
        return LettersImgResponseDto.builder()
                .lettersImgUrl(lettersImg.getLettersImgUrl())
                .keyword(lettersImg.getKeyword())
                .build();
    }

}
