package com.ssafy.bridgetalkback.parentingInfo.domain;

import com.ssafy.bridgetalkback.global.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name="parenting_info")
public class ParentingInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parentingInfoId;

    @Column(nullable = false)
    private String titleKor;

    @Column(nullable = false)
    private String titleViet;

    @Column(nullable = false)
    private String titlePh;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contentKor;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contentViet;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contentPh;

    @Column(nullable = false, length = 300)
    private String link;

    @Convert(converter = Category.CategoryConverter.class)
    @Column(nullable = false, length = 20)
    private Category category;

    private ParentingInfo(String titleKor, String titleViet, String titlePh, String contentKor, String contentViet,
                          String contentPh, String link, Category category) {
        this.titleKor = titleKor;
        this.titleViet = titleViet;
        this.titlePh = titlePh;
        this.contentKor = contentKor;
        this.contentViet = contentViet;
        this.contentPh = contentPh;
        this.link = link;
        this.category = category;
    }

    public static ParentingInfo createParentingInfo(String titleKor, String titleViet, String titlePh, String contentKor, String contentViet,
                                                    String contentPh, String link, Category category) {
        return new ParentingInfo(titleKor, titleViet, titlePh, contentKor, contentViet, contentPh, link, category);
    }
}
