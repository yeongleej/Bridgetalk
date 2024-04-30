package com.ssafy.bridgetalkback.auth.dto;

public record TokenResponseDto(
        String accessToken,
        String refreshToken
) {
}
