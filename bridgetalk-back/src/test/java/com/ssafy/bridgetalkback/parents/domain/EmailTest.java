package com.ssafy.bridgetalkback.parents.domain;

import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.parents.exception.ParentsErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Email VO 테스트")
public class EmailTest {
    @ParameterizedTest
    @ValueSource(strings = {"abc@gmail", "@gmail.com", "aaa", "ssafy123"})
    @DisplayName("이메일 형식에 맞지 않아 이메일 저장에 실패한다")
    void throwExceptionByInvalidEmailFormat(String value) {
        assertThatThrownBy(() -> Email.from(value))
                .isInstanceOf(BaseException.class)
                .hasMessage(ParentsErrorCode.INVALID_EMAIL_FORMAT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc@gmail.com", "ssafy123@naver.com", "bridgetalk@bridgetalk.co.kr"})
    @DisplayName("이메일 저장에 성공한다")
    void success(String value) {
        Email email = Email.from(value);
        assertThat(email.getValue()).isEqualTo(value);
    }
}
