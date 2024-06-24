package com.ssafy.bridgetalkback.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailAuthRequestDto(
        @NotBlank(message = "이메일 입력은 필수입니다.")
        @Email(message = "이메일 형식에 맞게 입력해주세요.")
        String email,
        @NotBlank(message = "인증번호 입력은 필수입니다.")
        String authCode
) {
}
