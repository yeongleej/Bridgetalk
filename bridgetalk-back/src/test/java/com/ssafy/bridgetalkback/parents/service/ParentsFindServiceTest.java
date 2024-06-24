package com.ssafy.bridgetalkback.parents.service;

import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.parents.exception.ParentsErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SUNKYOUNG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Parents [Service Layer] -> ParentsFindService 테스트")
public class ParentsFindServiceTest extends ServiceTest {
    @Autowired
    private ParentsFindService parentsFindService;

    private Parents parents;

    @BeforeEach
    void setup() {
        parents = parentsRepository.save(SUNKYOUNG.toParents());
    }

    @Test
    @DisplayName("ID(PK)로 부모를 조회한다")
    void findParentsByUuidAndIsDeleted() {
        // when
        Parents findParents = parentsFindService.findParentsByUuidAndIsDeleted(parents.getUuid());
        UUID inVaildUuid = UUID.randomUUID();

        // then
        assertThatThrownBy(() -> parentsFindService.findParentsByUuidAndIsDeleted(inVaildUuid))
                .isInstanceOf(BaseException.class)
                .hasMessage(ParentsErrorCode.PARENTS_NOT_FOUND.getMessage());

        assertThat(findParents).isEqualTo(parents);
    }

    @Test
    @DisplayName("email로 부모를 조회한다")
    void findParentsByParentsEmailAndIsDeleted() {
        // when
        Parents findParents = parentsFindService.findParentsByParentsEmailAndIsDeleted(parents.getParentsEmail().getValue());

        // then
        assertThatThrownBy(() -> parentsFindService.findParentsByParentsEmailAndIsDeleted("inVaild@gmail.com"))
                .isInstanceOf(BaseException.class)
                .hasMessage(ParentsErrorCode.PARENTS_NOT_FOUND.getMessage());

        assertThat(findParents).isEqualTo(parents);
    }
}
