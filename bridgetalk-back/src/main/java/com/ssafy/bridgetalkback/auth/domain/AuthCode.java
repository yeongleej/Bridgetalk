package com.ssafy.bridgetalkback.auth.domain;

import com.ssafy.bridgetalkback.parents.domain.Email;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash("authCode")
public class AuthCode {
    public static final Long DEFAULT_TTL = 600L; // 10분

    @Id
    private String email;

    private String authCode;

    @TimeToLive
    private Long expiration;

    private AuthCode(String email, String authCode) {
        this.email = email;
        this.authCode = authCode;
        this.expiration = DEFAULT_TTL;
    }

    public static AuthCode createAuthCode(String email, String authCode) {
        return new AuthCode(email, authCode);
    }

    public void updateAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
