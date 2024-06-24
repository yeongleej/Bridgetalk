package com.ssafy.bridgetalkback.letters.domain;

import com.ssafy.bridgetalkback.global.BaseEntity;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.reports.domain.Reports;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="letters")
public class Letters extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lettersId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parents_uuid")
    private Parents parents;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reports_id")
    private Reports reports;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String lettersOriginalContent;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String lettersTranslationContent;

    @Column(columnDefinition = "integer default 0")
    private int isChecked;
    
    private Letters(Parents parents, Reports reports, String lettersOriginalContent, String lettersTranslationContent) {
        this.parents = parents;
        this.reports = reports;
        this.lettersOriginalContent = lettersOriginalContent;
        this.lettersTranslationContent = lettersTranslationContent;
    }

    public static Letters createLetters(Parents parents, Reports reports, String lettersOriginalContent, String lettersTranslationContent) {
        return new Letters(parents, reports, lettersOriginalContent, lettersTranslationContent);
    }

    public void updateIsChecked() {
        this.isChecked = 1;
    }

}
