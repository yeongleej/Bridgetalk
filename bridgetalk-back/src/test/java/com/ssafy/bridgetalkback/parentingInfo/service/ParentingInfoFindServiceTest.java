package com.ssafy.bridgetalkback.parentingInfo.service;

import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.parentingInfo.domain.ParentingInfo;
import com.ssafy.bridgetalkback.parentingInfo.exception.ParentingInfoErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ssafy.bridgetalkback.fixture.ParentingInfoFixture.PARENTINGINFO_01;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("ParentingInfo [Service Layer] -> ParentingInfoFindService 테스트")
public class ParentingInfoFindServiceTest extends ServiceTest {
    @Autowired
    private ParentingInfoFindService parentingInfoFindService;

    private ParentingInfo parentingInfo;

    @BeforeEach
    void setup() {
        parentingInfo = parentingInfoRepository.save(PARENTINGINFO_01.toParentingInfo());
    }

    @Test
    @DisplayName("ID(PK)로 육아 정보를 조회한다")
    void findParentingInfoByParentingInfoIdAndIsDeleted() {
        // when
        ParentingInfo findParentingInfo = parentingInfoFindService.findParentingInfoByParentingInfoIdAndIsDeleted(parentingInfo.getParentingInfoId());

        // then
        assertThatThrownBy(() -> parentingInfoFindService.findParentingInfoByParentingInfoIdAndIsDeleted(findParentingInfo.getParentingInfoId() + 100L))
                .isInstanceOf(BaseException.class)
                .hasMessage(ParentingInfoErrorCode.PARENTINGINFO_NOT_FOUND.getMessage());

        assertThat(findParentingInfo).isEqualTo(parentingInfo);
    }
}
