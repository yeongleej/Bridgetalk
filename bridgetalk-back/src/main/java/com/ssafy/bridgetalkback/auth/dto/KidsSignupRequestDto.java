package com.ssafy.bridgetalkback.auth.dto;

public record KidsSignupRequestDto(
        String parentsId,
        String kidsName,
        String kidsNickname,
        String kidsDino
) {
}
