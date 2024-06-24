package com.ssafy.bridgetalkback.reports.service;

import com.ssafy.bridgetalkback.chatgpt.config.ChatGptRequestCode;
import com.ssafy.bridgetalkback.chatgpt.exception.ChatGptErrorCode;
import com.ssafy.bridgetalkback.chatgpt.service.ChatGptService;
import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.kids.service.KidsFindService;
import com.ssafy.bridgetalkback.notification.domain.NotificationType;
import com.ssafy.bridgetalkback.notification.dto.request.NotificationRequestDto;
import com.ssafy.bridgetalkback.notification.service.SseService;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.reports.domain.Reports;
import com.ssafy.bridgetalkback.slang.domain.Slang;
import com.ssafy.bridgetalkback.translation.service.TranslationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReportsUpdateService {

    private final ChatGptService chatGptService;
    private final ReportsFindService reportsFindService;
    private final TranslationService translationService;
    private final SseService sseService;
    private final KidsFindService kidsFindService;

    public void createReport(Long reportsId) {
        log.info("{ ReportsService } : 아이속마음 레포트 저장 진입");

        Reports reports = reportsFindService.findByReportsIdAndIsDeleted(reportsId);

        String originText = reports.getReportsOriginContent();

        log.info(">> summaryText 진입: {}", originText);
        String summaryText = chatGptService.createPrompt(originText, ChatGptRequestCode.SUMMARY);
        reports.updateSummaryKor(summaryText);
        log.info(">>>> reports.summaryKor : {}", reports.getReportsSummaryKor());
        log.info(">> summaryText 성공: {}", summaryText);

        log.info(">> translateText1 진입: {}", summaryText);
        String translateText1 = translationService.translation(summaryText, "ko", "vi");
        reports.updateSummaryViet(translateText1);
        log.info(">>>> reports.summaryViet : {}", reports.getReportsSummaryViet());
        log.info(">> translateText1 성공: {}", translateText1);

        log.info(">> translateText2 진입: {}", summaryText);
        String engText = chatGptService.createPrompt(summaryText, ChatGptRequestCode.TRANSLATE_ENG);
        String translateText2 = chatGptService.createPrompt(engText, ChatGptRequestCode.TRANSLATE_PH);
        reports.updateSummaryViet(translateText2);
        log.info(">>>> reports.summaryPh : {}", reports.getReportsSummaryPh());
        log.info(">> translateText2 성공: {}", translateText2);

        log.info(">> keywords_kor 진입");
        String keywords_kor = chatGptService.createPrompt(reports.getReportsOriginContent(), ChatGptRequestCode.KEYWORD);
        String[] keyword_kor_arr = keywords_kor.split(", ");
        if (keyword_kor_arr.length != 3)
            throw BaseException.type(ChatGptErrorCode.INVALID_KEYWORD);
        reports.updateKeywordsKor(arraytoList(keyword_kor_arr));
        log.info(">>>> reports.keywordKorArr : {}", reports.getReportsKeywordsKor().toString());
        log.info(">> keywords_kor 성공: {}", Arrays.toString(keyword_kor_arr));

        log.info(">> keywords_viet 진입");
        String keywords_viet = translationService.translation(keywords_kor, "ko", "vi");
        String[] keyword_viet_arr = keywords_viet.split(", ");
        if (keyword_viet_arr.length != 3)
            throw BaseException.type(ChatGptErrorCode.INVALID_KEYWORD);
        reports.updateKeywordsViet(arraytoList(keyword_viet_arr));
        log.info(">>>> reports.keywordVietArr : {}", reports.getReportsKeywordsViet().toString());
        log.info(">> keywords_viet 성공: {}", Arrays.toString(keyword_viet_arr));

        log.info(">> keywords_ph 진입");
        String keywords_ph = chatGptService.createPrompt(keywords_kor, ChatGptRequestCode.TRANSLATE_PH_VER1);
        String[] keywords_ph_arr = keywords_ph.split(", ");
        if (keywords_ph_arr.length != 3)
            throw BaseException.type(ChatGptErrorCode.INVALID_KEYWORD);
        reports.updateKeywordsPh(arraytoList(keywords_ph_arr));
        log.info(">>>> reports.keywordPhArr : {}", reports.getReportsKeywordsPh().toString());
        log.info(">> keywords_ph 성공: {}", Arrays.toString(keywords_ph_arr));

        log.info(">> solutionTextKor 진입: {}", originText);
        String solutionTextKor = chatGptService.createPrompt(originText, ChatGptRequestCode.SOLUTION);
        reports.updateSolutionKor(solutionTextKor);
        log.info(">>>> reports.solutionKor : {}", reports.getReportsSolutionKor());
        log.info(">> solutionTextKor 성공: {}", solutionTextKor);

        log.info(">> solutionTextViet 진입: {}", solutionTextKor);
        String solutionTextViet = translationService.translation(solutionTextKor, "ko", "vi");
        reports.updateSolutionViet(solutionTextViet);
        log.info(">>>> reports.solutionViet : {}", reports.getReportsSolutionViet());
        log.info(">> solutionTextViet 성공: {}", solutionTextViet);

        log.info(">> solutionTextPh 진입: {}", solutionTextKor);
        String solutionEngText = chatGptService.createPrompt(solutionTextKor, ChatGptRequestCode.TRANSLATE_ENG);
        String solutionTextPh = chatGptService.createPrompt(solutionEngText, ChatGptRequestCode.TRANSLATE_PH);
        reports.updateSolutionPh(solutionTextPh);
        log.info(">>>> reports.solutionPh : {}", reports.getReportsSolutionPh());
        log.info(">> solutionTextPh 성공: {}", solutionTextPh);

        log.info("{ ReportsService } : 아이속마음 레포트 저장 성공");
    }

    public void createReportAsync(Long reportsId, String kidsUuid) throws ExecutionException, InterruptedException {
        // 부모 조회
        Parents parents = kidsFindService.findKidsByUuidAndIsDeleted(UUID.fromString(kidsUuid)).getParents();
//        log.info(">> 부모 조회 성공 : {}", parents.getParentsEmail());

        log.info("{ ReportsService } : 아이속마음 레포트 저장 진입");

        Reports reports = reportsFindService.findByReportsIdAndIsDeleted(reportsId);
        String originText = reports.getReportsOriginContent();

        log.info(">> summaryText 진입: {}", originText);
        CompletableFuture<String[]> summary = chatGptService.createSummary(originText);

        log.info(">> keywords 진입");
        CompletableFuture<String[]> keywords = chatGptService.createKeywords(originText);

        log.info(">> solution 진입");
        CompletableFuture<String[]> solution = chatGptService.createSolution(originText);

        String[] summaryText = summary.get();
        log.info(">> summaryText 성공: {}", Arrays.toString(summaryText));
        String[] keywordsText = keywords.get();
        log.info(">> keywords 성공: {}", Arrays.toString(keywordsText));
        String[] solutionText = solution.get();
        log.info(">> solutionText 성공: {}", Arrays.toString(solutionText));
        log.info("========= keywordsText : {}", Arrays.stream(keywordsText).toArray());
        log.info("========= keywordsText[0] : {}", keywordsText[0]);
        log.info("========= keywordsText[1] : {}", keywordsText[1]);
        log.info("========= keywordsText[2] : {}", keywordsText[2]);
        String[] keyword_kor_arr = keywordsText[0].split(", ");
        if (keyword_kor_arr.length != 3) {
            keyword_kor_arr = null;
//            throw BaseException.type(ChatGptErrorCode.INVALID_KEYWORD);
        }
        String[] keyword_viet_arr = keywordsText[1].split(", ");
        if (keyword_viet_arr.length != 3) {
            keyword_viet_arr = null;
//            throw BaseException.type(ChatGptErrorCode.INVALID_KEYWORD);
        }

        String[] keyword_ph_arr = keywordsText[2].split(", ");
        if (keyword_ph_arr.length != 3) {
            keyword_ph_arr = null;
//            throw BaseException.type(ChatGptErrorCode.INVALID_KEYWORD);
        }

        // 부모의 국가 베트남
        reports.updateReportsViet(summaryText[0], summaryText[1], summaryText[2], arraytoList(keyword_kor_arr), arraytoList(keyword_viet_arr), arraytoList(keyword_ph_arr), solutionText[0], solutionText[1], solutionText[2]);
        log.info(">>>> reports.summaryKor : {}", reports.getReportsSummaryKor());
        log.info(">>>> reports.summaryViet : {}", reports.getReportsSummaryViet());
        log.info(">>>> reports.summaryPh : {}", reports.getReportsSummaryPh());
        log.info(">>>> reports.keywordKorArr : {}", reports.getReportsKeywordsKor().toString());
        log.info(">>>> reports.keywordVietArr : {}", reports.getReportsKeywordsViet().toString());
        log.info(">>>> reports.keywordPhArr : {}", reports.getReportsKeywordsPh().toString());
        log.info(">>>> reports.solutionKor : {}", reports.getReportsSolutionKor());
        log.info(">>>> reports.solutionViet : {}", reports.getReportsSolutionViet());
        log.info(">>>> reports.solutionPh : {}", reports.getReportsSolutionPh());

        log.info("{ ReportsService } : 아이속마음 레포트 저장 성공");

        log.info(">>>> (부모에게) SSE 알림 전송 시작");
        NotificationRequestDto notificationRequestDto = NotificationRequestDto.builder()
                .receiverUuid(parents.getUuid().toString())
                .url("https://bridgetalk.co.kr/api/reports/"
                        + reports.getKids().getUuid().toString()
                        + "/"
                        + reportsId
                        + "/"
                        + "kor")
                .content(NotificationType.KID_REPORTS_REGISTER.getWord())
                .notificationType(NotificationType.KID_REPORTS_REGISTER)
                .build();
        sseService.send(notificationRequestDto);
        log.info(">>>> (부모에게) SSE 알림 전송 완료");
    }

    public void createReportAsync2(Long reportsId, String kidsUuid) throws ExecutionException, InterruptedException {
        // 부모 조회
        Parents parents = kidsFindService.findKidsByUuidAndIsDeleted(UUID.fromString(kidsUuid)).getParents();
//        log.info(">> 부모 조회 성공 : {}", parents.getParentsEmail());

        log.info("{ ReportsService } : 아이속마음 레포트 저장 진입");

        Reports reports = reportsFindService.findByReportsIdAndIsDeleted(reportsId);
        String originText = reports.getReportsOriginContent();

        log.info(">> summaryText 진입: {}", originText);
        CompletableFuture<String> summary = chatGptService.createSummaryKor(originText);

        log.info(">> keywords 진입");
        CompletableFuture<String> keywords = chatGptService.createKeywordsKor(originText);

        log.info(">> solution 진입");
        CompletableFuture<String> solution = chatGptService.createSolutionKor(originText);

        String summaryText = summary.get();
        log.info(">> summaryText 성공: {}", summaryText);
        String keywordsText = keywords.get();
        log.info(">> keywords 성공: {}", keywordsText);
        String solutionText = solution.get();
        log.info(">> solutionText 성공: {}", solutionText);

        CompletableFuture<String> summaryViet = chatGptService.createSummaryViet(summaryText);
        CompletableFuture<String> summaryPh = chatGptService.createSummaryPh(summaryText);
        CompletableFuture<String> keywordsViet = chatGptService.createKeywordsViet(keywordsText);
        CompletableFuture<String> keywordsPh = chatGptService.createKeywordsPh(keywordsText);
        CompletableFuture<String> solutionViet = chatGptService.createSolutionViet(solutionText);
        CompletableFuture<String> solutionPh = chatGptService.createSolutionPh(solutionText);

        String summaryVietText = summaryViet.get();
        String summaryPhText = summaryPh.get();
        String solutionVietText = solutionViet.get();
        String solutionPhText = solutionPh.get();

        String[] keyword_kor_arr = keywordsText.split(", ");
        if (keyword_kor_arr.length != 3) {
            keyword_kor_arr = null;
//            throw BaseException.type(ChatGptErrorCode.INVALID_KEYWORD);
        }
        String[] keyword_viet_arr = keywordsViet.get().split(", ");
        if (keyword_viet_arr.length != 3) {
            keyword_viet_arr = null;
//            throw BaseException.type(ChatGptErrorCode.INVALID_KEYWORD);
        }

        String[] keyword_ph_arr = keywordsPh.get().split(", ");
        if (keyword_ph_arr.length != 3) {
            keyword_ph_arr = null;
//            throw BaseException.type(ChatGptErrorCode.INVALID_KEYWORD);
        }

        // 부모의 국가 베트남
        reports.updateReportsViet(summaryText, summaryVietText, summaryPhText, arraytoList(keyword_kor_arr), arraytoList(keyword_viet_arr), arraytoList(keyword_ph_arr), solutionText, solutionVietText, solutionPhText);
        log.info(">>>> reports.summaryKor : {}", reports.getReportsSummaryKor());
        log.info(">>>> reports.summaryViet : {}", reports.getReportsSummaryViet());
        log.info(">>>> reports.summaryPh : {}", reports.getReportsSummaryPh());
        log.info(">>>> reports.keywordKorArr : {}", reports.getReportsKeywordsKor().toString());
        log.info(">>>> reports.keywordVietArr : {}", reports.getReportsKeywordsViet().toString());
        log.info(">>>> reports.keywordPhArr : {}", reports.getReportsKeywordsPh().toString());
        log.info(">>>> reports.solutionKor : {}", reports.getReportsSolutionKor());
        log.info(">>>> reports.solutionViet : {}", reports.getReportsSolutionViet());
        log.info(">>>> reports.solutionPh : {}", reports.getReportsSolutionPh());

        log.info("{ ReportsService } : 아이속마음 레포트 저장 성공");

        log.info(">>>> (부모에게) SSE 알림 전송 시작");
        NotificationRequestDto notificationRequestDto = NotificationRequestDto.builder()
                .receiverUuid(parents.getUuid().toString())
                .url("https://bridgetalk.co.kr/api/reports/"
                        + reports.getKids().getUuid().toString()
                        + "/"
                        + reportsId
                        + "/"
                        + "kor")
                .content(NotificationType.KID_REPORTS_REGISTER.getWord())
                .notificationType(NotificationType.KID_REPORTS_REGISTER)
                .build();
        sseService.send(notificationRequestDto);
        log.info(">>>> (부모에게) SSE 알림 전송 완료");
    }



    private List<String> arraytoList(String[] strings) {
        return strings == null ? Collections.emptyList() : new ArrayList<>(Arrays.asList(strings));
    }
}
