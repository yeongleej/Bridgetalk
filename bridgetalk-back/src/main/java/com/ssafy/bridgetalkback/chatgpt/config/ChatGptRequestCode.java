package com.ssafy.bridgetalkback.chatgpt.config;

public enum ChatGptRequestCode {
    SUMMARY("summary"),
    CONVERSION("conversion");

    private final String label;

    private ChatGptRequestCode(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}