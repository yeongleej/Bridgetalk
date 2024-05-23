package com.ssafy.bridgetalkback.parentingInfo.service;

import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.parentingInfo.domain.Category;
import com.ssafy.bridgetalkback.parentingInfo.domain.ParentingInfoBoardNum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ParentingInfoCrawling [Service Layer] -> ParentingInfoCrawling 테스트")
public class ParentingInfoCrawlingServiceTest extends ServiceTest {
    @Autowired
    private ParentingInfoCrawlingService parentingInfoCrawlingService;

    private List<String> urlList;

    @BeforeEach
    void setup() {
        boardNumRepository.save(ParentingInfoBoardNum.createBoardNum("707973", Category.PROSPECTIVE));
        urlList = new ArrayList<>();
        urlList.add("https://www.mogef.go.kr/kps/olb/kps_olb_s001d.do?mid=mda753&div1=mda75301&cd=kps&bbtSn=707973");
    }

    @Test
    @DisplayName("글번호로부터 urlList를 생성한다")
    void createUrlList() {
        // when
        List<String> urlList = parentingInfoCrawlingService.createUrlList("75301", Category.PROSPECTIVE);

        // then
        Assertions.assertAll(
                () -> assertThat(urlList.size()).isEqualTo(1),
                () -> assertThat(urlList.get(0)).isEqualTo("https://www.mogef.go.kr/kps/olb/kps_olb_s001d.do?mid=mda753&div1=mda75301&cd=kps&bbtSn=707973")
        );
    }
}
