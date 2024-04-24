package com.ssafy.bridgetalkback.letters.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class LettersRequestDTO {

    private final String reportsId;
    private final MultipartFile lettersFile;

    @Builder
    public LettersRequestDTO(String reportsId, MultipartFile lettersFile) {
        this.reportsId = reportsId;
        this.lettersFile = lettersFile;
    }
}
