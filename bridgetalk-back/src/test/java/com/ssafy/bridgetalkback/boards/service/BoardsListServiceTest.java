package com.ssafy.bridgetalkback.boards.service;

import com.ssafy.bridgetalkback.boards.domain.Boards;
import com.ssafy.bridgetalkback.boards.dto.response.BoardsListResponseDto;
import com.ssafy.bridgetalkback.boards.dto.response.CustomBoardsListResponseDto;
import com.ssafy.bridgetalkback.boards.exception.BoardsErrorCode;
import com.ssafy.bridgetalkback.boards.query.dto.BoardsListDto;
import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.reports.domain.Reports;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

import static com.ssafy.bridgetalkback.fixture.BoardsFixture.*;
import static com.ssafy.bridgetalkback.fixture.KidsFixture.JIYEONG;
import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SUNKYOUNG;
import static com.ssafy.bridgetalkback.fixture.ReportsFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("BoardsList [Service Layer] -> BoardsListService 테스트")
public class BoardsListServiceTest extends ServiceTest {
    @Autowired
    private BoardsListService boardsListService;

    private Parents parents;
    private Kids kids;
    private final Reports[] reports = new Reports[12];
    private final Boards[] boards = new Boards[12];

    private static final int PAGE = 0;
    private static final int PAGE_SIZE = 4;
    private static final String SEARCH_TYPE = "제목";
    private static final String SEARCH_WORD_KOR = "제목";
    private static final String SEARCH_WORD_VIET = "tiêu";
    private static final String SEARCH_WORD_PH = "pamagat";
    private static final String SORT_CONDITION = "최신순";
    private static final String INVALID_SEARCH_TYPE = "댓글";
    private static final String INVALID_SORT_CONDITION = "조회순";

    @BeforeEach
    void setup() {
        parents = parentsRepository.save(SUNKYOUNG.toParents());
        kids = kidsRepository.save(JIYEONG.toKids(parents));
        reports[0] = reportsRepository.save(REPORTS_01.toReports(kids));
        reports[1] = reportsRepository.save(REPORTS_02.toReports(kids));
        reports[2] = reportsRepository.save(REPORTS_03.toReports(kids));
        reports[3] = reportsRepository.save(REPORTS_04.toReports(kids));
        reports[4] = reportsRepository.save(REPORTS_05.toReports(kids));
        reports[5] = reportsRepository.save(REPORTS_06.toReports(kids));
        reports[6] = reportsRepository.save(REPORTS_07.toReports(kids));
        reports[7] = reportsRepository.save(REPORTS_08.toReports(kids));
        reports[8] = reportsRepository.save(REPORTS_09.toReports(kids));
        reports[9] = reportsRepository.save(REPORTS_10.toReports(kids));
        reports[10] = reportsRepository.save(REPORTS_11.toReports(kids));
        reports[11] = reportsRepository.save(REPORTS_12.toReports(kids));
        boards[0] = boardsRepository.save(BOARDS_01.toBoards(reports[0], parents));
        boards[1] = boardsRepository.save(BOARDS_02.toBoards(reports[1], parents));
        boards[2] = boardsRepository.save(BOARDS_03.toBoards(reports[2], parents));
        boards[3] = boardsRepository.save(BOARDS_04.toBoards(reports[3], parents));
        boards[4] = boardsRepository.save(BOARDS_05.toBoards(reports[4], parents));
        boards[5] = boardsRepository.save(BOARDS_06.toBoards(reports[5], parents));
        boards[6] = boardsRepository.save(BOARDS_07.toBoards(reports[6], parents));
        boards[7] = boardsRepository.save(BOARDS_08.toBoards(reports[7], parents));
        boards[8] = boardsRepository.save(BOARDS_09.toBoards(reports[8], parents));
        boards[9] = boardsRepository.save(BOARDS_10.toBoards(reports[9], parents));
        boards[10] = boardsRepository.save(BOARDS_11.toBoards(reports[10], parents));
        boards[11] = boardsRepository.save(BOARDS_12.toBoards(reports[11], parents));
    }

    @Nested
    @DisplayName("게시글 리스트 조회")
    class customBoardsList {
        @Test
        @DisplayName("존재하지 않는 검색 조건이라면 게시글 리스트 조회에 실패한다")
        void throwNotFoundSearchType() {
            // when - then
            assertThatThrownBy(() -> boardsListService.getCustomBoardsList(PAGE, INVALID_SEARCH_TYPE, SEARCH_WORD_KOR, SORT_CONDITION, Language.kor))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(BoardsErrorCode.SEARCH_TYPE_NOT_FOUND.getMessage());
        }

