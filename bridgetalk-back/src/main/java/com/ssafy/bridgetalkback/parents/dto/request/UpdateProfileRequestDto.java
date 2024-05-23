package com.ssafy.bridgetalkback.parents.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateProfileRequestDto(
        @NotBlank(message = "닉네임은 필수입니다.")
        String nickname,
        @NotBlank(message = "공룡 번호는 필수입니다.")
        String dino
) {
}
