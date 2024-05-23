package com.ssafy.bridgetalkback.kids.service;

import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.kids.exception.KidsErrorCode;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static com.ssafy.bridgetalkback.fixture.KidsFixture.JIYEONG;
import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SUNKYOUNG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Kids [Service Layer] -> KidsFindService 테스트")
public class KidsFindServiceTest extends ServiceTest {
    @Autowired
    private KidsFindService kidsFindService;

    private Parents parents;
    private Kids kids;

    @BeforeEach
    void setup() {
        parents = parentsRepository.save(SUNKYOUNG.toParents());
        kids = kidsRepository.save(JIYEONG.toKids(parents));
    }

    @Test
    @DisplayName("ID(PK)로 아이를 조회한다")
    void findKidsByUuidAndIsDeleted() {
        // when
        Kids findKids = kidsFindService.findKidsByUuidAndIsDeleted(kids.getUuid());
        UUID inVaildUuid = UUID.randomUUID();

        // then
        assertThatThrownBy(() -> kidsFindService.findKidsByUuidAndIsDeleted(inVaildUuid))
                .isInstanceOf(BaseException.class)
                .hasMessage(KidsErrorCode.KIDS_NOT_FOUND.getMessage());

        assertThat(findKids).isEqualTo(kids);
    }

    @Test
    @DisplayName("email로 아이를 조회한다")
    void findByKidsEmailAndIsDeleted() {
        // when
        Kids findKids = kidsFindService.findByKidsEmailAndIsDeleted(kids.getKidsEmail());

        // then
        assertThatThrownBy(() -> kidsFindService.findByKidsEmailAndIsDeleted("inVaild@gmail.com"))
                .isInstanceOf(BaseException.class)
                .hasMessage(KidsErrorCode.KIDS_NOT_FOUND.getMessage());

        assertThat(findKids).isEqualTo(kids);
    }
}
