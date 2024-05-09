package com.ssafy.bridgetalkback.chatgpt.config;

public enum ChatGptRequestCode {
    SUMMARY("summary"),
    CONVERSION("conversion"),
    KEYWORD("keyword"),
    ANSWER("answer"),
    SOLUTION("solution");

    private final String label;

    private ChatGptRequestCode(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}