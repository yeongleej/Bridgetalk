package com.ssafy.bridgetalkback.parents.dto.response;

import java.util.List;

public record ProfileListResponseDto(
        List<ProfileResponseDto> profileList
) {
}
