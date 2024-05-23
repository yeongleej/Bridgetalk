package com.ssafy.bridgetalkback.parents.controller;

import com.ssafy.bridgetalkback.auth.dto.request.ProfileLoginRequestDto;
import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.common.ControllerTest;
import com.ssafy.bridgetalkback.parents.dto.request.DeleteProfileRequestDto;
import com.ssafy.bridgetalkback.parents.dto.request.UpdateProfileRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SUNKYOUNG;
import static com.ssafy.bridgetalkback.fixture.TokenFixture.BEARER_TOKEN;
import static com.ssafy.bridgetalkback.fixture.TokenFixture.REFRESH_TOKEN;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Profile [Controller Layer] -> ProfileController 테스트")
public class ProfileControllerTest extends ControllerTest {
    @Nested
    @DisplayName("프로필 수정 API [PATCH /api/profile/{profileId}]")
    class updateProfile {
        private static final String BASE_URL = "/api/profile/{profileId}";
        private static final String PROFILE_ID = "7cfadd66-e491-4cb2-9d8f-6aa2e285dc46";

        @Test
        @DisplayName("Authorization_Header에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            // when
            final UpdateProfileRequestDto request = createUpdateProfileRequestDto();
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .patch(BASE_URL, PROFILE_ID)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(request));

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
        @DisplayName("프로필 수정에 성공한다")
        void success() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            final UpdateProfileRequestDto request = createUpdateProfileRequestDto();
            doNothing()
                    .when(profileService)
                    .updateProfile(UUID.randomUUID(), request);

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .patch(BASE_URL, PROFILE_ID)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(request))
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());

        }
    }

    @Nested
    @DisplayName("프로필 삭제 API [DELETE /api/profile]")
    class deleteProfile {
        private static final String BASE_URL = "/api/profile";

        @Test
        @DisplayName("Authorization_Header에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            // when
            final DeleteProfileRequestDto request = createDeleteProfileRequestDto();
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .delete(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(request));

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
        @DisplayName("프로필 삭제에 성공한다")
        void success() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            final DeleteProfileRequestDto request = createDeleteProfileRequestDto();
            doNothing()
                    .when(profileService)
                    .deleteProfile(request);

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .delete(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(request))
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());

        }
    }

    private UpdateProfileRequestDto createUpdateProfileRequestDto() {
        return new UpdateProfileRequestDto("새로운 닉네임", "D3");
    }

    private DeleteProfileRequestDto createDeleteProfileRequestDto() {
        return new DeleteProfileRequestDto("7cfadd66-e491-4cb2-9d8f-6aa2e285dc46", SUNKYOUNG.getParentsPassword());
    }
}
