package com.ssafy.bridgetalkback.parentingInfo.service;

import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.parentingInfo.domain.ParentingInfo;
import com.ssafy.bridgetalkback.parentingInfo.dto.response.CustomParentingInfoListResponseDto;
import com.ssafy.bridgetalkback.parentingInfo.exception.ParentingInfoErrorCode;
import com.ssafy.bridgetalkback.parentingInfo.query.dto.ParentingInfoListDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ssafy.bridgetalkback.fixture.ParentingInfoFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("ParentingInfoList [Service Layer] -> ParentingInfoListService 테스트")
public class ParentingInfoListServiceTest extends ServiceTest {
    @Autowired
    private ParentingInfoListService parentingInfoListService;

    private final ParentingInfo[] parentingInfoList = new ParentingInfo[6];

    private static final int PAGE = 0;
    private static final String SEARCH_CATEGORY = "prospective";
    private static final String INVALID_SEARCH_CATEGORY = "not_prospective";
    private static final int PAGE_SIZE = 4;

    @BeforeEach
    void setup() {
        parentingInfoList[0] = parentingInfoRepository.save(PARENTINGINFO_01.toParentingInfo());
        parentingInfoList[1] = parentingInfoRepository.save(PARENTINGINFO_02.toParentingInfo());
        parentingInfoList[2] = parentingInfoRepository.save(PARENTINGINFO_03.toParentingInfo());
        parentingInfoList[3] = parentingInfoRepository.save(PARENTINGINFO_04.toParentingInfo());
        parentingInfoList[4] = parentingInfoRepository.save(PARENTINGINFO_05.toParentingInfo());
        parentingInfoList[5] = parentingInfoRepository.save(PARENTINGINFO_06.toParentingInfo());
    }

    @Nested
    @DisplayName("육아정보 리스트 조회")
    class customParentingInfoList {
        @Test
        @DisplayName("존재하지 않는 검색 카테고리라면 육아정보 리스트 조회에 실패한다")
        void throwNotFoundSearchCategory() {
            // when - then
            assertThatThrownBy(() -> parentingInfoListService.getCustomParentingInfoList(PAGE, INVALID_SEARCH_CATEGORY, Language.kor))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(ParentingInfoErrorCode.CATEGORY_NOT_FOUND.getMessage());
        }

        @Test
        @DisplayName("(한국어) 육아정보 리스트조회(카테고리별)에 성공한다")
        void successKor() {
            // when
            CustomParentingInfoListResponseDto<ParentingInfoListDto> responseDto = parentingInfoListService.getCustomParentingInfoList(PAGE, SEARCH_CATEGORY, Language.kor);

            // then
            assertThat(responseDto.parentingInfoList().size()).isLessThanOrEqualTo(PAGE_SIZE);
            assertThat(responseDto.pageInfo().totalPages()).isLessThanOrEqualTo(PAGE_SIZE);

            assertAll(
                    () -> assertThat(responseDto.pageInfo().totalPages()).isEqualTo(2),
                    () -> assertThat(responseDto.pageInfo().totalElements()).isEqualTo(6),
                    () -> assertThat(responseDto.pageInfo().hasNext()).isTrue(),
                    () -> assertThat(responseDto.pageInfo().numberOfElements()).isEqualTo(PAGE_SIZE),
                    () -> assertThat(responseDto.parentingInfoList().size()).isLessThanOrEqualTo(PAGE_SIZE),
                    () -> assertThat(responseDto.parentingInfoList().get(0).parentingInfoId()).isEqualTo(parentingInfoList[0].getParentingInfoId()),
                    () -> assertThat(responseDto.parentingInfoList().get(0).title()).isEqualTo(parentingInfoList[0].getTitleKor()),
                    () -> assertThat(responseDto.parentingInfoList().get(0).content()).isEqualTo(parentingInfoList[0].getContentKor()),
                    () -> assertThat(responseDto.parentingInfoList().get(0).link()).isEqualTo(parentingInfoList[0].getLink()),
                    () -> assertThat(responseDto.parentingInfoList().get(0).category()).isEqualTo(parentingInfoList[0].getCategory().getValue())
            );
        }

