package com.ssafy.bridgetalkback.letters.dto.response;

/**
 * TranslationResultsDTO : papago api responseDTO
 * @param srcLangType : 원본 언어
 * @param tarLangType : 번역본 언어
 * @param translatedText : 번역본 텍스트
 * */
public record TranslationResultsDto(
        String srcLangType,
        String tarLangType,
        String translatedText
) {
}
