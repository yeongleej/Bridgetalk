package com.ssafy.bridgetalkback.letters.dto.request;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record LettersRequestDto(
        Long reportsId,
        MultipartFile lettersFile
) {
}
