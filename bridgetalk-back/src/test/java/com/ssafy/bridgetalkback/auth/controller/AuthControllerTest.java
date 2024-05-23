package com.ssafy.bridgetalkback.auth.controller;

import com.ssafy.bridgetalkback.auth.dto.request.KidsSignupRequestDto;
import com.ssafy.bridgetalkback.auth.dto.request.LoginRequestDto;
import com.ssafy.bridgetalkback.auth.dto.request.ProfileLoginRequestDto;
import com.ssafy.bridgetalkback.auth.dto.response.LoginResponseDto;
import com.ssafy.bridgetalkback.auth.dto.request.ParentsSignupRequestDto;
import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.common.ControllerTest;
import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static com.ssafy.bridgetalkback.fixture.KidsFixture.JIYEONG;
import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SUNKYOUNG;
import static com.ssafy.bridgetalkback.fixture.TokenFixture.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Auth [Controller Layer] -> AuthController 테스트")
public class AuthControllerTest extends ControllerTest {
    @Nested
    @DisplayName("회원가입(부모) API [POST /api/auth/signup]")
    class signup {
        private static final String BASE_URL = "/api/auth/signup";

        @Test
        @DisplayName("중복된 이메일이라면 회원가입에 실패한다")
        void throwExceptionByDuplicateEmail() throws Exception {
            // given
            doThrow(BaseException.type(AuthErrorCode.DUPLICATE_EMAIL))
                    .when(authService)
                    .signup(any());

            // when
            final ParentsSignupRequestDto request = createParentsSignupRequestDto();
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(request));

