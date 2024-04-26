package com.ssafy.bridgetalkback.auth.dto;

import java.util.UUID;

public record KidsSingupRequestDto(
        String parentsId,
        String kidsName,
        String kidsNickname,
        String kidsDino
) {
}
