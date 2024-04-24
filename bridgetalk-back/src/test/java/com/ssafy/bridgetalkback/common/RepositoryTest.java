package com.ssafy.bridgetalkback.common;

import com.ssafy.bridgetalkback.global.config.JpaAuditingConfig;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(JpaAuditingConfig.class)
public class RepositoryTest {
}