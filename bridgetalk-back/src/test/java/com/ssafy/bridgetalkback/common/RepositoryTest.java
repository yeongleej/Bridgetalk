package com.ssafy.bridgetalkback.common;

import com.ssafy.bridgetalkback.global.config.JpaAuditingConfig;
import com.ssafy.bridgetalkback.global.config.QueryDslConfig;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({QueryDslConfig.class, JpaAuditingConfig.class})
public class RepositoryTest {
}