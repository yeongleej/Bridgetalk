package com.ssafy.bridgetalkback.global.security;

import java.util.UUID;

public record UserDetailDto(
        UUID uuid,
        String email,
        String name,
        String role
) {
}
