package com.ssafy.bridgetalkback.auth.service;

import com.ssafy.bridgetalkback.auth.dto.request.EmailAuthRequestDto;
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

@DisplayName("Email [Service Layer] -> EmailService 테스트")
public class EmailServiceTest extends ServiceTest {
    @Autowired
    private AuthCodeService authCodeService;

    @Autowired
    private EmailService emailService;

    @Value("${MAIL_USERNAME}")
    private String userEmail;

    private Email email;
    private String authCode;
    private String invalidAuthCode;

    @BeforeEach
    void setup() {
        email = Email.from(userEmail);
        authCode = authCodeService.createAuthCode();
        invalidAuthCode = "0000000";
        authCodeRedisRepository.save(createAuthCode(email.getValue(), authCode));
    }

    @Nested
    @DisplayName("인증번호 검증")
    class verifyAuthCode {
        @Test
        @DisplayName("인증번호가 일치하지 않으면 인증번호 검증에 실패한다")
        void throwExceptionByWrongAuthCode() {
            // when - then
            assertThatThrownBy(() -> emailService.verifyAuthCode(createWrongEmailAuthRequestDto()))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(AuthErrorCode.WRONG_AUTH_CODE.getMessage());
        }

        @Test
        @DisplayName("인증번호 검증에 성공한다")
        void success() {
            // when - then
            boolean result = emailService.verifyAuthCode(createEmailAuthRequestDto());

            assertThat(result).isTrue();
        }
    }

    private EmailAuthRequestDto createEmailAuthRequestDto() {
        return new EmailAuthRequestDto(email.getValue(), authCode);
    }

    private EmailAuthRequestDto createWrongEmailAuthRequestDto() {
        return new EmailAuthRequestDto(email.getValue(), invalidAuthCode);
    }
}
