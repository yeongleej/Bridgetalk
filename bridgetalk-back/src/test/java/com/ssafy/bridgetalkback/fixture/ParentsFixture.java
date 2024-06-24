package com.ssafy.bridgetalkback.fixture;

import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.parents.domain.Email;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.parents.domain.Password;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.ssafy.bridgetalkback.global.utils.PasswordEncoderUtils.ENCODER;

@Getter
@RequiredArgsConstructor
public enum ParentsFixture {
    SUNKYOUNG("윤선경", "ssafy123@gmail.com", "ssafy123", "닉네임1", "D1", Language.viet),
    SOYOUNG("방소영", "ssafy456@gmail.com", "ssafy456", "닉네임2", "D1", Language.ph)
    ;

    private final String parentsName;
    private final String parentsEmail;
    private final String parentsPassword;
    private final String parentsNickname;
    private final String parentsDino;
    private final Language language;

    public Parents toParents() {
        return Parents.createParents(parentsName, Email.from(parentsEmail), Password.encrypt(parentsPassword, ENCODER), parentsNickname, parentsDino, language);
    }
}
