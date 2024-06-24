package com.ssafy.bridgetalkback.translation.service;

import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.global.exception.GlobalErrorCode;
import com.ssafy.bridgetalkback.letters.exception.LettersErrorCode;
import com.ssafy.bridgetalkback.letters.exception.TranslateBadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class TranslationService {

    @Value("${PAPAGO_CLIENTID}")
    private String clientId;

    @Value("${PAPAGO_CLIENT_SECRET}")
    private String clientSecret;


    /**
     * translation() : 번역 api 호출 메서드
     * @param orignal : 원본 텍스트
     * @return String : 번역본 텍스트
     * */
    public String translation(String orignal, String source, String target) {
        log.info("{ LetterService.translation() } : 번역 api 호출 메서드 ");

        String extractTranslationText = "";
        try {
            String text = URLEncoder.encode(orignal, "UTF-8");
            String apiURL = "https://naveropenapi.apigw.ntruss.com/nmt/v1/translation";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            // post request
            if (source.isEmpty() || target.isEmpty()){
                throw new TranslateBadRequestException(LettersErrorCode.TRANSLATION_EMPTY_CODE.getMessage(), 400);
            }
            String postParams = "source="+source+"&target="+target+"&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            log.info(">> papago api responseCode : {}", responseCode);
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 오류 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                // JSON 객체 생성
                JSONTokener tokener = new JSONTokener(inputLine);
                JSONObject jsonObject = new JSONObject(tokener);
                log.info(">> jsonObject : {}", jsonObject);
                // extract translated text
                JSONObject message = (JSONObject) jsonObject.get("message");
                JSONObject result = (JSONObject) message.get("result");

                extractTranslationText = result.get("translatedText").toString();
                log.info(">>>> translatedText : {}", extractTranslationText);
            }

            br.close();
        }catch (TranslateBadRequestException tbe){
            log.error(tbe.getMessage());
            throw BaseException.type(LettersErrorCode.TRANSLATION_BAD_REQUEST);

        }catch (Exception e) {
            log.error(e.getMessage());
            throw BaseException.type(GlobalErrorCode.INTERNAL_SERVER_ERROR);
        }

        return extractTranslationText;
    }

}
