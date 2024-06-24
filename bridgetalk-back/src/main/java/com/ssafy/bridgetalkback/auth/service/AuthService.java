package com.ssafy.bridgetalkback.auth.service;

import com.ssafy.bridgetalkback.auth.dto.request.KidsSignupRequestDto;
import com.ssafy.bridgetalkback.auth.dto.request.LoginRequestDto;
import com.ssafy.bridgetalkback.auth.dto.request.ParentsSignupRequestDto;
import com.ssafy.bridgetalkback.auth.dto.request.ProfileLoginRequestDto;
import com.ssafy.bridgetalkback.auth.dto.response.LoginResponseDto;
import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.auth.utils.JwtProvider;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.kids.domain.KidsPassword;
import com.ssafy.bridgetalkback.kids.repository.KidsRepository;
import com.ssafy.bridgetalkback.kids.service.KidsFindService;
import com.ssafy.bridgetalkback.parents.domain.Email;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.parents.domain.Password;
import com.ssafy.bridgetalkback.parents.repository.ParentsRepository;
import com.ssafy.bridgetalkback.parents.service.ParentsFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.ssafy.bridgetalkback.global.utils.PasswordEncoderUtils.ENCODER;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {
    private final ParentsRepository parentsRepository;
    private final ParentsFindService parentsFindService;
    private final JwtProvider jwtProvider;
    private final TokenService tokenService;
    private final KidsRepository kidsRepository;
    private final KidsFindService kidsFindService;

    @Transactional
    public UUID signup(ParentsSignupRequestDto requestDto) {
        log.info("{ AuthService } : 회원가입 진입");
        DuplicateEmail(requestDto.parentsEmail());

        Parents parents = Parents.createParents(requestDto.parentsName(), Email.from(requestDto.parentsEmail()),
                Password.encrypt(requestDto.parentsPassword(), ENCODER), requestDto.parentsNickname(), requestDto.parentsDino(), requestDto.language());

        return parentsRepository.save(parents).getUuid();
    }

    @Transactional
    public LoginResponseDto login(LoginRequestDto requestDto) {
        log.info("{ AuthService } : 부모로그인 진입");
        Parents parents = parentsFindService.findParentsByParentsEmailAndIsDeleted(requestDto.email());
        validateParentsPassword(requestDto.password(), parents.getParentsPassword());
        log.info("{ AuthService } : 비밀번호 일치");

        String accessToken = jwtProvider.createAccessToken(parents.getUuid());
        String refreshToken = jwtProvider.createRefreshToken(parents.getUuid());
        tokenService.synchronizeRefreshToken(parents.getUuid(), refreshToken);

        return LoginResponseDto.fromParents(parents, accessToken, refreshToken);
    }

    @Transactional
    public void logout(UUID userId) {
        tokenService.deleteRefreshTokenByUserId(userId);
        log.info("{ AuthService } : 로그아웃 성공");
    }

    @Transactional
    public UUID kidsSignup(KidsSignupRequestDto requestDto) {
        log.info("{ AuthService } : 아이 회원가입 진입");
        Parents parents = parentsFindService.findParentsByUuidAndIsDeleted(UUID.fromString(requestDto.parentsId()));

        Kids kids = Kids.createKids(parents, requestDto.kidsName(), "",
                requestDto.kidsNickname(), requestDto.kidsDino(), KidsPassword.encrypt(requestDto.kidsPassword(), ENCODER));

        UUID kidsId = kidsRepository.save(kids).getUuid();
        Kids findKids = kidsFindService.findKidsByUuidAndIsDeleted(kidsId);
        findKids.updateKidsEmail(createKidsEmail(String.valueOf(kidsId)));

        return findKids.getUuid();
    }

    @Transactional
    public LoginResponseDto profileLogin(ProfileLoginRequestDto requestDto) {
        log.info("{ AuthService } : 프로필 선택 진입");
        UUID profileId = UUID.fromString(requestDto.profileId());
        String password = requestDto.password();
        LoginResponseDto loginResponseDto = null;

        if(parentsRepository.existsParentsByUuidAndIsDeleted(profileId, 0)){
            log.info("{ AuthService } : 부모 측 - 프로필 조회");
            Parents parents = parentsFindService.findParentsByUuidAndIsDeleted(profileId);
            validateParentsPassword(password, parents.getParentsPassword());
            log.info("{ AuthService } : 프로필 비밀번호 인증 성공");

            String accessToken = jwtProvider.createAccessToken(parents.getUuid());
            String refreshToken = jwtProvider.createRefreshToken(parents.getUuid());
            tokenService.synchronizeRefreshToken(parents.getUuid(), refreshToken);
            loginResponseDto = LoginResponseDto.fromParents(parents, accessToken, refreshToken);
        }
        else if(kidsRepository.existsKidsByUuidAndIsDeleted(profileId, 0)) {
            log.info("{ AuthService } : 아이 측 - 프로필 조회");
            Kids kids = kidsFindService.findKidsByUuidAndIsDeleted(profileId);
            validateKidsPassword(password, kids.getKidsPassword());
            log.info("{ AuthService } : 프로필 비밀번호 인증 성공");

            String accessToken = jwtProvider.createAccessToken(kids.getUuid());
            String refreshToken = jwtProvider.createRefreshToken(kids.getUuid());
            tokenService.synchronizeRefreshToken(kids.getUuid(), refreshToken);
            loginResponseDto = LoginResponseDto.fromKids(kids, accessToken, refreshToken);
        }
        else throw BaseException.type(AuthErrorCode.USER_NOT_FOUND);

        return loginResponseDto;
    }

    public void duplicateNickname(String nickname) {
        if(parentsRepository.existsParentsByParentsNicknameAndIsDeleted(nickname, 0)) {
            log.info("{ AuthService } : 중복 닉네임");
            throw BaseException.type(AuthErrorCode.DUPLICATE_NICKNAME);
        }
    }

    public void DuplicateEmail(String email) {
        // 기존 이메일 가입한 경우 재가입 불가
        if(parentsRepository.existsParentsByParentsEmail(Email.from(email))) {
            log.info("{ AuthService } : 중복 이메일로 가입 실패");
            throw BaseException.type(AuthErrorCode.DUPLICATE_EMAIL);
        }
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

    private String createKidsEmail(String kidsId) {
        String randomNumber = String.valueOf((int)(Math.random() * 99) + 10);
        return "bridgetalk" + kidsId + randomNumber + "@bridgetalk.co.kr";
    }
}
