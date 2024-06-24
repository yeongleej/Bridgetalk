package com.ssafy.bridgetalkback.kids.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class KidsPassword {
    @Column(name = "kids_password", nullable = false, length = 200)
    private String value;

    private KidsPassword(String value) {
        this.value = value;
    }

    public static KidsPassword encrypt(String value, PasswordEncoder encoder) {
        return new KidsPassword(encoder.encode(value));
    }

    public boolean isSamePassword(String comparePassword, PasswordEncoder encoder) {
        return encoder.matches(comparePassword, this.value);
    }
}
