package com.ssafy.bridgetalkback.comment.controller;

import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.comments.dto.request.CommentsRequestDto;
import com.ssafy.bridgetalkback.comments.dto.request.CommentsUpdateRequestDto;
import com.ssafy.bridgetalkback.comments.dto.response.CommentsResponseDto;
import com.ssafy.bridgetalkback.comments.exception.CommentsErrorCode;
import com.ssafy.bridgetalkback.common.ControllerTest;
import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static com.ssafy.bridgetalkback.fixture.CommentsFixture.COMMENTS_01;
import static com.ssafy.bridgetalkback.fixture.TokenFixture.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Comments [Controller Layer] -> CommentsControllerTest 테스트")
public class CommentsControllerTest extends ControllerTest {

    @Nested
    @DisplayName("Comments 생성 API [POST /api/comments]")
    class createComments {
        private static final String BASE_URL = "/api/comments";

        @Test
        @DisplayName("Authorization_Header에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            // when
            final CommentsRequestDto commentsRequestDto = createCommentsRequestDto(Language.kor);
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(commentsRequestDto));

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
        @DisplayName("(한국어) 등록에 성공한다.")
        void successKor() throws Exception {
            //given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(createCommentsResponseDto(Language.kor))
                    .when(commentsService)
                    .createComments(any(), any());

            //when
            final CommentsRequestDto commentsRequestDto = createCommentsRequestDto(Language.kor);
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(commentsRequestDto));

            //then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isCreated());
        }

        @Test
        @DisplayName("(베트남어) 등록에 성공한다.")
        void successViet() throws Exception {
            //given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(createCommentsResponseDto(Language.viet))
                    .when(commentsService)
                    .createComments(any(), any());

            //when
            final CommentsRequestDto commentsRequestDto = createCommentsRequestDto(Language.viet);
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(commentsRequestDto));

