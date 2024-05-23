package com.ssafy.bridgetalkback.parents.service;

import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.parents.dto.response.ProfileListResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ssafy.bridgetalkback.fixture.KidsFixture.*;
import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SUNKYOUNG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Parents [Service Layer] -> ProfileListService 테스트")
public class ProfileListServiceTest extends ServiceTest {
    @Autowired
    private ProfileListService profileListService;

    private Parents parents;
    private Kids[] kids = new Kids[3];

    @BeforeEach
    void setup() {
        parents = parentsRepository.save(SUNKYOUNG.toParents());
        kids[0] = kidsRepository.save(JIYEONG.toKids(parents));
        kids[1] = kidsRepository.save(HYUNYOUNG.toKids(parents));
        kids[2] = kidsRepository.save(SIYEON.toKids(parents));
    }

    @Test
    @DisplayName("삭제되지(탈퇴하지) 않은 프로필 리스트를 조회한다")
    void findProfileList() {
        // when
        kids[2].updateIsDeleted();
        flushAndClear();
        ProfileListResponseDto profileList = profileListService.profileList(parents.getUuid());

        // then
        assertAll(
                () -> assertThat(profileList.profileList()).size().isEqualTo(3),
                () -> assertThat(profileList.profileList().get(0).userId()).isEqualTo(String.valueOf(parents.getUuid())),
                () -> assertThat(profileList.profileList().get(0).userName()).isEqualTo(parents.getParentsName()),
                () -> assertThat(profileList.profileList().get(0).userEmail()).isEqualTo(parents.getParentsEmail().getValue()),
                () -> assertThat(profileList.profileList().get(2).userId()).isEqualTo(String.valueOf(kids[1].getUuid())),
                () -> assertThat(profileList.profileList().get(2).userName()).isEqualTo(kids[1].getKidsName()),
                () -> assertThat(profileList.profileList().get(2).userEmail()).isEqualTo(kids[1].getKidsEmail())
        );
    }
}
