package com.ssafy.bridgetalkback.tts.service;

import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.tts.exception.TtsErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TtsService {
    @Value("${CLOVA_CLIENTID}")
    private String clientId;

    @Value("${CLOVA_CLIENT_SECRET}")
    private String clientSecret;

    private URL url;

    public Resource textToSpeech(String inputText) {
        log.info("{tts Service }: tts 변환 서비스 호출");
        try {
            String text = URLEncoder.encode(inputText, StandardCharsets.UTF_8);
            url = new URL("https://naveropenapi.apigw.ntruss.com/tts-premium/v1/tts");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String postParams = "speaker=ndain&volume=5&speed=0&emotion=2&emotion-strength=1&pitch=0&format=mp3&text=" + text;
            con.setDoOutput(true);
            con.getOutputStream().write(postParams.getBytes(StandardCharsets.UTF_8));
            con.getOutputStream().flush();
            con.getOutputStream().close();

            File tempFile = File.createTempFile("tts- ", ".mp3");
            try (InputStream is = con.getInputStream(); FileOutputStream fos = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[4096];
                int len;
                while ((len = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
            }
            Resource resource = new FileSystemResource(tempFile);
            tempFile.deleteOnExit();

            return resource;
        } catch (Exception e) {
            throw new BaseException(TtsErrorCode.CLOVA_API_ERROR);
        }
    }
}
