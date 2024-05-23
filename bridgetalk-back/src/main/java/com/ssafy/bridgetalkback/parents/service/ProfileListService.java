package com.ssafy.bridgetalkback.parents.service;

import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.kids.repository.KidsRepository;
import com.ssafy.bridgetalkback.kids.service.KidsFindService;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.parents.dto.response.ProfileListResponseDto;
import com.ssafy.bridgetalkback.parents.dto.response.ProfileResponseDto;
import com.ssafy.bridgetalkback.parents.repository.ParentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileListService {
    private final ParentsRepository parentsRepository;
    private final KidsRepository kidsRepository;
    private final ParentsFindService parentsFindService;
    private final KidsFindService kidsFindService;

    @Transactional
    public ProfileListResponseDto profileList(UUID userId) {
        Parents parents = null;

        if(parentsRepository.existsParentsByUuidAndIsDeleted(userId, 0)){
            log.info("{ ProfileListService } : 부모 측 - 프로필리스트 조회");
            parents = parentsFindService.findParentsByUuidAndIsDeleted(userId);
        }
        else if(kidsRepository.existsKidsByUuidAndIsDeleted(userId, 0)) {
            log.info("{ ProfileListService } : 아이 측 - 프로필리스트 조회");
            Kids kids = kidsFindService.findKidsByUuidAndIsDeleted(userId);
            parents = kids.getParents();
        }
        else throw BaseException.type(AuthErrorCode.USER_NOT_FOUND);

        List<Kids> kidsList = parents.getKidsList();
        List<ProfileResponseDto> profileList = new ArrayList<>();

        profileList.add(ProfileResponseDto.fromParents(parents));
        for (Kids kids : kidsList){
            if(kidsRepository.existsKidsByUuidAndIsDeleted(kids.getUuid(), 0)) {
                profileList.add(ProfileResponseDto.fromKids(kids));
            }
        }

        log.info("{ ProfileListService } : 프로필리스트 조회 성공");
        return new ProfileListResponseDto(profileList);
    }
}
