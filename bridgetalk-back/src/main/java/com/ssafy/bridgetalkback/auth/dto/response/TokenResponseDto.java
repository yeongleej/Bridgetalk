package com.ssafy.bridgetalkback.auth.dto.response;

public record TokenResponseDto(
        String accessToken,
        String refreshToken
) {
}