        @Test
        @DisplayName("존재하지 않는 정렬 유형이라면 게시글 리스트 조회에 실패한다")
        void throwNotFoundSearchCondition() {
            // when - then
            assertThatThrownBy(() -> boardsListService.getCustomBoardsList(PAGE, SEARCH_TYPE, SEARCH_WORD_KOR, INVALID_SORT_CONDITION, Language.kor))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(BoardsErrorCode.SORT_CONDITION_NOT_FOUND.getMessage());
        }

        @Test
        @DisplayName("(한국어) 정렬 유형과 검색 조건에 따른 게시글 리스트 최신순 조회에 성공한다")
        void successKorean() {
            // when
            CustomBoardsListResponseDto<BoardsListDto> responseDto = boardsListService.getCustomBoardsList(PAGE, SEARCH_TYPE, SEARCH_WORD_KOR, SORT_CONDITION, Language.kor);

            // then
            assertThat(responseDto.boardsList().size()).isLessThanOrEqualTo(PAGE_SIZE);
            assertThat(responseDto.pageInfo().totalPages()).isLessThanOrEqualTo(PAGE_SIZE);

            assertAll(
                    () -> assertThat(responseDto.pageInfo().totalPages()).isEqualTo(3),
                    () -> assertThat(responseDto.pageInfo().totalElements()).isEqualTo(12),
                    () -> assertThat(responseDto.pageInfo().hasNext()).isTrue(),
                    () -> assertThat(responseDto.pageInfo().numberOfElements()).isEqualTo(PAGE_SIZE),
                    () -> assertThat(responseDto.boardsList().size()).isLessThanOrEqualTo(PAGE_SIZE),
                    () -> assertThat(responseDto.boardsList().get(0).boardId()).isEqualTo(boards[11].getBoardsId()),
                    () -> assertThat(responseDto.boardsList().get(0).boardsTitle()).isEqualTo(boards[11].getBoardsTitleKor()),
                    () -> assertThat(responseDto.boardsList().get(0).boardsContent()).isEqualTo(boards[11].getBoardsContentKor()),
                    () -> assertThat(responseDto.boardsList().get(0).likes()).isEqualTo(boards[11].getLikes()),
                    () -> assertThat(responseDto.boardsList().get(0).createdAt()).isNotNull(),
                    () -> assertThat(responseDto.boardsList().get(0).reportsSummary()).isEqualTo(reports[11].getReportsSummaryKor()),
                    () -> assertThat(responseDto.boardsList().get(0).reportsKeywords()).isEqualTo(reports[11].getReportsKeywordsKor()==null ? Collections.emptyList() : reports[11].getReportsKeywordsKor()),
                    () -> assertThat(responseDto.boardsList().get(0).parentsNickname()).isEqualTo(parents.getParentsNickname())
            );
        }

        @Test
        @DisplayName("(베트남어) 정렬 유형과 검색 조건에 따른 게시글 리스트 최신순 조회에 성공한다")
        void successViet() {
            // when
            CustomBoardsListResponseDto<BoardsListDto> responseDto = boardsListService.getCustomBoardsList(PAGE, SEARCH_TYPE, SEARCH_WORD_VIET, SORT_CONDITION, Language.viet);

            // then
            assertThat(responseDto.boardsList().size()).isLessThanOrEqualTo(PAGE_SIZE);
            assertThat(responseDto.pageInfo().totalPages()).isLessThanOrEqualTo(PAGE_SIZE);

            assertAll(
                    () -> assertThat(responseDto.pageInfo().totalPages()).isEqualTo(3),
                    () -> assertThat(responseDto.pageInfo().totalElements()).isEqualTo(12),
                    () -> assertThat(responseDto.pageInfo().hasNext()).isTrue(),
                    () -> assertThat(responseDto.pageInfo().numberOfElements()).isEqualTo(PAGE_SIZE),
                    () -> assertThat(responseDto.boardsList().size()).isLessThanOrEqualTo(PAGE_SIZE),
                    () -> assertThat(responseDto.boardsList().get(0).boardId()).isEqualTo(boards[11].getBoardsId()),
                    () -> assertThat(responseDto.boardsList().get(0).boardsTitle()).isEqualTo(boards[11].getBoardsTitleViet()),
                    () -> assertThat(responseDto.boardsList().get(0).boardsContent()).isEqualTo(boards[11].getBoardsContentViet()),
                    () -> assertThat(responseDto.boardsList().get(0).likes()).isEqualTo(boards[11].getLikes()),
                    () -> assertThat(responseDto.boardsList().get(0).createdAt()).isNotNull(),
                    () -> assertThat(responseDto.boardsList().get(0).reportsSummary()).isEqualTo(reports[11].getReportsSummaryViet()),
                    () -> assertThat(responseDto.boardsList().get(0).reportsKeywords()).isEqualTo(reports[11].getReportsKeywordsViet()==null ? Collections.emptyList() : reports[11].getReportsKeywordsViet()),
                    () -> assertThat(responseDto.boardsList().get(0).parentsNickname()).isEqualTo(parents.getParentsNickname())
            );
        }

