package com.ssafy.bridgetalkback.auth.dto.request;

import com.ssafy.bridgetalkback.global.Language;

public record ParentsSignupRequestDto(
        String parentsEmail,
        String parentsPassword,
        String parentsName,
        String parentsNickname,
        String parentsDino,
        Language language
) {
}
