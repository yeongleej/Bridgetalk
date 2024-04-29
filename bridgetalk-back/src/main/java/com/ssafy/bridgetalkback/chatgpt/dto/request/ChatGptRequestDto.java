package com.ssafy.bridgetalkback.chatgpt.dto.request;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatGptRequestDto {

    private String model;
    private String prompt;
    private float temperature;
    private int max_tokens;

    @Builder
    public ChatGptRequestDto(String prompt) {
        this.model = "gpt-3.5-turbo-instruct";
        this.prompt = prompt;
        this.temperature = 0;
        this.max_tokens = 1000;
    }
}