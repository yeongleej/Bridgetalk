package com.ssafy.bridgetalkback.parents.controller;

import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.common.ControllerTest;
import com.ssafy.bridgetalkback.parents.dto.response.ProfileListResponseDto;
import com.ssafy.bridgetalkback.parents.dto.response.ProfileResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static com.ssafy.bridgetalkback.fixture.KidsFixture.HYUNYOUNG;
import static com.ssafy.bridgetalkback.fixture.KidsFixture.JIYEONG;
import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SUNKYOUNG;
import static com.ssafy.bridgetalkback.fixture.TokenFixture.BEARER_TOKEN;
import static com.ssafy.bridgetalkback.fixture.TokenFixture.REFRESH_TOKEN;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Parents [Controller Layer] -> ProfileListController 테스트")
public class ProfileListControllerTest extends ControllerTest {
    @Nested
    @DisplayName("프로필 리스트 조회 API [GET /api/profile]")
    class profileList {
        private static final String BASE_URL = "/api/profile";

        @Test
        @DisplayName("Authorization_Header에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL);

            // then
            final AuthErrorCode expectedError = AuthErrorCode.INVALID_PERMISSION;
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isForbidden(),
                            jsonPath("$.status").exists(),
                            jsonPath("$.status").value(expectedError.getStatus().value()),
                            jsonPath("$.errorCode").exists(),
                            jsonPath("$.errorCode").value(expectedError.getErrorCode()),
                            jsonPath("$.message").exists(),
                            jsonPath("$.message").value(expectedError.getMessage())
                    );

        }

        @Test
        @DisplayName("프로필 리스트 조회에 성공한다")
        void success() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(createProfileListResponseDto())
                    .when(profileListService)
                    .profileList(UUID.randomUUID());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());

        }
    }

    private ProfileListResponseDto createProfileListResponseDto() {
        List<ProfileResponseDto> profileList = new ArrayList<>();
        profileList.add(new ProfileResponseDto("7cfadd66-e491-4cb2-9d8f-6aa2e285dc46", SUNKYOUNG.getParentsName(), SUNKYOUNG.getParentsEmail(),
                SUNKYOUNG.getParentsNickname(), SUNKYOUNG.getParentsDino()));
        profileList.add(new ProfileResponseDto("5cfadd66-e491-4cb2-9d8f-6aa2e285dc46", JIYEONG.getKidsName(), JIYEONG.getKidsEmail(),
                JIYEONG.getKidsNickname(), JIYEONG.getKidsDino()));
        profileList.add(new ProfileResponseDto("6cfadd66-e491-4cb2-9d8f-6aa2e285dc46", HYUNYOUNG.getKidsName(), HYUNYOUNG.getKidsEmail(),
                HYUNYOUNG.getKidsNickname(), HYUNYOUNG.getKidsDino()));
        return new ProfileListResponseDto(profileList);
    }
}
