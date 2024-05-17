package com.ssafy.bridgetalkback.parents.dto.request;

public record DeleteProfileRequestDto(
        String profileId,
        String password
) {
}
