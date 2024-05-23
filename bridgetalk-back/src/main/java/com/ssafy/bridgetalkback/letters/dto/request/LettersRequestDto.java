package com.ssafy.bridgetalkback.letters.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record LettersRequestDto(
        Long reportsId,
        @NotBlank(message = "편지파일은 필수입니다.")
        MultipartFile lettersFile
) {
}
