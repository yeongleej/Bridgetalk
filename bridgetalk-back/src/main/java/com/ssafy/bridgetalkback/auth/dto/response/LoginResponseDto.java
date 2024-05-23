package com.ssafy.bridgetalkback.auth.dto.response;

import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import lombok.Builder;

@Builder
public record LoginResponseDto(
        String userId,
        String userName,
        String userEmail,
        String userNickname,
        String userDino,
        String accessToken,
        String refreshToken,
        Language language
) {
    public static LoginResponseDto fromParents(Parents parents, String accessToken, String refreshToken) {
        return LoginResponseDto.builder()
                .userId(String.valueOf(parents.getUuid()))
                .userName(parents.getParentsName())
                .userEmail(parents.getParentsEmail().getValue())
                .userNickname(parents.getParentsNickname())
                .userDino(parents.getParentsDino())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .language(parents.getLanguage())
                .build();
    }

    public static LoginResponseDto fromKids(Kids kids, String accessToken, String refreshToken) {
        return LoginResponseDto.builder()
                .userId(String.valueOf(kids.getUuid()))
                .userName(kids.getKidsName())
                .userEmail(kids.getKidsEmail())
                .userNickname(kids.getKidsNickname())
                .userDino(kids.getKidsDino())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
