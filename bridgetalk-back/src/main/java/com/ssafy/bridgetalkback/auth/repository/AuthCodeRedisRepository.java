package com.ssafy.bridgetalkback.auth.repository;

import com.ssafy.bridgetalkback.auth.domain.AuthCode;
import org.springframework.data.repository.CrudRepository;

public interface AuthCodeRedisRepository extends CrudRepository<AuthCode, String> {
}
