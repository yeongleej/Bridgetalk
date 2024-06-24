package com.ssafy.bridgetalkback.reports.domain;

import com.ssafy.bridgetalkback.global.BaseEntity;
import com.ssafy.bridgetalkback.global.utils.StringListConverter;
import com.ssafy.bridgetalkback.kids.domain.Kids;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reports")
public class Reports extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kids_uuid")
    private Kids kids;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String reportsOriginContent;

    @Column(columnDefinition = "TEXT")
    private String reportsSummaryKor;

    @Column(columnDefinition = "TEXT")
    private String reportsSummaryViet;

    @Column(columnDefinition = "TEXT")
    private String reportsSummaryPh;

    @Convert(converter = StringListConverter.class)
    private List<String> reportsKeywordsKor;

    @Convert(converter = StringListConverter.class)
    private List<String> reportsKeywordsViet;

    @Convert(converter = StringListConverter.class)
    private List<String> reportsKeywordsPh;

    @Column(columnDefinition = "TEXT")
    private String reportsSolutionKor;

    @Column(columnDefinition = "TEXT")
    private String reportsSolutionViet;

    @Column(columnDefinition = "TEXT")
    private String reportsSolutionPh;

    private Reports(Kids kids, String reportsOriginContent) {
        this.kids = kids;
        this.reportsOriginContent = reportsOriginContent;
    }

    public static Reports createReports(Kids kids, String reportsOriginContent) {
        return new Reports(kids, reportsOriginContent);
    }

    public void updateSummaryKor(String summaryTextKor) {
        this.reportsSummaryKor = summaryTextKor;
    }

    public void updateSummaryViet(String summaryTextViet) {
        this.reportsSummaryViet = summaryTextViet;
    }
    public void updateSummaryPh(String summaryTextPh) {
        this.reportsSummaryPh = summaryTextPh;
    }

    public void updateKeywordsKor(List<String> keywordKorList) {
        this.reportsKeywordsKor = keywordKorList;
    }

    public void updateKeywordsViet(List<String> keywordVietList) {
        this.reportsKeywordsViet = keywordVietList;
    }
    public void updateKeywordsPh(List<String> keywordVietList) {
        this.reportsKeywordsPh = keywordVietList;
    }

    public void updateSolutionKor(String solutionTextKor) {
        this.reportsSolutionKor = solutionTextKor;
    }

    public void updateSolutionViet(String solutionTextViet) {
        this.reportsSolutionViet = solutionTextViet;
    }
    public void updateSolutionPh(String solutionTextViet) {
        this.reportsSolutionPh = solutionTextViet;
    }

    public void updateReportsViet(String summaryTextKor, String summaryTextViet, String summaryTextPh,List<String> keywordKorList, List<String> keywordVietList, List<String> keywordPhList, String solutionTextKor, String solutionTextViet, String solutionTextPh) {
        this.reportsSummaryKor = summaryTextKor;
        this.reportsSummaryViet = summaryTextViet;
        this.reportsSummaryPh = summaryTextPh;
        this.reportsKeywordsKor = keywordKorList;
        this.reportsKeywordsViet = keywordVietList;
        this.reportsKeywordsPh = keywordPhList;
        this.reportsSolutionKor = solutionTextKor;
        this.reportsSolutionViet = solutionTextViet;
        this.reportsSolutionPh = solutionTextPh;
    }

    public void updateReportsOriginContent(String updateContent) {
        this.reportsOriginContent = updateContent;
    }
}
