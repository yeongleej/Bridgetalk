package com.ssafy.bridgetalkback.global.security;

import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.kids.service.KidsFindService;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.parents.service.ParentsFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final ParentsFindService parentsFindService;
    private final KidsFindService kidsFindService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetailDto userDetailDto = null;
        int idx = email.indexOf("@");
        String isKids = email.substring(idx+1);
        if(!isKids.equals("bridgetalk.com")) {
            Parents parents = parentsFindService.findParentsByParentsEmailAndIsDeleted(email);
            userDetailDto = new UserDetailDto(parents.getUuid(), parents.getParentsEmail().getValue(), parents.getParentsName(), parents.getRole().getAuthority());
        }
        else {
            Kids kids = kidsFindService.findByKidsEmailAndIsDeleted(email);
            userDetailDto = new UserDetailDto(kids.getUuid(), kids.getKidsEmail(), kids.getKidsName(), kids.getRole().getAuthority());
        }

        return new CustomUserDetails(userDetailDto);
    }
}


