package com.ssafy.bridgetalkback.kids.domain;

import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.parents.domain.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.ssafy.bridgetalkback.fixture.KidsFixture.JIYEONG;
import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SUNKYOUNG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Kids 도메인 테스트")
public class KidsTest {
    @Test
    @DisplayName("Kids 생성에 성공한다")
    void success() {
        Parents parents = SUNKYOUNG.toParents();
        Kids kids = JIYEONG.toKids(parents);

        assertAll(
                () -> assertThat(kids.getUuid()).isNotNull(),
                () -> assertThat(kids.getKidsName()).isEqualTo(JIYEONG.getKidsName()),
                () -> assertThat(kids.getKidsEmail()).isEqualTo(JIYEONG.getKidsEmail()),
                () -> assertThat(kids.getKidsNickname()).isEqualTo(JIYEONG.getKidsNickname()),
                () -> assertThat(kids.getKidsDino()).isEqualTo(JIYEONG.getKidsDino()),
                () -> assertThat(kids.getIsDeleted()).isEqualTo(0),
                () -> assertThat(kids.getRole()).isEqualTo(Role.USER),
                () -> assertThat(kids.getParents()).isEqualTo(parents)
        );
    }
}
