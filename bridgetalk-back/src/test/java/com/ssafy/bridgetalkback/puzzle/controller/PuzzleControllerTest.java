package com.ssafy.bridgetalkback.puzzle.controller;

import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.common.ControllerTest;
import com.ssafy.bridgetalkback.puzzle.dto.request.PuzzleRequestDto;
import com.ssafy.bridgetalkback.puzzle.dto.response.PuzzleListResponseDto;
import com.ssafy.bridgetalkback.puzzle.dto.response.PuzzleResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;
import static com.ssafy.bridgetalkback.fixture.PuzzleFixture.*;
import static com.ssafy.bridgetalkback.fixture.TokenFixture.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Puzzle [Service Layer] -> PuzzleController 테스트")
public class PuzzleControllerTest extends ControllerTest {
    @Nested
    @DisplayName("퍼즐 등록 API [POST /api/puzzle]")
    class createPuzzle {
        private static final String BASE_URL = "/api/puzzle";

        @Test
        @DisplayName("Authorization_Header에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            // when
            final PuzzleRequestDto request = createPuzzleRequestDto();
            MockMultipartFile puzzleFile = new MockMultipartFile("puzzleFile", null,
                    "multipart/form-data", new byte[]{});
            MockMultipartFile mockRequest = new MockMultipartFile("request", null,
                    "application/json", objectMapper.writeValueAsString(request).getBytes(StandardCharsets.UTF_8));
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .multipart(BASE_URL)
                    .file(puzzleFile)
                    .file(mockRequest)
                    .accept(APPLICATION_JSON);

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
        @DisplayName("퍼즐 등록에 성공한다")
        void success() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            final PuzzleRequestDto request = createPuzzleRequestDto();
            doReturn(1L)
                    .when(puzzleService)
                    .createPuzzle(any(), anyString());

            // when
            MockMultipartFile puzzleFile = new MockMultipartFile("puzzleFile", null,
                    "multipart/form-data", new byte[]{});
            MockMultipartFile mockRequest = new MockMultipartFile("request", null,
                    "application/json", objectMapper.writeValueAsString(request).getBytes(StandardCharsets.UTF_8));
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .multipart(BASE_URL)
                    .file(puzzleFile)
                    .file(mockRequest)
                    .accept(APPLICATION_JSON)
                    .header(AUTHORIZATION, BEARER_TOKEN + ACCESS_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());

        }
    }

    @Nested
    @DisplayName("퍼즐 상세 조회 API [GET /api/puzzle/{puzzleId}]")
    class puzzleDetail {
        private static final String BASE_URL = "/api/puzzle/{puzzleId}";
        private static final Long PUZZLE_ID = 1L;

        @Test
        @DisplayName("Authorization_Header에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL, PUZZLE_ID);

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
        @DisplayName("퍼즐 상세 조회에 성공한다")
        void success() throws Exception {
            // given
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(createPuzzleResponseDto())
                    .when(puzzleService)
                    .puzzleDetail(anyLong());

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL, PUZZLE_ID)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());

        }
    }

    @Nested
    @DisplayName("퍼즐 리스트 조회 API [GET /api/puzzle]")
    class puzzleList {
        private static final String BASE_URL = "/api/puzzle/list";

        @Test
        @DisplayName("Authorization_Header에 RefreshToken이 없으면 예외가 발생한다")
        void throwExceptionByInvalidPermission() throws Exception {
            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL+"/viet");

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
        @DisplayName("퍼즐 리스트 조회에 성공한다")
        void success() throws Exception {
            // given
            String nation = "viet";
            given(jwtProvider.getId(anyString())).willReturn(String.valueOf(UUID.randomUUID()));
            doReturn(createPuzzleListResponseDto())
                    .when(puzzleService)
                    .puzzleList(nation);

            // when
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                    .get(BASE_URL+"/"+nation)
                    .header(AUTHORIZATION, BEARER_TOKEN + REFRESH_TOKEN);

            // then
            mockMvc.perform(requestBuilder)
                    .andExpect(status().isOk());

        }
    }

    private PuzzleRequestDto createPuzzleRequestDto() {
        return new PuzzleRequestDto(PUZZLE_01.getPuzzleNation(), PUZZLE_01.getPuzzleLandmarkName(), PUZZLE_01.getPuzzleLandmarkContent());
    }

    private PuzzleResponseDto createPuzzleResponseDto() {
        return new PuzzleResponseDto(1L, PUZZLE_01.getPuzzleNation(), PUZZLE_01.getPuzzleImageUrl(),
                PUZZLE_01.getPuzzleLandmarkName(), PUZZLE_01.getPuzzleLandmarkContent());
    }

    private PuzzleListResponseDto createPuzzleListResponseDto() {
        List<PuzzleResponseDto> puzzleList = new ArrayList<>();
        puzzleList.add(new PuzzleResponseDto(1L, PUZZLE_01.getPuzzleNation(), PUZZLE_01.getPuzzleImageUrl(),
                PUZZLE_01.getPuzzleLandmarkName(), PUZZLE_01.getPuzzleLandmarkContent()));
        puzzleList.add(new PuzzleResponseDto(2L, PUZZLE_02.getPuzzleNation(), PUZZLE_02.getPuzzleImageUrl(),
                PUZZLE_02.getPuzzleLandmarkName(), PUZZLE_02.getPuzzleLandmarkContent()));
        puzzleList.add(new PuzzleResponseDto(3L, PUZZLE_03.getPuzzleNation(), PUZZLE_03.getPuzzleImageUrl(),
                PUZZLE_03.getPuzzleLandmarkName(), PUZZLE_03.getPuzzleLandmarkContent()));
        return new PuzzleListResponseDto(puzzleList);
    }
}
