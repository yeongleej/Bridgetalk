package com.ssafy.bridgetalkback.auth.dto;

public record ParentsSignupRequestDto(
        String parentsEmail,
        String parentsPassword,
        String parentsName,
        String parentsNickname,
        String parentsDino
) {
}
