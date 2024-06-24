package com.ssafy.bridgetalkback.parents.service;

import com.ssafy.bridgetalkback.auth.dto.response.LoginResponseDto;
import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.auth.service.TokenService;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.kids.domain.KidsPassword;
import com.ssafy.bridgetalkback.kids.repository.KidsRepository;
import com.ssafy.bridgetalkback.kids.service.KidsFindService;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.parents.domain.Password;
import com.ssafy.bridgetalkback.parents.dto.request.DeleteProfileRequestDto;
import com.ssafy.bridgetalkback.parents.dto.request.UpdateProfileRequestDto;
import com.ssafy.bridgetalkback.parents.repository.ParentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.ssafy.bridgetalkback.global.utils.PasswordEncoderUtils.ENCODER;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileService {
    private final ParentsRepository parentsRepository;
    private final KidsRepository kidsRepository;
    private final ParentsFindService parentsFindService;
    private final KidsFindService kidsFindService;
    private final TokenService tokenService;

    @Transactional
    public void updateProfile(UUID profileId, UpdateProfileRequestDto requestDto) {

        if(parentsRepository.existsParentsByUuidAndIsDeleted(profileId, 0)){
            log.info("{ ProfileService } : 부모 측 - 프로필 수정");
            Parents parents = parentsFindService.findParentsByUuidAndIsDeleted(profileId);
            parents.updateProfile(requestDto.nickname(), requestDto.dino());
            log.info("{ ProfileService } : 부모 측 - 프로필 수정 성공");
        }
        else if(kidsRepository.existsKidsByUuidAndIsDeleted(profileId, 0)) {
            log.info("{ ProfileService } : 아이 측 - 프로필 수정");
            Kids kids = kidsFindService.findKidsByUuidAndIsDeleted(profileId);
            kids.updateProfile(requestDto.nickname(), requestDto.dino());
            log.info("{ ProfileService } : 아이 측 - 프로필 수정 성공");
        }
        else throw BaseException.type(AuthErrorCode.USER_NOT_FOUND);
    }

    @Transactional
    public void deleteProfile(DeleteProfileRequestDto requestDto) {
        UUID profileId = UUID.fromString(requestDto.profileId());
        String password = requestDto.password();

        if(parentsRepository.existsParentsByUuidAndIsDeleted(profileId, 0)){
            Parents parents = parentsFindService.findParentsByUuidAndIsDeleted(profileId);

            validateParentsPassword(password, parents.getParentsPassword());
            log.info("{ ProfileService } : 프로필 비밀번호 인증 성공");

            parents.updateIsDeleted();
            log.info("{ ProfileService } : 부모 측 - 프로필 삭제 ");
            tokenService.deleteRefreshTokenByUserId(profileId);
            log.info("{ ProfileService } : 부모 측 - RefreshToken 삭제");

            List<Kids> kidsList = parents.getKidsList();
            for(Kids kids : kidsList) {
                kids.updateIsDeleted();
                tokenService.deleteRefreshTokenByUserId(kids.getUuid());
            }
            log.info("{ ProfileService } : 부모 측 - 부모의 아이들 프로필 및 RefreshToken 삭제");
        }
        else if(kidsRepository.existsKidsByUuidAndIsDeleted(profileId, 0)) {
            Kids kids = kidsFindService.findKidsByUuidAndIsDeleted(profileId);

            validateKidsPassword(password, kids.getKidsPassword());
            log.info("{ ProfileService } : 프로필 비밀번호 인증 성공");

            kids.updateIsDeleted();
            log.info("{ ProfileService } : 아이 측 - 프로필 삭제");
            tokenService.deleteRefreshTokenByUserId(profileId);
            log.info("{ ProfileService } : 아이 측 - RefreshToken 삭제");
        }
        else throw BaseException.type(AuthErrorCode.USER_NOT_FOUND);
    }

    private void validateParentsPassword(String comparePassword, Password findPassword) {
        if(!findPassword.isSamePassword(comparePassword, ENCODER)) {
            throw BaseException.type(AuthErrorCode.WRONG_PASSWORD);
        }
    }

    private void validateKidsPassword(String comparePassword, KidsPassword findPassword) {
        if(!findPassword.isSamePassword(comparePassword, ENCODER)) {
            throw BaseException.type(AuthErrorCode.WRONG_PASSWORD);
        }
    }
}
