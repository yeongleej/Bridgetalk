package com.ssafy.bridgetalkback.auth.dto.request;

public record ProfileLoginRequestDto(
        String profileId,
        String password
) {
}
