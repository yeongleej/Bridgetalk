package com.ssafy.bridgetalkback.parents.domain;

import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.parents.exception.ParentsErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Email {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";
    private static final Pattern EMAIL_MATCHER = Pattern.compile(EMAIL_PATTERN);

    @Column(name = "parents_email", nullable = false, unique = true, updatable = false, length = 30)
    private String value;

    private Email(String value){
        this.value = value;
    }

    public static Email from(String value) {
        validateEmailPattern(value);
        return new Email(value);
    }

    private static void validateEmailPattern(String value) {
        if (!isValidPattern(value)) {
            throw BaseException.type(ParentsErrorCode.INVALID_EMAIL_FORMAT);
        }
    }

    public boolean isSameEmail(Email email){
        return this.value.equals(email.getValue());
    }

    private static boolean isValidPattern(String email) {
        return EMAIL_MATCHER.matcher(email).matches();
    }
}
