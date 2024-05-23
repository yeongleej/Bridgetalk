package com.ssafy.bridgetalkback.fixture;

import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.kids.domain.KidsPassword;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.ssafy.bridgetalkback.global.utils.PasswordEncoderUtils.ENCODER;

@Getter
@RequiredArgsConstructor
public enum KidsFixture {
    JIYEONG("이지영", "btasdfsdfsdf@bridgetalk.co.kr", "닉네임", "D1", "kidsPassword"),
    HYUNYOUNG("이현영", "abcabcabc@bridgetalk.co.kr", "닉네임", "D2", "kidsPassword"),
    SIYEON("박시연", "qweqweqwe@bridge.co.kr", "닉네임", "D3", "kidsPassword")
    ;

    private final String kidsName;
    private final String kidsEmail;
    private final String kidsNickname;
    private final String kidsDino;
    private final String kidsPassword;

    public Kids toKids(Parents parents) {
        return Kids.createKids(parents, kidsName, kidsEmail, kidsNickname, kidsDino, KidsPassword.encrypt(kidsPassword, ENCODER));
    }
}
