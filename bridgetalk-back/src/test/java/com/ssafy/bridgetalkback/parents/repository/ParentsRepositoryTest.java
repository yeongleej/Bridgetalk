package com.ssafy.bridgetalkback.parents.repository;

import com.ssafy.bridgetalkback.common.RepositoryTest;
import com.ssafy.bridgetalkback.parents.domain.Email;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SUNKYOUNG;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Parents [Repository Test] -> ParentsRepository 테스트")
public class ParentsRepositoryTest extends RepositoryTest {
    @Autowired
    private ParentsRepository parentsRepository;

    private Parents parents;

    @BeforeEach
    void setUp() {
        parents = parentsRepository.save(SUNKYOUNG.toParents());
    }

    @Test
    @DisplayName("uuid(PK)가 일치하고 탈퇴하지 않은 회원의 존재 여부를 확인한다")
    void existsParentsByUuidAndIsDeleted() {
        boolean actual1 = parentsRepository.existsParentsByUuidAndIsDeleted(parents.getUuid(), 0);
        boolean actual2 = parentsRepository.existsParentsByUuidAndIsDeleted(UUID.randomUUID(), 0);

        // then
        Assertions.assertAll(
                () -> assertThat(actual1).isTrue(),
                () -> assertThat(actual2).isFalse()
        );
    }

    @Test
    @DisplayName("email이 일치하고 탈퇴하지 않은 회원의 존재 여부를 확인한다")
    void existsParentsByParentsEmail() {
        boolean actual1 = parentsRepository.existsParentsByParentsEmail(parents.getParentsEmail());
        boolean actual2 = parentsRepository.existsParentsByParentsEmail(Email.from("123@naver.com"));

        // then
        Assertions.assertAll(
                () -> assertThat(actual1).isTrue(),
                () -> assertThat(actual2).isFalse()
        );
    }
}
