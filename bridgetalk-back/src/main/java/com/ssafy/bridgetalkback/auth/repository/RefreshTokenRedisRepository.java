package com.ssafy.bridgetalkback.auth.repository;

import com.ssafy.bridgetalkback.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, UUID> {
}