            //then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isCreated());
        }

        @Test
        @DisplayName("(필리핀어) 등록에 성공한다.")
        void successPh() throws Exception {
            //given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(createCommentsResponseDto(Language.ph))
                    .when(commentsService)
                    .createComments(any(), any());

            //when
            final CommentsRequestDto commentsRequestDto = createCommentsRequestDto(Language.ph);
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(commentsRequestDto));

            //then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isCreated());
        }
    }

    @Nested
    @DisplayName("Comments 수정 API [PATCH /api/comments/{commentsId}]")
    class updateComments {
        private static final String BASE_URL = "/api/comments/{commentsId}";
        private static final Long COMMENTS_ID = 1L;

        @Test
        @DisplayName("Authorization_Header에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            // when
            final CommentsUpdateRequestDto commentsUpdateRequestDto = createCommentsUpdateRequestDto(Language.kor);
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .patch(BASE_URL, COMMENTS_ID)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(commentsUpdateRequestDto));

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
        @DisplayName("(한국어) 수정에 성공한다.")
        void successKor() throws Exception {
            //given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(createCommentsResponseDto(Language.kor))
                    .when(commentsService)
                    .updateComments(any(), any(), any());

            //when
            final CommentsUpdateRequestDto commentsUpdateRequestDto = createCommentsUpdateRequestDto(Language.kor);
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .patch(BASE_URL, COMMENTS_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(commentsUpdateRequestDto));

            //then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("(베트남어) 수정에 성공한다.")
        void successViet() throws Exception {
            //given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(createCommentsResponseDto(Language.viet))
                    .when(commentsService)
                    .updateComments(any(), any(), any());

            //when
            final CommentsUpdateRequestDto commentsUpdateRequestDto = createCommentsUpdateRequestDto(Language.viet);
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .patch(BASE_URL, COMMENTS_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(commentsUpdateRequestDto));

            //then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("(필리핀어) 수정에 성공한다.")
        void successPh() throws Exception {
            //given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(createCommentsResponseDto(Language.ph))
                    .when(commentsService)
                    .updateComments(any(), any(), any());

            //when
            final CommentsUpdateRequestDto commentsUpdateRequestDto = createCommentsUpdateRequestDto(Language.ph);
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .patch(BASE_URL, COMMENTS_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN)
                    .contentType(APPLICATION_JSON)
                    .content(convertObjectToJson(commentsUpdateRequestDto));

            //then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("Comments 삭제 API [DELETE /api/comments/{commentsId}]")
    class deleteComments {
        private static final String BASE_URL = "/api/comments/{commentsId}";
        private static final Long COMMENTS_ID = 1L;

        @Test
        @DisplayName("Authorization_Header에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .patch(BASE_URL, COMMENTS_ID);

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
        @DisplayName("삭제에 성공한다.")
        void success() throws Exception {
            //given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doNothing()
                    .when(commentsService)
                    .deleteComments(any(), any());

            //when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .delete(BASE_URL, COMMENTS_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);
            //then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("답글좋아요 등록 API [POST /api/comments/likes/{commentsId}]")
    class register {
        private static final String BASE_URL = "/api/comments/likes/{commentsId}";
        private static final Long COMMENT_ID = 1L;

        @Test
        @DisplayName("Authorization Header에 AccessToken이 없으면 게시글좋아요 등록에 실패한다")
        void withoutAccessToken() throws Exception {
            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL, COMMENT_ID);

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
        @DisplayName("본인의 답글에는 좋아요를 누를 수 없다")
        void throwExceptionBySelfFollowNotAllowed() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doThrow(BaseException.type(CommentsErrorCode.SELF_COMMENT_LIKE_NOT_ALLOWED))
                    .when(commentsLikeService)
                    .register(any(), anyLong());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL, COMMENT_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            final CommentsErrorCode expectedError = CommentsErrorCode.SELF_COMMENT_LIKE_NOT_ALLOWED;
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
        @DisplayName("한 답글에 두 번 이상 좋아요를 누를 수 없다")
        void throwExceptionByAlreadyBoardLike() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doThrow(BaseException.type(CommentsErrorCode.ALREADY_COMMENT_LIKE))
                    .when(commentsLikeService)
                    .register(any(), anyLong());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL, COMMENT_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            final CommentsErrorCode expectedError = CommentsErrorCode.ALREADY_COMMENT_LIKE;
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
        @DisplayName("답글 좋아요 등록에 성공한다")
        void success() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(1L)
                    .when(commentsLikeService)
                    .register(any(), anyLong());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .post(BASE_URL, COMMENT_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isOk()
                    );
        }
    }

    private CommentsUpdateRequestDto createCommentsUpdateRequestDto(Language language) {
        CommentsUpdateRequestDto requestDto = null;
        switch (language) {
            case kor -> requestDto = new CommentsUpdateRequestDto(COMMENTS_01.getCommentsContentKor(), language);
            case viet -> requestDto = new CommentsUpdateRequestDto(COMMENTS_01.getCommentsContentViet(), language);
            case ph -> requestDto = new CommentsUpdateRequestDto(COMMENTS_01.getCommentsContentPh(), language);
        }
        return requestDto;
    }

    @Nested
    @DisplayName("답글좋아요 취소 API [DELETE /api/comments/likes/{commentsId}]")
    class cancel {
        private static final String BASE_URL = "/api/comments/likes/{commentsId}";
        private static final Long COMMENT_ID = 1L;

        @Test
        @DisplayName("Authorization Header에 AccessToken이 없으면 게시글좋아요 취소에 실패한다")
        void withoutAccessToken() throws Exception {
            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .delete(BASE_URL, COMMENT_ID);

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
        @DisplayName("좋아요를 누르지 않은 답글의 좋아요는 취소할 수 없다")
        void throwExceptionByBoardLikeNotFound() throws Exception {
            // given
            given(jwtProvider.validateToken(anyString())).willReturn(true);
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doThrow(BaseException.type(CommentsErrorCode.COMMENT_LIKE_NOT_FOUND))
                    .when(commentsLikeService)
                    .cancel(any(), anyLong());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .delete(BASE_URL, COMMENT_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            final CommentsErrorCode expectedError = CommentsErrorCode.COMMENT_LIKE_NOT_FOUND;
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
        @DisplayName("답글 좋아요 취소에 성공한다")
        void success() throws Exception {
            // given
            given(jwtProvider.validateToken(anyString())).willReturn(true);
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doNothing()
                    .when(commentsLikeService)
                    .cancel(any(), anyLong());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .delete(BASE_URL, COMMENT_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpectAll(
                            status().isOk()
                    );
        }
    }

    @Nested
    @DisplayName("답글 좋아요 여부 확인 API [GET /api/comments/likes/{commentsId}]")
    class checkLike {
        private static final String BASE_URL = "/api/comments/likes/{commentsId}";
        private static final Long COMMENTS_ID = 1L;

        @Test
        @DisplayName("게시글 좋아요 여부 확인에 성공한다")
        void success() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(true)
                    .when(commentsLikeService)
                    .checkLike(any(), anyLong());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL, COMMENTS_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + ACCESS_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpectAll(status().isOk());
        }
    }


    private CommentsRequestDto createCommentsRequestDto(Language language) {
        CommentsRequestDto requestDto = null;
        switch (language) {
            case kor -> requestDto = new CommentsRequestDto(1L, COMMENTS_01.getCommentsContentKor(), language);
            case viet -> requestDto = new CommentsRequestDto(1L, COMMENTS_01.getCommentsContentViet(), language);
            case ph -> requestDto = new CommentsRequestDto(1L, COMMENTS_01.getCommentsContentPh(), language);
        }
        return requestDto;
    }

    private CommentsResponseDto createCommentsResponseDto(Language language) {
        CommentsResponseDto requestDto = null;
        switch (language) {
            case kor -> requestDto = new CommentsResponseDto(1L, String.valueOf(UUID.randomUUID()), "닉네임", COMMENTS_01.getCommentsContentKor(), 1, LocalDateTime.now());
            case viet -> requestDto = new CommentsResponseDto(1L, String.valueOf(UUID.randomUUID()), "닉네임", COMMENTS_01.getCommentsContentViet(), 2, LocalDateTime.now());
            case ph -> requestDto = new CommentsResponseDto(1L, String.valueOf(UUID.randomUUID()), "닉네임", COMMENTS_01.getCommentsContentPh(), 3, LocalDateTime.now());
        }
        return requestDto;
    }
}
