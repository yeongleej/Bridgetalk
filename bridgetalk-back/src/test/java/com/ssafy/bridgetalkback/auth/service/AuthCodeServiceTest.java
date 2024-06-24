package com.ssafy.bridgetalkback.auth.service;

import com.ssafy.bridgetalkback.auth.domain.AuthCode;
import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.parents.domain.Email;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.ssafy.bridgetalkback.auth.domain.AuthCode.createAuthCode;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("AuthCode [Service Layer] -> AuthCodeService 테스트")
public class AuthCodeServiceTest extends ServiceTest {
    @Autowired
    private AuthCodeService authCodeService;

    @Value("${MAIL_USERNAME}")
    private String userEmail;

    private Email email;

    @BeforeEach
    void setup() {
        email = Email.from(userEmail);
    }

    @Test
    @DisplayName("인증번호 발급에 성공한다")
    void saveAuthCode() {
        // when
        String authCode = authCodeService.saveAuthCode(email);

        // then
        AuthCode findAuthCode = authCodeService.findAuthCodeByEmail(email);
        assertThat(findAuthCode.getAuthCode()).isEqualTo(authCode);
    }

    @Test
    @DisplayName("이메일에 해당하는 인증번호 찾는다")
    void success() {
        // given
        String authCode = authCodeService.createAuthCode();
        authCodeRedisRepository.save(createAuthCode(email.getValue(), authCode));

        // when
        AuthCode findAuthCode = authCodeService.findAuthCodeByEmail(email);

        // then
        assertThatThrownBy(() -> authCodeService.findAuthCodeByEmail(Email.from("wrong"+userEmail)))
                .isInstanceOf(BaseException.class)
                .hasMessage(AuthErrorCode.AUTH_CODE_NOT_FOUND.getMessage());

        assertThat(findAuthCode.getAuthCode()).isEqualTo(authCode);
    }
}
