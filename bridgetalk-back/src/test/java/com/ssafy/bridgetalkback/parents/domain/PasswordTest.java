package com.ssafy.bridgetalkback.parents.domain;

import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.parents.exception.ParentsErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.ssafy.bridgetalkback.global.utils.PasswordEncoderUtils.ENCODER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Password VO 테스트")
public class PasswordTest {
    @ParameterizedTest
    @ValueSource(strings = {"password", "12345678!", "abcdefg!", "12345678910", "Abcd", "!!!!!!!!"})
    @DisplayName("비밀번호 패턴에 맞지 않아 비밀번호 생성에 실패한다")
    void throwExceptionByInvaildPassword(String value) {
        assertThatThrownBy(() -> Password.encrypt(value, ENCODER))
                .isInstanceOf(BaseException.class)
                .hasMessage(ParentsErrorCode.INVALID_PASSWORD_PATTERN.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"ssafy123", "ssafy1!!", "password1234", "password123@@", "password123^^^"})
    @DisplayName("비밀번호 생성에 성공한다")
    void createPassword(String value) {
        Password password = Password.encrypt(value, ENCODER);

        assertThat(password.isSamePassword(value, ENCODER)).isTrue();
    }
}