        @Test
        @DisplayName("(필리핀어) 정렬 유형과 검색 조건에 따른 게시글 리스트 최신순 조회에 성공한다")
        void successPh() {
            // when
            CustomBoardsListResponseDto<BoardsListDto> responseDto = boardsListService.getCustomBoardsList(PAGE, SEARCH_TYPE, SEARCH_WORD_PH, SORT_CONDITION, Language.ph);

            // then
            assertThat(responseDto.boardsList().size()).isLessThanOrEqualTo(PAGE_SIZE);
            assertThat(responseDto.pageInfo().totalPages()).isLessThanOrEqualTo(PAGE_SIZE);

            assertAll(
                    () -> assertThat(responseDto.pageInfo().totalPages()).isEqualTo(3),
                    () -> assertThat(responseDto.pageInfo().totalElements()).isEqualTo(12),
                    () -> assertThat(responseDto.pageInfo().hasNext()).isTrue(),
                    () -> assertThat(responseDto.pageInfo().numberOfElements()).isEqualTo(PAGE_SIZE),
                    () -> assertThat(responseDto.boardsList().size()).isLessThanOrEqualTo(PAGE_SIZE),
                    () -> assertThat(responseDto.boardsList().get(0).boardId()).isEqualTo(boards[11].getBoardsId()),
                    () -> assertThat(responseDto.boardsList().get(0).boardsTitle()).isEqualTo(boards[11].getBoardsTitlePh()),
                    () -> assertThat(responseDto.boardsList().get(0).boardsContent()).isEqualTo(boards[11].getBoardsContentPh()),
                    () -> assertThat(responseDto.boardsList().get(0).likes()).isEqualTo(boards[11].getLikes()),
                    () -> assertThat(responseDto.boardsList().get(0).createdAt()).isNotNull(),
                    () -> assertThat(responseDto.boardsList().get(0).reportsSummary()).isEqualTo(reports[11].getReportsSummaryPh()),
                    () -> assertThat(responseDto.boardsList().get(0).reportsKeywords()).isEqualTo(reports[11].getReportsKeywordsPh()==null ? Collections.emptyList() : reports[11].getReportsKeywordsPh()),
                    () -> assertThat(responseDto.boardsList().get(0).parentsNickname()).isEqualTo(parents.getParentsNickname())
            );
        }
    }

    @Nested
    @DisplayName("내가 쓴 글 리스트 조회")
    class myBoardList {
        @Test
        @DisplayName("존재하지 않는 정렬 유형이라면 내가 쓴 글 리스트 조회에 실패한다")
        void throwNotFoundSearchCondition() {
            // when - then
            assertThatThrownBy(() -> boardsListService.getMyBoardsList(parents.getUuid(), INVALID_SORT_CONDITION, Language.kor))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(BoardsErrorCode.SORT_CONDITION_NOT_FOUND.getMessage());
        }

