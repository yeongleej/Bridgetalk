package com.ssafy.bridgetalkback.reports.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TalkFastApiService {

    private WebClient webClient;

    @PostConstruct
    public void initWebClient() {
        webClient = WebClient.create("http://bridgetalk.co.kr:8000");
    }

    public String callFastApi(String fileUrl) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
//        formData.add("audio_file", "https://bridge-talk.s3.ap-northeast-2.amazonaws.com/letters/test-k1.mp3");
        formData.add("audio_file", fileUrl);

        return webClient.post()
                .uri("/stt")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(String.class)
                .block();
//        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
//        formData.add("audio_file", file.getResource());
//
//        return webClient.post()
//                .uri("/stt")
//                .contentType(MediaType.MULTIPART_FORM_DATA)
//                .body(BodyInserters.fromMultipartData(formData))
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
    }
}
