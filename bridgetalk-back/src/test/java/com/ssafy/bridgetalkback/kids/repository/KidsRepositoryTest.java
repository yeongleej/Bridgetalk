package com.ssafy.bridgetalkback.kids.repository;

import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static com.ssafy.bridgetalkback.fixture.KidsFixture.JIYEONG;
import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SUNKYOUNG;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Kids [Repository Test] -> KidsRepository 테스트")
public class KidsRepositoryTest extends ServiceTest {
    @Autowired
    private KidsRepository kidsRepository;

    private Parents parents;
    private Kids kids;

    @BeforeEach
    void setUp() {
        parents = parentsRepository.save(SUNKYOUNG.toParents());
        kids = kidsRepository.save(JIYEONG.toKids(parents));
    }

    @Test
    @DisplayName("uuid(PK)가 일치하고 탈퇴하지 않은 회원의 존재 여부를 확인한다")
    void existsKidsByUuidAndIsDeleted() {
        boolean actual1 = kidsRepository.existsKidsByUuidAndIsDeleted(kids.getUuid(), 0);
        boolean actual2 = kidsRepository.existsKidsByUuidAndIsDeleted(UUID.randomUUID(), 0);

        // then
        Assertions.assertAll(
                () -> assertThat(actual1).isTrue(),
                () -> assertThat(actual2).isFalse()
        );
    }
}