        @Test
        @DisplayName("(한국어) 정렬 유형과 검색 조건에 따른 내가 쓴 글 리스트 최신순 조회에 성공한다")
        void successKorean() {
            // when
            BoardsListResponseDto responseDto = boardsListService.getMyBoardsList(parents.getUuid(), SORT_CONDITION, Language.kor);

            assertAll(
                    () -> assertThat(responseDto.boardsList().size()).isEqualTo(12),
                    () -> assertThat(responseDto.boardsList().get(0).boardId()).isEqualTo(boards[11].getBoardsId()),
                    () -> assertThat(responseDto.boardsList().get(0).boardsTitle()).isEqualTo(boards[11].getBoardsTitleKor()),
                    () -> assertThat(responseDto.boardsList().get(0).boardsContent()).isEqualTo(boards[11].getBoardsContentKor()),
                    () -> assertThat(responseDto.boardsList().get(0).likes()).isEqualTo(boards[11].getLikes()),
                    () -> assertThat(responseDto.boardsList().get(0).createdAt()).isNotNull(),
                    () -> assertThat(responseDto.boardsList().get(0).reportsSummary()).isEqualTo(reports[11].getReportsSummaryKor()),
                    () -> assertThat(responseDto.boardsList().get(0).reportsKeywords()).isEqualTo(reports[11].getReportsKeywordsKor()==null ? Collections.emptyList() : reports[11].getReportsKeywordsKor()),
                    () -> assertThat(responseDto.boardsList().get(0).parentsNickname()).isEqualTo(parents.getParentsNickname())
            );
        }

        @Test
        @DisplayName("(베트남어) 정렬 유형과 검색 조건에 따른 내가 쓴 글 리스트 최신순 조회에 성공한다")
        void successViet() {
            // when
            BoardsListResponseDto responseDto = boardsListService.getMyBoardsList(parents.getUuid(), SORT_CONDITION, Language.viet);

            // then
            assertAll(
                    () -> assertThat(responseDto.boardsList().size()).isEqualTo(12),
                    () -> assertThat(responseDto.boardsList().get(0).boardId()).isEqualTo(boards[11].getBoardsId()),
                    () -> assertThat(responseDto.boardsList().get(0).boardsTitle()).isEqualTo(boards[11].getBoardsTitleViet()),
                    () -> assertThat(responseDto.boardsList().get(0).boardsContent()).isEqualTo(boards[11].getBoardsContentViet()),
                    () -> assertThat(responseDto.boardsList().get(0).likes()).isEqualTo(boards[11].getLikes()),
                    () -> assertThat(responseDto.boardsList().get(0).createdAt()).isNotNull(),
                    () -> assertThat(responseDto.boardsList().get(0).reportsSummary()).isEqualTo(reports[11].getReportsSummaryViet()),
                    () -> assertThat(responseDto.boardsList().get(0).reportsKeywords()).isEqualTo(reports[11].getReportsKeywordsViet()==null ? Collections.emptyList() : reports[11].getReportsKeywordsViet()),
                    () -> assertThat(responseDto.boardsList().get(0).parentsNickname()).isEqualTo(parents.getParentsNickname())
            );
        }

        @Test
        @DisplayName("(필리핀어) 정렬 유형과 검색 조건에 따른 내가 쓴 글 리스트 최신순 조회에 성공한다")
        void successPh() {
            // when
            BoardsListResponseDto responseDto = boardsListService.getMyBoardsList(parents.getUuid(), SORT_CONDITION, Language.ph);

            // then
            assertAll(
                    () -> assertThat(responseDto.boardsList().size()).isLessThanOrEqualTo(12),
                    () -> assertThat(responseDto.boardsList().get(0).boardId()).isEqualTo(boards[11].getBoardsId()),
                    () -> assertThat(responseDto.boardsList().get(0).boardsTitle()).isEqualTo(boards[11].getBoardsTitlePh()),
                    () -> assertThat(responseDto.boardsList().get(0).boardsContent()).isEqualTo(boards[11].getBoardsContentPh()),
                    () -> assertThat(responseDto.boardsList().get(0).likes()).isEqualTo(boards[11].getLikes()),
                    () -> assertThat(responseDto.boardsList().get(0).createdAt()).isNotNull(),
                    () -> assertThat(responseDto.boardsList().get(0).reportsSummary()).isEqualTo(reports[11].getReportsSummaryPh()),
                    () -> assertThat(responseDto.boardsList().get(0).reportsKeywords()).isEqualTo(reports[11].getReportsKeywordsPh()==null ? Collections.emptyList() : reports[11].getReportsKeywordsPh()),
                    () -> assertThat(responseDto.boardsList().get(0).parentsNickname()).isEqualTo(parents.getParentsNickname())
            );
        }
    }
}
