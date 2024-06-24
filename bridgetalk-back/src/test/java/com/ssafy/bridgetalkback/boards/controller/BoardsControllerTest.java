package com.ssafy.bridgetalkback.boards.controller;

import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.boards.dto.request.BoardsRequestDto;
import com.ssafy.bridgetalkback.boards.dto.request.BoardsUpdateRequestDto;
import com.ssafy.bridgetalkback.boards.dto.response.BoardsListResponseDto;
import com.ssafy.bridgetalkback.boards.dto.response.BoardsResponseDto;
import com.ssafy.bridgetalkback.common.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import com.ssafy.bridgetalkback.boards.dto.response.CustomBoardsListResponseDto;
import com.ssafy.bridgetalkback.boards.exception.BoardsErrorCode;
import com.ssafy.bridgetalkback.boards.query.dto.BoardsListDto;
import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static com.ssafy.bridgetalkback.fixture.BoardsFixture.*;
import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SUNKYOUNG;
import static com.ssafy.bridgetalkback.fixture.TokenFixture.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Boards [Controller Layer] -> BoardsControllerTest 테스트")
public class BoardsControllerTest extends ControllerTest {

    @Nested
    @DisplayName("Boards 생성 API [POST /api/boards]")
    class createBoards {
        private static final String BASE_URL = "/api/boards";

        @Test
        @DisplayName("Authorization_Header에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            // when
            final BoardsRequestDto boardsRequestDto = createBoardsRequestDto(Language.kor);
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(boardsRequestDto));

            //then
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
        @DisplayName("(한국어) 게시글 등록에 성공한다.")
        void successKor() throws Exception {
            //given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(getBoardsResponseDto())
                    .when(boardsService)
                    .createBoards(any(), any());

            //when
            final BoardsRequestDto boardsRequestDto = createBoardsRequestDto(Language.kor);
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(boardsRequestDto));

            //then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isCreated());
        }

        @Test
        @DisplayName("(베트남어) 게시글 등록에 성공한다.")
        void successViet() throws Exception {
            //given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(getBoardsResponseDto())
                    .when(boardsService)
                    .createBoards(any(), any());

            //when
            final BoardsRequestDto boardsRequestDto = createBoardsRequestDto(Language.viet);
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(boardsRequestDto));

