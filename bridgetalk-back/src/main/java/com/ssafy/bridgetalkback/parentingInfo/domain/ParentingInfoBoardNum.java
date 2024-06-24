package com.ssafy.bridgetalkback.parentingInfo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name="parenting_info_board_num")
public class ParentingInfoBoardNum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNumId;

    @Column(nullable = false, length = 10)
    private String num;

    @Convert(converter = Category.CategoryConverter.class)
    @Column(nullable = false, length = 20)
    private Category age;

    private ParentingInfoBoardNum(String num, Category age) {
        this.num = num;
        this.age = age;
    }

    public static ParentingInfoBoardNum createBoardNum(String num, Category age) {
        return new ParentingInfoBoardNum(num, age);
    }
}
