package com.ssafy.bridgetalkback.parents.dto.response;

import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import lombok.Builder;

@Builder
public record ProfileResponseDto(
        String userId,
        String userName,
        String userEmail,
        String userNickname,
        String userDino
) {
    public static ProfileResponseDto fromParents(Parents parents) {
        return ProfileResponseDto.builder()
                .userId(String.valueOf(parents.getUuid()))
                .userName(parents.getParentsName())
                .userEmail(parents.getParentsEmail().getValue())
                .userNickname(parents.getParentsNickname())
                .userDino(parents.getParentsDino())
                .build();
    }

    public static ProfileResponseDto fromKids(Kids kids) {
        return ProfileResponseDto.builder()
                .userId(String.valueOf(kids.getUuid()))
                .userName(kids.getKidsName())
                .userEmail(kids.getKidsEmail())
                .userNickname(kids.getKidsNickname())
                .userDino(kids.getKidsDino())
                .build();
    }
}