            //then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isCreated());
        }

        @Test
        @DisplayName("(필리핀어) 게시글 등록에 성공한다.")
        void successPh() throws Exception {
            //given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(getBoardsResponseDto())
                    .when(boardsService)
                    .createBoards(any(), any());

            //when
            final BoardsRequestDto boardsRequestDto = createBoardsRequestDto(Language.ph);
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(boardsRequestDto));

            //then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isCreated());
        }
    }

    @Nested
    @DisplayName("Boards 수정 API [PATCH /api/boards/{boardsId}]")
    class updateBoards {
        private static final String BASE_URL = "/api/boards/{boardsId}";
        private static final Long BOARDS_ID = 1L;

        @Test
        @DisplayName("(한국어) 게시글 수정에 성공한다.")
        void successKor() throws Exception {
            //given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(getBoardsResponseDto())
                    .when(boardsService)
                    .updateBoards(any(), any(), any());

            //when
            final BoardsUpdateRequestDto boardsUpdateRequestDto = createBoardsUpdateRequestDto(Language.kor);
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .patch(BASE_URL, BOARDS_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(boardsUpdateRequestDto));

            //then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("(베트남어) 게시글 수정에 성공한다.")
        void successViet() throws Exception {
            //given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(getBoardsResponseDto())
                    .when(boardsService)
                    .updateBoards(any(), any(), any());

            //when
            final BoardsUpdateRequestDto boardsUpdateRequestDto = createBoardsUpdateRequestDto(Language.viet);
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .patch(BASE_URL, BOARDS_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(boardsUpdateRequestDto));

            //then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("(필리핀어) 게시글 수정에 성공한다.")
        void successPh() throws Exception {
            //given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(getBoardsResponseDto())
                    .when(boardsService)
                    .updateBoards(any(), any(), any());

            //when
            final BoardsUpdateRequestDto boardsUpdateRequestDto = createBoardsUpdateRequestDto(Language.ph);
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .patch(BASE_URL, BOARDS_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(boardsUpdateRequestDto));

            //then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("Boards 삭제 API [DELETE /api/boards/{boardsId}]")
    class deleteBoards {
        private static final String BASE_URL = "/api/boards/{boardsId}";
        private static final Long BOARDS_ID = 1L;

        @Test
        @DisplayName("Authorization_Header에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .delete(BASE_URL, BOARDS_ID);

            //then
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
        @DisplayName("게시글 삭제에 성공한다.")
        void success() throws Exception {
            //given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doNothing()
                    .when(boardsService)
                    .deleteBoards(any(), any());

            //when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .delete(BASE_URL, BOARDS_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            //then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());
        }
    }


    @Nested
    @DisplayName("게시글 상세조회 API [GET /api/boards/read/{boardsId}/{language}]")
    class parentingInfoDetail {
        private static final String BASE_URL = "/api/boards/read/{boardsId}/{language}";
        private static final Long BOARDS_ID = 1L;
        private static final Language LANGUAGE_KOR = Language.kor;

        @Test
        @DisplayName("게시글 상세조회에 성공한다")
        void success() throws Exception {
            // given
            doReturn(getBoardsResponseDto())
                    .when(boardsService)
                    .getBoardsDetail(anyLong(), any());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL, BOARDS_ID, LANGUAGE_KOR);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());
        }
    }


    @Nested
    @DisplayName("게시글 리스트조회 API [GET /api/boards/read/{language}]")
    class getCustomBoardsList {
        private static final String BASE_URL = "/api/boards/read/{language}";
        private static final Language LANGUAGE_KOR = Language.kor;
        private static final int PAGE = 0;
        private static final String SEARCH_TYPE = "제목";
        private static final String SEARCH_WORD_KOR = "제목";
        private static final String SORT_CONDITION = "최신순";
        private static final String INVALID_SEARCH_TYPE = "댓글";
        private static final String INVALID_SORT_CONDITION = "조회순";

        @Test
        @DisplayName("존재하지 않는 검색 조건이라면 게시글 리스트 조회에 실패한다")
        void throwNotFoundSearchType() throws Exception {
            // given
            doThrow(BaseException.type(BoardsErrorCode.SEARCH_TYPE_NOT_FOUND))
                    .when(boardsListService)
                    .getCustomBoardsList(anyInt(), any(), any(), any(), any());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL, LANGUAGE_KOR)
                    .param("page", String.valueOf(PAGE))
                    .param("searchType", INVALID_SEARCH_TYPE)
                    .param("searchWord", SEARCH_WORD_KOR)
                    .param("sort", SORT_CONDITION);


            // then
            final BoardsErrorCode expectedError = BoardsErrorCode.SEARCH_TYPE_NOT_FOUND;
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
        @DisplayName("존재하지 않는 정렬 유형이라면 게시글 리스트 조회에 실패한다")
        void throwNotFoundSearchCondition() throws Exception {
            // given
            doThrow(BaseException.type(BoardsErrorCode.SORT_CONDITION_NOT_FOUND))
                    .when(boardsListService)
                    .getCustomBoardsList(anyInt(), any(), any(), any(), any());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL, LANGUAGE_KOR)
                    .param("page", String.valueOf(PAGE))
                    .param("searchType", SEARCH_TYPE)
                    .param("searchWord", SEARCH_WORD_KOR)
                    .param("sort", INVALID_SORT_CONDITION);


            // then
            final BoardsErrorCode expectedError = BoardsErrorCode.SORT_CONDITION_NOT_FOUND;
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
        @DisplayName("정렬 유형과 검색 조건에 따른 게시글 리스트 조회에 성공한다")
        void success() throws Exception {
            // given
            doReturn(getCustomBoardsListResponseDto())
                    .when(boardsListService)
                    .getCustomBoardsList(anyInt(), any(), any(), any(), any());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL, LANGUAGE_KOR)
                    .param("page", String.valueOf(PAGE))
                    .param("searchType", SEARCH_TYPE)
                    .param("searchWord", SEARCH_WORD_KOR)
                    .param("sort", SORT_CONDITION);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("게시글좋아요 등록 API [POST /api/boards/likes/{boardsId}]")
    class register {
        private static final String BASE_URL = "/api/boards/likes/{boardsId}";
        private static final Long BOARD_ID = 1L;

        @Test
        @DisplayName("Authorization Header에 AccessToken이 없으면 게시글좋아요 등록에 실패한다")
        void withoutAccessToken() throws Exception {
            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL, BOARD_ID);

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
        @DisplayName("본인의 게시글에는 좋아요를 누를 수 없다")
        void throwExceptionBySelfFollowNotAllowed() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doThrow(BaseException.type(BoardsErrorCode.SELF_BOARD_LIKE_NOT_ALLOWED))
                    .when(boardsLikeService)
                    .register(any(), anyLong());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL, BOARD_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            final BoardsErrorCode expectedError = BoardsErrorCode.SELF_BOARD_LIKE_NOT_ALLOWED;
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isConflict(),
                            jsonPath("$.status").exists(),
                            jsonPath("$.status").value(expectedError.getStatus().value()),
                            jsonPath("$.errorCode").exists(),
                            jsonPath("$.errorCode").value(expectedError.getErrorCode()),
                            jsonPath("$.message").exists(),
                            jsonPath("$.message").value(expectedError.getMessage())
                    );
        }

        @Test
        @DisplayName("한 게시글에 두 번 이상 좋아요를 누를 수 없다")
        void throwExceptionByAlreadyBoardLike() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doThrow(BaseException.type(BoardsErrorCode.ALREADY_BOARD_LIKE))
                    .when(boardsLikeService)
                    .register(any(), anyLong());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL, BOARD_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            final BoardsErrorCode expectedError = BoardsErrorCode.ALREADY_BOARD_LIKE;
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isConflict(),
                            jsonPath("$.status").exists(),
                            jsonPath("$.status").value(expectedError.getStatus().value()),
                            jsonPath("$.errorCode").exists(),
                            jsonPath("$.errorCode").value(expectedError.getErrorCode()),
                            jsonPath("$.message").exists(),
                            jsonPath("$.message").value(expectedError.getMessage())
                    );
        }

        @Test
        @DisplayName("게시글좋아요 등록에 성공한다")
        void success() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(1L)
                    .when(boardsLikeService)
                    .register(any(), anyLong());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL, BOARD_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isOk()
                    );
        }
    }

    @Nested
    @DisplayName("내가 쓴 글 리스트조회 API [GET /api/boards/my/{language}]")
    class getMyBoardsList {
        private static final String BASE_URL = "/api/boards/my/{language}";
        private static final Language LANGUAGE_KOR = Language.kor;
        private static final String SORT_CONDITION = "최신순";
        private static final String INVALID_SORT_CONDITION = "조회순";

        @Test
        @DisplayName("Authorization Header에 AccessToken이 없으면 게시글좋아요 취소에 실패한다")
        void withoutAccessToken() throws Exception {
            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL, LANGUAGE_KOR)
                    .param("sort", SORT_CONDITION);

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
        @DisplayName("존재하지 않는 정렬 유형이라면 게시글 리스트 조회에 실패한다")
        void throwNotFoundSearchCondition() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doThrow(BaseException.type(BoardsErrorCode.SORT_CONDITION_NOT_FOUND))
                    .when(boardsListService)
                    .getMyBoardsList(any(), any(), any());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL, LANGUAGE_KOR)
                    .param("sort", INVALID_SORT_CONDITION)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);


            // then
            final BoardsErrorCode expectedError = BoardsErrorCode.SORT_CONDITION_NOT_FOUND;
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
        @DisplayName("정렬 유형과 검색 조건에 따른 게시글 리스트 조회에 성공한다")
        void success() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(createBoardsListResponseDto())
                    .when(boardsListService)
                    .getMyBoardsList(any(), any(), any());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL, LANGUAGE_KOR)
                    .param("sort", SORT_CONDITION)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("게시글좋아요 취소 API [DELETE /api/boards/likes/{boardsId}]")
    class cancel {
        private static final String BASE_URL = "/api/boards/likes/{boardsId}";
        private static final Long BOARD_ID = 1L;

        @Test
        @DisplayName("Authorization Header에 AccessToken이 없으면 게시글좋아요 취소에 실패한다")
        void withoutAccessToken() throws Exception {
            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .delete(BASE_URL, BOARD_ID);

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
        @DisplayName("좋아요를 누르지 않은 게시글의 좋아요는 취소할 수 없다")
        void throwExceptionByBoardLikeNotFound() throws Exception {
            // given
            given(jwtProvider.validateToken(anyString())).willReturn(true);
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doThrow(BaseException.type(BoardsErrorCode.BOARD_LIKE_NOT_FOUND))
                    .when(boardsLikeService)
                    .cancel(any(), anyLong());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .delete(BASE_URL, BOARD_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            final BoardsErrorCode expectedError = BoardsErrorCode.BOARD_LIKE_NOT_FOUND;
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
        @DisplayName("게시글좋아요 취소에 성공한다")
        void success() throws Exception {
            // given
            given(jwtProvider.validateToken(anyString())).willReturn(true);
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doNothing()
                    .when(boardsLikeService)
                    .cancel(any(), anyLong());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .delete(BASE_URL, BOARD_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isOk()
                    );
        }
    }

    @Nested
    @DisplayName("게시글 좋아요 여부 확인 API [GET /api/boards/likes/{boardsId}]")
    class checkLike {
        private static final String BASE_URL = "/api/boards/likes/{boardsId}";
        private static final Long BOARDS_ID = 1L;

        @Test
        @DisplayName("게시글 좋아요 여부 확인에 성공한다")
        void success() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(true)
                    .when(boardsLikeService)
                    .checkLike(any(), anyLong());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL, BOARDS_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + ACCESS_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpectAll(status().isOk());
        }
    }

    private BoardsUpdateRequestDto createBoardsUpdateRequestDto(Language language) {
        BoardsUpdateRequestDto requestDto = null;
        switch (language) {
            case kor -> requestDto =new BoardsUpdateRequestDto(BOARDS_02.getBoardsTitleKor(), BOARDS_02.getBoardsContentKor(), Language.kor);
            case viet -> requestDto = new BoardsUpdateRequestDto(BOARDS_02.getBoardsTitleViet(), BOARDS_02.getBoardsContentViet(), Language.viet);
            case ph -> requestDto = new BoardsUpdateRequestDto(BOARDS_02.getBoardsTitlePh(), BOARDS_02.getBoardsContentPh(), Language.ph);
        }
        return requestDto;
    }

    private BoardsRequestDto createBoardsRequestDto(Language language) {
        BoardsRequestDto requestDto = null;
        switch (language) {
            case kor -> requestDto =new BoardsRequestDto(1L, BOARDS_02.getBoardsTitleKor(), BOARDS_02.getBoardsContentKor(), Language.kor);
            case viet -> requestDto = new BoardsRequestDto(1L, BOARDS_02.getBoardsTitleViet(), BOARDS_02.getBoardsContentViet(), Language.viet);
            case ph -> requestDto = new BoardsRequestDto(1L, BOARDS_02.getBoardsTitlePh(), BOARDS_02.getBoardsContentPh(), Language.ph);
        }
        return requestDto;
    }



    private BoardsResponseDto getBoardsResponseDto() {
        List<String> keywordList = new ArrayList<>();
        keywordList.add("레포트 키워드1");
        return new BoardsResponseDto(1L, BOARDS_01.getBoardsTitleKor(), BOARDS_01.getBoardsContentKor(),
                0, LocalDateTime.now(), "레포트요약본", keywordList, SUNKYOUNG.getParentsNickname());
    }

    private List<BoardsListDto> createBoardsListDto() {
        List<BoardsListDto> BoardsLists = new ArrayList<>();

        List<String> keywordList = new ArrayList<>();
        keywordList.add("레포트 키워드1");
        BoardsLists.add(new BoardsListDto(1L, BOARDS_01.getBoardsTitleKor(), BOARDS_01.getBoardsContentKor(),
                0, LocalDateTime.now().plusHours(1), "레포트요약본", keywordList, SUNKYOUNG.getParentsNickname()));
        BoardsLists.add(new BoardsListDto(2L, BOARDS_02.getBoardsTitleKor(), BOARDS_02.getBoardsContentKor(),
                0, LocalDateTime.now().plusHours(2), "레포트요약본", keywordList, SUNKYOUNG.getParentsNickname()));
        BoardsLists.add(new BoardsListDto(3L, BOARDS_03.getBoardsTitleKor(), BOARDS_03.getBoardsContentKor(),
                0, LocalDateTime.now().plusHours(3), "레포트요약본", keywordList, SUNKYOUNG.getParentsNickname()));
        return BoardsLists;
    }

    private CustomBoardsListResponseDto.CustomPageable createCustomPageable() {
        return new CustomBoardsListResponseDto.CustomPageable(1, 4, false, 4);
    }

    private CustomBoardsListResponseDto getCustomBoardsListResponseDto() {
        return new CustomBoardsListResponseDto<>(createCustomPageable(), createBoardsListDto());
    }

    private BoardsListResponseDto createBoardsListResponseDto() {
        return new BoardsListResponseDto(createBoardsListDto());
    }
}

