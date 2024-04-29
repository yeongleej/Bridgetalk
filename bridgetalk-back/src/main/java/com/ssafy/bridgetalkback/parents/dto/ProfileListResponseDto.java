package com.ssafy.bridgetalkback.parents.dto;

import java.util.List;

public record ProfileListResponseDto(
        List<ProfileResponseDto> profileList
) {
}
