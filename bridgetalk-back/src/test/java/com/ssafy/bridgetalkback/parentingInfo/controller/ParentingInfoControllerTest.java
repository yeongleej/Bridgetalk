package com.ssafy.bridgetalkback.parentingInfo.controller;

import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.common.ControllerTest;
import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.parentingInfo.dto.response.CustomParentingInfoListResponseDto;
import com.ssafy.bridgetalkback.parentingInfo.dto.response.ParentingInfoResponseDto;
import com.ssafy.bridgetalkback.parentingInfo.exception.ParentingInfoErrorCode;
import com.ssafy.bridgetalkback.parentingInfo.query.dto.ParentingInfoListDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static com.ssafy.bridgetalkback.fixture.ParentingInfoFixture.*;
import static com.ssafy.bridgetalkback.fixture.TokenFixture.BEARER_TOKEN;
import static com.ssafy.bridgetalkback.fixture.TokenFixture.REFRESH_TOKEN;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ParentingInfoControllerTest extends ControllerTest {
    @Nested
    @DisplayName("육아 정보 상세조회 API [GET /api/parentingInfo/{parentingInfoId}/{language}]")
    class parentingInfoDetail {
        private static final String BASE_URL = "/api/parentingInfo/{parentingInfoId}/{language}";
        private static final Long PARENTINGINFO_ID = 1L;
        private static final Language LANGUAGE_KOR = Language.kor;

        @Test
        @DisplayName("Authorization_Header에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL, PARENTINGINFO_ID, LANGUAGE_KOR);

            // then
            final AuthErrorCode expectedError = AuthErrorCode.INVALID_PERMISSION;
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isForbidden(),
                            jsonPath("$.status").exists(),
                            jsonPath("$.status").value(expectedError.getStatus().value()),
                            jsonPath("$.errorCode").exists(),
                            jsonPath("$.errorCode").value(expectedError.getErrorCode()),
                            jsonPath("$.message").exists(),
                            jsonPath("$.message").value(expectedError.getMessage())
                    );

        }

        @Test
        @DisplayName("육아 정보 상세조회에 성공한다")
        void success() throws Exception {
            // given
            doReturn(getParentingInfoResponseDto())
                    .when(parentingInfoService)
                    .getParentingInfoDetail(anyLong(), any());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL, PARENTINGINFO_ID, LANGUAGE_KOR)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("육아 정보 리스트조회 (카테고리별) API [GET /api/parentingInfo/{language}]")
    class parentingInfoList {
        private static final String BASE_URL = "/api/parentingInfo/{language}";
        private static final int PAGE = 0;
        private static final String SEARCH_CATEGORY = "prospective";
        private static final String INVALID_SEARCH_CATEGORY = "not_prospective";
        private static final Language LANGUAGE_KOR = Language.kor;

        @Test
        @DisplayName("Authorization_Header에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL, LANGUAGE_KOR);

            // then
            final AuthErrorCode expectedError = AuthErrorCode.INVALID_PERMISSION;
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isForbidden(),
                            jsonPath("$.status").exists(),
                            jsonPath("$.status").value(expectedError.getStatus().value()),
                            jsonPath("$.errorCode").exists(),
                            jsonPath("$.errorCode").value(expectedError.getErrorCode()),
                            jsonPath("$.message").exists(),
                            jsonPath("$.message").value(expectedError.getMessage())
                    );

        }

        @Test
        @DisplayName("존재하지 않는 검색 카테고리라면 육아정보 리스트 조회에 실패한다")
        void throwExceptionByNotFoundSearchCategory() throws Exception {
            // given
            doThrow(BaseException.type(ParentingInfoErrorCode.CATEGORY_NOT_FOUND))
                    .when(parentingInfoListService)
                    .getCustomParentingInfoList(anyInt(), any(), any());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL, LANGUAGE_KOR)
                    .param("page", String.valueOf(PAGE))
                    .param("searchCategory", INVALID_SEARCH_CATEGORY)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);


            // then
            final ParentingInfoErrorCode expectedError = ParentingInfoErrorCode.CATEGORY_NOT_FOUND;
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isNotFound(),
                            jsonPath("$.status").exists(),
                            jsonPath("$.status").value(expectedError.getStatus().value()),
                            jsonPath("$.errorCode").exists(),
                            jsonPath("$.errorCode").value(expectedError.getErrorCode()),
                            jsonPath("$.message").exists(),
                            jsonPath("$.message").value(expectedError.getMessage())
                    );
        }

        @Test
        @DisplayName("육아 정보 리스트조회 (카테고리별)에 성공한다")
        void success() throws Exception {
            // given
            doReturn(getCustomParentingInfoListResponseDto())
                    .when(parentingInfoListService)
                    .getCustomParentingInfoList(anyInt(), any(), any());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL, LANGUAGE_KOR)
                    .param("page", String.valueOf(PAGE))
                    .param("searchCategory", SEARCH_CATEGORY)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());
        }
    }

    private ParentingInfoResponseDto getParentingInfoResponseDto() {
        return new ParentingInfoResponseDto(1L, PARENTINGINFO_01.getTitleKor(), PARENTINGINFO_01.getContentKor(),
                PARENTINGINFO_01.getLink(), PARENTINGINFO_01.getCategory().getValue());
    }

    private List<ParentingInfoListDto> createParentingInfoList() {
        List<ParentingInfoListDto> parentingInfoLists = new ArrayList<>();
        parentingInfoLists.add(new ParentingInfoListDto(1L, PARENTINGINFO_01.getTitleKor(), PARENTINGINFO_01.getContentKor(),
                PARENTINGINFO_01.getLink(), PARENTINGINFO_01.getCategory().getValue()));
        parentingInfoLists.add(new ParentingInfoListDto(2L, PARENTINGINFO_02.getTitleKor(), PARENTINGINFO_02.getContentKor(),
                PARENTINGINFO_02.getLink(), PARENTINGINFO_02.getCategory().getValue()));
        parentingInfoLists.add(new ParentingInfoListDto(3L, PARENTINGINFO_03.getTitleKor(), PARENTINGINFO_03.getContentKor(),
                PARENTINGINFO_03.getLink(), PARENTINGINFO_03.getCategory().getValue()));
        return parentingInfoLists;
    }

    private CustomParentingInfoListResponseDto.CustomPageable createCustomPageable() {
        return new CustomParentingInfoListResponseDto.CustomPageable(1, 4, false, 4);
    }

    private CustomParentingInfoListResponseDto getCustomParentingInfoListResponseDto() {
        return new CustomParentingInfoListResponseDto<>(createCustomPageable(), createParentingInfoList());
    }
}
