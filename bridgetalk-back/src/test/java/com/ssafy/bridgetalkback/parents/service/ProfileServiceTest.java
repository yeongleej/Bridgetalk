package com.ssafy.bridgetalkback.parents.service;

import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.kids.exception.KidsErrorCode;
import com.ssafy.bridgetalkback.kids.service.KidsFindService;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.parents.dto.request.DeleteProfileRequestDto;
import com.ssafy.bridgetalkback.parents.dto.request.UpdateProfileRequestDto;
import com.ssafy.bridgetalkback.parents.exception.ParentsErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.ssafy.bridgetalkback.fixture.KidsFixture.*;
import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SUNKYOUNG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Profile [Service Layer] -> ProfileService 테스트")
public class ProfileServiceTest extends ServiceTest {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private ParentsFindService parentsFindService;

    @Autowired
    private KidsFindService kidsFindService;

    private Parents parents;
    private Kids kids;

    @BeforeEach
    void setup() {
        parents = parentsRepository.save(SUNKYOUNG.toParents());
        kids = kidsRepository.save(JIYEONG.toKids(parents));
        flushAndClear();
    }

    @Test
    @DisplayName("프로필 수정에 성공한다")
    void updateProfile() {
        // when
        profileService.updateProfile(parents.getUuid(), createUpdateProfileRequestDto());
        profileService.updateProfile(kids.getUuid(), createUpdateProfileRequestDto());

        // then
        Parents findParents = parentsFindService.findParentsByUuidAndIsDeleted(parents.getUuid());
        Kids findKids = kidsFindService.findKidsByUuidAndIsDeleted(kids.getUuid());
        assertAll(
                () -> assertThat(findParents.getParentsNickname()).isEqualTo("새로운 닉네임"),
                () -> assertThat(findParents.getParentsDino()).isEqualTo("D3"),
                () -> assertThat(findKids.getKidsNickname()).isEqualTo("새로운 닉네임"),
                () -> assertThat(findKids.getKidsDino()).isEqualTo("D3")
        );
    }

    @Nested
    @DisplayName("프로필 삭제")
    class deleteProfile {
        @Test
        @DisplayName("부모 프로필 삭제에 성공한다")
        void deleteParentsProfile() {
            // when
            profileService.deleteProfile(createParentsDeleteProfileRequestDto());

            // then
            assertThatThrownBy(() -> parentsFindService.findParentsByUuidAndIsDeleted(parents.getUuid()))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(ParentsErrorCode.PARENTS_NOT_FOUND.getMessage());
            assertThatThrownBy(() -> kidsFindService.findKidsByUuidAndIsDeleted(kids.getUuid()))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(KidsErrorCode.KIDS_NOT_FOUND.getMessage());

            Optional<Parents> findParents = parentsRepository.findById(parents.getUuid());
            Optional<Kids> findKids = kidsRepository.findById(kids.getUuid());
            assertAll(
                    () -> assertThat(findParents.orElseThrow().getIsDeleted()).isEqualTo(1),
                    () -> assertThat(refreshTokenRedisRepository.findById(findParents.orElseThrow().getUuid())).isEmpty(),
                    () -> assertThat(findKids.orElseThrow().getIsDeleted()).isEqualTo(1),
                    () -> assertThat(refreshTokenRedisRepository.findById(findKids.orElseThrow().getUuid())).isEmpty()
            );
        }

        @Test
        @DisplayName("아이 프로필 삭제에 성공한다")
        void deleteKidsProfile() {
            // when
            profileService.deleteProfile(createKidsDeleteProfileRequestDto());

            // then
            assertThatThrownBy(() -> kidsFindService.findKidsByUuidAndIsDeleted(kids.getUuid()))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(KidsErrorCode.KIDS_NOT_FOUND.getMessage());

            Optional<Kids> findKids = kidsRepository.findById(kids.getUuid());
            assertAll(
                    () -> assertThat(findKids.orElseThrow().getIsDeleted()).isEqualTo(1),
                    () -> assertThat(refreshTokenRedisRepository.findById(findKids.orElseThrow().getUuid())).isEmpty()
            );
        }
    }

    private UpdateProfileRequestDto createUpdateProfileRequestDto() {
        return new UpdateProfileRequestDto("새로운 닉네임", "D3");
    }

    private DeleteProfileRequestDto createParentsDeleteProfileRequestDto() {
        return new DeleteProfileRequestDto(parents.getUuid().toString(), SUNKYOUNG.getParentsPassword());
    }

    private DeleteProfileRequestDto createKidsDeleteProfileRequestDto() {
        return new DeleteProfileRequestDto(kids.getUuid().toString(), JIYEONG.getKidsPassword());
    }
}
