package com.ssafy.bridgetalkback.auth.dto.request;

public record KidsSignupRequestDto(
        String parentsId,
        String kidsName,
        String kidsNickname,
        String kidsDino
) {
}
