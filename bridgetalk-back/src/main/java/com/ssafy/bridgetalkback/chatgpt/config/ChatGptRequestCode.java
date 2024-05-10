package com.ssafy.bridgetalkback.chatgpt.config;

public enum ChatGptRequestCode {
    SUMMARY("summary"),
    TRANSLATE("translate"),
    CONVERSION("conversion"),
    KEYWORD("keyword"),
    ANSWER("answer"),
    PARAGRAPH_TRANSLATE_ENG("paragraph_translate_eng"),
    PARAGRAPH_TRANSLATE_VIET("paragraph_translate_viet")
    ;

    private final String label;

    private ChatGptRequestCode(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}