package com.ssafy.bridgetalkback.parents.domain;

import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.parents.exception.ParentsErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Password {
    private static final String PASSWORD_PATTERN1 = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,20}$"; //대소문자 1개이상, 숫자 1개이상, 8~20자이내
    private static final String PASSWORD_PATTERN2 = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*+=-]).{8,20}$"; //대소문자 1개이상, 숫자 1개이상, 특수문자1개이상, 8~20자이내
    private static final Pattern PASSWORD_MATCHER1 = Pattern.compile(PASSWORD_PATTERN1);
    private static final Pattern PASSWORD_MATCHER2 = Pattern.compile(PASSWORD_PATTERN2);

    @Column(name = "parents_password", nullable = false, length = 200)
    private String value;

    private Password(String value) {
        this.value = value;
    }

    public static Password encrypt(String value, PasswordEncoder encoder) {
        validatePasswordPattern(value);
        return new Password(encoder.encode(value));
    }

    private static void validatePasswordPattern(String value) {
        if (!isValidPattern(value)) {
            throw BaseException.type(ParentsErrorCode.INVALID_PASSWORD_PATTERN);
        }
    }

    public boolean isSamePassword(String comparePassword, PasswordEncoder encoder) {
        return encoder.matches(comparePassword, this.value);
    }

    private static boolean isValidPattern(String password) {
        return PASSWORD_MATCHER1.matcher(password).matches() || PASSWORD_MATCHER2.matcher(password).matches();
    }
}
