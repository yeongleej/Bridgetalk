package com.ssafy.bridgetalkback.chatgpt.config;

public enum ChatGptRequestCode {
    SUMMARY("summary"),
    TRANSLATE("translate"),
    CONVERSION("conversion"),
    KEYWORD("keyword"),
    ANSWER("answer"),
    SOLUTION("solution"),
    EMOTION("emotion"),
    PARAGRAPH_TRANSLATE_ENG("paragraph_translate_eng"),
    PARAGRAPH_TRANSLATE_VIET("paragraph_translate_viet"),
    PARAGRAPH_TRANSLATE_PH("paragraph_translate_ph"),
    LETTERS_KEYWORD("letters_keyword"),
    CONVERT_KEYWORD("convert_keyword"),
    TRANSLATE_PH("translate_ph"),
    TRANSLATE_VIET("translate_viet"),
    TRANSLATE_ENG("translate_eng"),
    TRANSLATE_PH_VER1("translate_ph_ver1"),

    STT_TRANSLATION("stt_translation")
    ;

    private final String label;

    private ChatGptRequestCode(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}