        @Test
        @DisplayName("(베트남어) 육아정보 리스트조회(카테고리별)에 성공한다")
        void successViet() {
            // when
            CustomParentingInfoListResponseDto<ParentingInfoListDto> responseDto = parentingInfoListService.getCustomParentingInfoList(PAGE, SEARCH_CATEGORY, Language.viet);

            // then
            assertThat(responseDto.parentingInfoList().size()).isLessThanOrEqualTo(PAGE_SIZE);
            assertThat(responseDto.pageInfo().totalPages()).isLessThanOrEqualTo(PAGE_SIZE);

            assertAll(
                    () -> assertThat(responseDto.pageInfo().totalPages()).isEqualTo(2),
                    () -> assertThat(responseDto.pageInfo().totalElements()).isEqualTo(6),
                    () -> assertThat(responseDto.pageInfo().hasNext()).isTrue(),
                    () -> assertThat(responseDto.pageInfo().numberOfElements()).isEqualTo(PAGE_SIZE),
                    () -> assertThat(responseDto.parentingInfoList().size()).isLessThanOrEqualTo(PAGE_SIZE),
                    () -> assertThat(responseDto.parentingInfoList().get(0).parentingInfoId()).isEqualTo(parentingInfoList[0].getParentingInfoId()),
                    () -> assertThat(responseDto.parentingInfoList().get(0).title()).isEqualTo(parentingInfoList[0].getTitleViet()),
                    () -> assertThat(responseDto.parentingInfoList().get(0).content()).isEqualTo(parentingInfoList[0].getContentViet()),
                    () -> assertThat(responseDto.parentingInfoList().get(0).link()).isEqualTo(parentingInfoList[0].getLink()),
                    () -> assertThat(responseDto.parentingInfoList().get(0).category()).isEqualTo(parentingInfoList[0].getCategory().getValue())
            );
        }

        @Test
        @DisplayName("(필리핀어) 육아정보 리스트조회(카테고리별)에 성공한다")
        void successPh() {
            // when
            CustomParentingInfoListResponseDto<ParentingInfoListDto> responseDto = parentingInfoListService.getCustomParentingInfoList(PAGE, SEARCH_CATEGORY, Language.ph);

            // then
            assertThat(responseDto.parentingInfoList().size()).isLessThanOrEqualTo(PAGE_SIZE);
            assertThat(responseDto.pageInfo().totalPages()).isLessThanOrEqualTo(PAGE_SIZE);

            assertAll(
                    () -> assertThat(responseDto.pageInfo().totalPages()).isEqualTo(2),
                    () -> assertThat(responseDto.pageInfo().totalElements()).isEqualTo(6),
                    () -> assertThat(responseDto.pageInfo().hasNext()).isTrue(),
                    () -> assertThat(responseDto.pageInfo().numberOfElements()).isEqualTo(PAGE_SIZE),
                    () -> assertThat(responseDto.parentingInfoList().size()).isLessThanOrEqualTo(PAGE_SIZE),
                    () -> assertThat(responseDto.parentingInfoList().get(0).parentingInfoId()).isEqualTo(parentingInfoList[0].getParentingInfoId()),
                    () -> assertThat(responseDto.parentingInfoList().get(0).title()).isEqualTo(parentingInfoList[0].getTitlePh()),
                    () -> assertThat(responseDto.parentingInfoList().get(0).content()).isEqualTo(parentingInfoList[0].getContentPh()),
                    () -> assertThat(responseDto.parentingInfoList().get(0).link()).isEqualTo(parentingInfoList[0].getLink()),
                    () -> assertThat(responseDto.parentingInfoList().get(0).category()).isEqualTo(parentingInfoList[0].getCategory().getValue())
            );
        }
    }
}
