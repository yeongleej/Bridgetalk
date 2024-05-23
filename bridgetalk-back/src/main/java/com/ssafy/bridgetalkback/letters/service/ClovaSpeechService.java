package com.ssafy.bridgetalkback.letters.service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.global.exception.GlobalErrorCode;
import com.ssafy.bridgetalkback.letters.exception.LettersErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ClovaSpeechService {

    // Clova Speech secret key
    @Value("${CLOVA_SPEECH_SECRET}")
    private String SECRET;
    // Clova Speech invoke URL
    @Value("${CLOVA_SPEECH_INVOKE_URL}")
    private String INVOKE_URL;

    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private Gson gson = new Gson();

//    private static final Header[] HEADERS = new Header[] {
//            new BasicHeader("Accept", "application/json"),
//            new BasicHeader("X-CLOVASPEECH-API-KEY", SECRET),
//    };
    private Header[] makeHeader(String secret) {
        return new Header[] {
                new BasicHeader("Accept", "application/json"),
                new BasicHeader("X-CLOVASPEECH-API-KEY", secret),
        };
    }


    public static class Boosting {
        private String words;

        public String getWords() {
            return words;
        }

        public void setWords(String words) {
            this.words = words;
        }
    }

    public static class Diarization {
        private Boolean enable = Boolean.FALSE;
        private Integer speakerCountMin;
        private Integer speakerCountMax;

        public Boolean getEnable() {
            return enable;
        }

        public void setEnable(Boolean enable) {
            this.enable = enable;
        }

        public Integer getSpeakerCountMin() {
            return speakerCountMin;
        }

        public void setSpeakerCountMin(Integer speakerCountMin) {
            this.speakerCountMin = speakerCountMin;
        }

        public Integer getSpeakerCountMax() {
            return speakerCountMax;
        }

        public void setSpeakerCountMax(Integer speakerCountMax) {
            this.speakerCountMax = speakerCountMax;
        }
    }

    public static class Sed {
        private Boolean enable = Boolean.FALSE;

        public Boolean getEnable() {
            return enable;
        }

        public void setEnable(Boolean enable) {
            this.enable = enable;
        }
    }

    public static class NestRequestEntity {
        private String language = "ko-KR";
        //completion optional, sync/async
        private String completion = "sync";
        //optional, used to receive the analyzed results
        private String callback;
        //optional, any data
        private Map<String, Object> userdata;
        private Boolean wordAlignment = Boolean.TRUE;
        private Boolean fullText = Boolean.TRUE;
        //boosting object array
        private List<Boosting> boostings;
        //comma separated words
        private String forbiddens;
        private Diarization diarization;
        private Sed sed;

        public Sed getSed() {
            return sed;
        }

        public void setSed(Sed sed) {
            this.sed = sed;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getCompletion() {
            return completion;
        }

        public void setCompletion(String completion) {
            this.completion = completion;
        }

        public String getCallback() {
            return callback;
        }

        public Boolean getWordAlignment() {
            return wordAlignment;
        }

        public void setWordAlignment(Boolean wordAlignment) {
            this.wordAlignment = wordAlignment;
        }

        public Boolean getFullText() {
            return fullText;
        }

        public void setFullText(Boolean fullText) {
            this.fullText = fullText;
        }

        public void setCallback(String callback) {
            this.callback = callback;
        }

        public Map<String, Object> getUserdata() {
            return userdata;
        }

        public void setUserdata(Map<String, Object> userdata) {
            this.userdata = userdata;
        }

        public String getForbiddens() {
            return forbiddens;
        }

        public void setForbiddens(String forbiddens) {
            this.forbiddens = forbiddens;
        }

        public List<Boosting> getBoostings() {
            return boostings;
        }

        public void setBoostings(List<Boosting> boostings) {
            this.boostings = boostings;
        }

        public Diarization getDiarization() {
            return diarization;
        }

        public void setDiarization(Diarization diarization) {
            this.diarization = diarization;
        }
    }

    /**
     * recognize media using URL
     * @param url required, the media URL
     * @param nestRequestEntity optional
     * @return string
     */
    public String url(String url, NestRequestEntity nestRequestEntity, String secret, String invoke_url) {
//        log.info(">> {}", secret);
//        log.info(">> {}", invoke_url);
        HttpPost httpPost = new HttpPost(invoke_url + "/recognizer/url");
//        httpPost.setHeaders(HEADERS);
        httpPost.setHeaders(makeHeader(secret));
        Map<String, Object> body = new HashMap<>();
        body.put("url", url);
        body.put("language", nestRequestEntity.getLanguage());
        body.put("completion", nestRequestEntity.getCompletion());
        body.put("callback", nestRequestEntity.getCallback());
        body.put("userdata", nestRequestEntity.getCallback());
        body.put("wordAlignment", nestRequestEntity.getWordAlignment());
        body.put("fullText", nestRequestEntity.getFullText());
        body.put("forbiddens", nestRequestEntity.getForbiddens());
        body.put("boostings", nestRequestEntity.getBoostings());
        body.put("diarization", nestRequestEntity.getDiarization());
        body.put("sed", nestRequestEntity.getSed());
        HttpEntity httpEntity = new StringEntity(gson.toJson(body), ContentType.APPLICATION_JSON);
        httpPost.setEntity(httpEntity);

//        Header[] headers = httpPost.getAllHeaders();
//        for (Header header : headers) {
//            System.out.println(header.getName() + ": " + header.getValue());
//        }
        return execute(httpPost);
    }

    private String execute(HttpPost httpPost) {
        try (final CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {
            final HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String stt(String fileUrl) {
        log.info("{ ClovaSpeechService - stt } : 클로바 speech api 호출 서비스");
        final ClovaSpeechService clovaSpeechClient = new ClovaSpeechService();
        NestRequestEntity requestEntity = new NestRequestEntity();
        String secret = SECRET;
        String invoke = INVOKE_URL;
        final String result = clovaSpeechClient.url(fileUrl, requestEntity, secret, invoke);
        log.info("result : {}", result);

        return extractText(result);
    }

    public String extractText(String result) {
        ObjectMapper objectMapper = new ObjectMapper();
        String eText = "";
        try {
            Map<String, Object> jsonData = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {});
            // 변환 실패 시,
            if (!jsonData.get("result").toString().equals("COMPLETED")){
                throw BaseException.type(LettersErrorCode.ERROR_REQUEST_PARAMETER);
            }
            eText = jsonData.get("text").toString();
            log.info(">> success extract text : {}", eText);
        } catch (JsonProcessingException jpe) {
            log.error("!! in extractText() : {}", jpe.getMessage());
            throw BaseException.type(GlobalErrorCode.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("!! in extractText() : {}", e.getMessage());
            throw BaseException.type(GlobalErrorCode.INTERNAL_SERVER_ERROR);
        }

        return eText;

    }

}