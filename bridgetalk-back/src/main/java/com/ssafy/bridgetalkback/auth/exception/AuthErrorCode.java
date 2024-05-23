package com.ssafy.bridgetalkback.auth.exception;

import com.ssafy.bridgetalkback.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "AUTH_001", "이미 가입된 이메일입니다."),
    AUTH_EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_002", "토큰의 유효기간이 만료되었습니다."),
    AUTH_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_003", "토큰이 유효하지 않습니다."),
    INVALID_PERMISSION(HttpStatus.FORBIDDEN, "AUTH_004", "권한이 없습니다. 로그인 먼저 해주세요."),
    WRONG_PASSWORD(HttpStatus.UNAUTHORIZED, "AUTH_005", "비밀번호가 일치하지 않습니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "AUTH_006", "리프레시 토큰을 찾을 수 없습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "AUTH_007", "토큰으로부터 추출된 uuid를 가진 유저를 찾을 수 없습니다."),
    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "AUTH_008", "이미 존재하는 닉네임입니다."),
    UNABLE_TO_SEND_EMAIL(HttpStatus.BAD_REQUEST, "AUTH_009", "이메일 전송에 실패했습니다."),
    AUTH_CODE_NOT_FOUND(HttpStatus.NOT_FOUND, "AUTH_010", "이메일에 해당하는 인증번호를 찾을 수 없습니다."),
    WRONG_AUTH_CODE(HttpStatus.BAD_REQUEST, "AUTH_011", "인증번호가 일치하지 않습니다.")
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
