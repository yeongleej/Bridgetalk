package com.ssafy.bridgetalkback.auth.dto;

import java.util.UUID;

public record KidsSingupRequestDto(
        UUID parentsId,
        String kidsName,
        String kidsNickname,
        String kidsDino
) {
}
