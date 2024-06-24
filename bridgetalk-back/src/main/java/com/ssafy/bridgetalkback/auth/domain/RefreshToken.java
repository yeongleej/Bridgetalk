package com.ssafy.bridgetalkback.auth.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash("Token")
public class RefreshToken {
    public static final Long DEFAULT_TTL = 1209600L; // 14일

    @Id
    private UUID uuid;

    private String refreshToken;

    @TimeToLive
    private Long expiration;

    private RefreshToken(UUID uuid, String refreshToken) {
        this.uuid = uuid;
        this.refreshToken = refreshToken;
        this.expiration = DEFAULT_TTL;
    }

    public static RefreshToken createRefreshToken(UUID userId, String refreshToken) {
        return new RefreshToken(userId, refreshToken);
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
