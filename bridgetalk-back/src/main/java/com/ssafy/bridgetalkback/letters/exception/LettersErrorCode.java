package com.ssafy.bridgetalkback.letters.exception;

import com.ssafy.bridgetalkback.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum LettersErrorCode implements ErrorCode {
    EMPTY_FILE(HttpStatus.BAD_REQUEST, "LETTERS_001", "전송된 파일이 없습니다."),
    LETTERS_NOT_FOUND(HttpStatus.NOT_FOUND, "LETTERS_002", "편지를 찾을 수 없습니다."),
    TRANSLATION_BAD_REQUEST(HttpStatus.BAD_REQUEST, "LETTERS_003", "[번역 api 호출] 잘못된 요청입니다."),
    TRANSLATION_EMPTY_CODE(HttpStatus.BAD_REQUEST, "LETTERS_004", "[번역 api 호출] source 코드 or target 코드를 찾을 수 없습니다."),
    CHATGPT_EMPTY_TEXT(HttpStatus.BAD_REQUEST, "LETTERS_005", "[chatgpt api 호출] 변환할 텍스트가 비어있습니다."),
    OBJECT_EMPTY(HttpStatus.INTERNAL_SERVER_ERROR, "TRANSLATE_001", "object 객체가 비어있습니다."),
    ERROR_REQUEST_PARAMETER(HttpStatus.INTERNAL_SERVER_ERROR, "TRANSLATE_002", "[clova api 호출] 잘못된 파라미터 요청"),
    LETTERS_IMG_NOT_FOUND(HttpStatus.NOT_FOUND, "LETTERS_006", "키워드에 해당하는 이미지가 없습니다. "),
    LETTERS_DUPLICATED(HttpStatus.BAD_REQUEST, "LETTERS_007", "이미 레포트에 해당하는 편지가 있습니다.");


    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
