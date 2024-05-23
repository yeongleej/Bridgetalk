package com.ssafy.bridgetalkback.slang.dto.response;

import com.ssafy.bridgetalkback.slang.domain.Slang;
import lombok.Builder;

@Builder
public record SlangListResponseDto(
        Long slangId,

        String slangWord,

        String originalWord,

        String meaning,

        String vietnamesePronunciation,

        String vietnameseTranslation
) {
    public static SlangListResponseDto from(Slang slang) {
        return SlangListResponseDto.builder()
                .slangId(slang.getSlangId())
                .slangWord(slang.getSlangWord())
                .originalWord(slang.getOriginalWord())
                .meaning(slang.getMeaning())
                .vietnamesePronunciation(slang.getVietnamesePronunciation())
                .vietnameseTranslation(slang.getVietnameseTranslation())
                .build();
    }
}
