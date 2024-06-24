package com.ssafy.bridgetalkback.parentingInfo.service;

import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.parentingInfo.domain.ParentingInfo;
import com.ssafy.bridgetalkback.parentingInfo.dto.response.ParentingInfoResponseDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ssafy.bridgetalkback.fixture.ParentingInfoFixture.PARENTINGINFO_01;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ParentingInfo [Service Layer] -> ParentingInfoService 테스트")
public class ParentingInfoServiceTest extends ServiceTest {
    @Autowired
    private ParentingInfoService parentingInfoService;

    @Autowired
    private ParentingInfoFindService parentingInfoFindService;

    private ParentingInfo parentingInfo;

    @BeforeEach
    void setup() {
        parentingInfo = parentingInfoRepository.save(PARENTINGINFO_01.toParentingInfo());
    }

    @Test
    @DisplayName("육아정보 등록에 성공한다")
    void createParentingInfo() {
        // given
        Long parentingInfoId = parentingInfoService.createParentingInfo(PARENTINGINFO_01.getTitleKor(), PARENTINGINFO_01.getTitleViet(),
                PARENTINGINFO_01.getTitlePh(), PARENTINGINFO_01.getContentKor(), PARENTINGINFO_01.getContentViet(), PARENTINGINFO_01.getContentPh(),
                PARENTINGINFO_01.getLink(), PARENTINGINFO_01.getCategory());

        // when - then
        ParentingInfo newParentingInfo = parentingInfoFindService.findParentingInfoByParentingInfoIdAndIsDeleted(parentingInfoId);
        Assertions.assertAll(
                () -> assertThat(newParentingInfo.getParentingInfoId()).isEqualTo(parentingInfoId),
                () -> assertThat(newParentingInfo.getTitleKor()).isEqualTo(PARENTINGINFO_01.getTitleKor()),
                () -> assertThat(newParentingInfo.getTitleViet()).isEqualTo(PARENTINGINFO_01.getTitleViet()),
                () -> assertThat(newParentingInfo.getTitlePh()).isEqualTo(PARENTINGINFO_01.getTitlePh()),
                () -> assertThat(newParentingInfo.getContentKor()).isEqualTo(PARENTINGINFO_01.getContentKor()),
                () -> assertThat(newParentingInfo.getContentViet()).isEqualTo(PARENTINGINFO_01.getContentViet()),
                () -> assertThat(newParentingInfo.getContentPh()).isEqualTo(PARENTINGINFO_01.getContentPh()),
                () -> assertThat(newParentingInfo.getLink()).isEqualTo(PARENTINGINFO_01.getLink()),
                () -> assertThat(newParentingInfo.getCategory()).isEqualTo(PARENTINGINFO_01.getCategory()),
                () -> assertThat(newParentingInfo.getIsDeleted()).isEqualTo(0)
        );
    }

    @Nested
    @DisplayName("육아정보 상세조회에 성공한다")
    class parentingInfoDetail {
        @Test
        @DisplayName("(한국어) 육아정보 상세조회에 성공한다")
        void successKor() {
            // given
            ParentingInfoResponseDto responseDto = parentingInfoService.getParentingInfoDetail(parentingInfo.getParentingInfoId(), Language.kor);

            // when - then
            Assertions.assertAll(
                    () -> assertThat(responseDto.parentingInfoId()).isEqualTo(parentingInfo.getParentingInfoId()),
                    () -> assertThat(responseDto.title()).isEqualTo(parentingInfo.getTitleKor()),
                    () -> assertThat(responseDto.content()).isEqualTo(parentingInfo.getContentKor()),
                    () -> assertThat(responseDto.link()).isEqualTo(parentingInfo.getLink()),
                    () -> assertThat(responseDto.category()).isEqualTo(parentingInfo.getCategory().getValue())
            );
        }

        @Test
        @DisplayName("(베트남어) 육아정보 상세조회에 성공한다")
        void successViet() {
            // given
            ParentingInfoResponseDto responseDto = parentingInfoService.getParentingInfoDetail(parentingInfo.getParentingInfoId(), Language.viet);

            // when - then
            Assertions.assertAll(
                    () -> assertThat(responseDto.parentingInfoId()).isEqualTo(parentingInfo.getParentingInfoId()),
                    () -> assertThat(responseDto.title()).isEqualTo(parentingInfo.getTitleViet()),
                    () -> assertThat(responseDto.content()).isEqualTo(parentingInfo.getContentViet()),
                    () -> assertThat(responseDto.link()).isEqualTo(parentingInfo.getLink()),
                    () -> assertThat(responseDto.category()).isEqualTo(parentingInfo.getCategory().getValue())
            );
        }

        @Test
        @DisplayName("(필리핀어) 육아정보 상세조회에 성공한다")
        void successPh() {
            // given
            ParentingInfoResponseDto responseDto = parentingInfoService.getParentingInfoDetail(parentingInfo.getParentingInfoId(), Language.ph);

            // when - then
            Assertions.assertAll(
                    () -> assertThat(responseDto.parentingInfoId()).isEqualTo(parentingInfo.getParentingInfoId()),
                    () -> assertThat(responseDto.title()).isEqualTo(parentingInfo.getTitlePh()),
                    () -> assertThat(responseDto.content()).isEqualTo(parentingInfo.getContentPh()),
                    () -> assertThat(responseDto.link()).isEqualTo(parentingInfo.getLink()),
                    () -> assertThat(responseDto.category()).isEqualTo(parentingInfo.getCategory().getValue())
            );
        }
    }
}