            // then
            final AuthErrorCode expectedError = AuthErrorCode.DUPLICATE_EMAIL;
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isBadRequest(),
                            jsonPath("$.status").exists(),
                            jsonPath("$.status").value(expectedError.getStatus().value()),
                            jsonPath("$.errorCode").exists(),
                            jsonPath("$.errorCode").value(expectedError.getErrorCode()),
                            jsonPath("$.message").exists(),
                            jsonPath("$.message").value(expectedError.getMessage())
                    );

        }

        @Test
        @DisplayName("회원가입에 성공한다")
        void success() throws Exception {
            // given
            doReturn(UUID.randomUUID())
                    .when(authService)
                    .signup(any());

            // when
            final ParentsSignupRequestDto request = createParentsSignupRequestDto();
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(request));

            // then
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isOk()
                    );
        }
    }

    @Nested
    @DisplayName("로그인(부모) API [POST /api/auth/login]")
    class login {
        private static final String BASE_URL = "/api/auth/login";

        @Test
        @DisplayName("불일치하는 비밀번호라면 로그인에 실패한다")
        void throwExceptionByWrongPassword() throws Exception {
            // given
            doThrow(BaseException.type(AuthErrorCode.WRONG_PASSWORD))
                    .when(authService)
                    .login(any());

            // when
            final LoginRequestDto request = createLoginRequestDto();
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(request));

            // then
            final AuthErrorCode expectedError = AuthErrorCode.WRONG_PASSWORD;
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isUnauthorized(),
                            jsonPath("$.status").exists(),
                            jsonPath("$.status").value(expectedError.getStatus().value()),
                            jsonPath("$.errorCode").exists(),
                            jsonPath("$.errorCode").value(expectedError.getErrorCode()),
                            jsonPath("$.message").exists(),
                            jsonPath("$.message").value(expectedError.getMessage())
                    );

        }

        @Test
        @DisplayName("로그인에 성공한다")
        void success() throws Exception {
            // given
            doReturn(loginResponseDto())
                    .when(authService)
                    .login(any());

            // when
            final LoginRequestDto request = createLoginRequestDto();
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(request));

            // then
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isOk()
                    );
        }
    }

    @Nested
    @DisplayName("로그아웃 API 테스트 [GET /api/auth/logout]")
    class logout {
        private static final String BASE_URL = "/api/auth/logout";

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
        @DisplayName("로그아웃에 성공한다")
        void success() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doNothing()
                    .when(authService)
                    .logout(UUID.randomUUID());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isOk()
                    );
        }
    }

    @Nested
    @DisplayName("아이 회원가입 (프로필 추가) API 테스트 [GET /api/auth/kids]")
    class kidsSingup {
        private static final String BASE_URL = "/api/auth/kids";

        @Test
        @DisplayName("아이 회원가입(프로필 추가)에 성공한다")
        void success() throws Exception {
            // given
            doReturn(UUID.randomUUID())
                    .when(authService)
                    .kidsSignup(any());

            // when
            final KidsSignupRequestDto request = createKidsSingupRequestDto();
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(request));

            // then
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isOk()
                    );
        }
    }

    @Nested
    @DisplayName("프로필 선택 (로그인) API 테스트 [GET /api/auth/profileLogin]")
    class profileLogin {
        private static final String BASE_URL = "/api/auth/profileLogin";

        @Test
        @DisplayName("Authorization_Header에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            // when
            final ProfileLoginRequestDto request = createProfileLoginRequestDto();
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
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
        @DisplayName("프로필 선택 (로그인)에 성공한다")
        void success() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            final ProfileLoginRequestDto request = createProfileLoginRequestDto();
            doReturn(loginResponseDto())
                    .when(authService)
                    .profileLogin(request);

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(request))
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isOk()
                    );
        }
    }

    @Nested
    @DisplayName("중복 닉네임 검사 API [POST /api/auth/nickname-duplicate/{nickname}]")
    class duplicateNickname {
        private static final String BASE_URL = "/api/auth/nickname-duplicate/{nickname}";
        private static final String NICKNAME = "닉네임";
        private static final String DUPLICATE_NICKNAME = "중복된 닉네임";

        @Test
        @DisplayName("중복된 닉네임이라면 회원가입에 실패한다")
        void throwExceptionByDuplicateNickname() throws Exception {
            // given
            doThrow(BaseException.type(AuthErrorCode.DUPLICATE_NICKNAME))
                    .when(authService)
                    .duplicateNickname(any());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL, DUPLICATE_NICKNAME)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            final AuthErrorCode expectedError = AuthErrorCode.DUPLICATE_NICKNAME;
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isBadRequest(),
                            jsonPath("$.status").exists(),
                            jsonPath("$.status").value(expectedError.getStatus().value()),
                            jsonPath("$.errorCode").exists(),
                            jsonPath("$.errorCode").value(expectedError.getErrorCode()),
                            jsonPath("$.message").exists(),
                            jsonPath("$.message").value(expectedError.getMessage())
                    );

        }

        @Test
        @DisplayName("닉네임 중복 검사에 성공한다")
        void success() throws Exception {
            // given
            doNothing()
                    .when(authService)
                    .duplicateNickname(any());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL, NICKNAME)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isOk()
                    );
        }
    }

    private ParentsSignupRequestDto createParentsSignupRequestDto() {
        return new ParentsSignupRequestDto(SUNKYOUNG.getParentsEmail(), SUNKYOUNG.getParentsPassword(), SUNKYOUNG.getParentsName(),
                SUNKYOUNG.getParentsNickname(), SUNKYOUNG.getParentsDino(), SUNKYOUNG.getLanguage());
    }

    private LoginRequestDto createLoginRequestDto() {
        return new LoginRequestDto(SUNKYOUNG.getParentsEmail(), SUNKYOUNG.getParentsPassword());
    }

    private LoginResponseDto loginResponseDto() {
        return new LoginResponseDto("7cfadd66-e491-4cb2-9d8f-6aa2e285dc46", SUNKYOUNG.getParentsName(), SUNKYOUNG.getParentsEmail(), SUNKYOUNG.getParentsNickname(),
                SUNKYOUNG.getParentsDino(), ACCESS_TOKEN, REFRESH_TOKEN, Language.kor);
    }

    private KidsSignupRequestDto createKidsSingupRequestDto() {
        return new KidsSignupRequestDto("7cfadd66-e491-4cb2-9d8f-6aa2e285dc46", JIYEONG.getKidsName(), JIYEONG.getKidsNickname(), JIYEONG.getKidsDino(),
                JIYEONG.getKidsPassword());
    }

    private ProfileLoginRequestDto createProfileLoginRequestDto() {
        return new ProfileLoginRequestDto("7cfadd66-e491-4cb2-9d8f-6aa2e285dc46", SUNKYOUNG.getParentsPassword());
    }
}
