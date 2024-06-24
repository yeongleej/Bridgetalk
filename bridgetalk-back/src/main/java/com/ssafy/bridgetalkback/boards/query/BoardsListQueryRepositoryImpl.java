package com.ssafy.bridgetalkback.boards.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.bridgetalkback.boards.domain.BoardsSearchType;
import com.ssafy.bridgetalkback.boards.dto.response.BoardsListResponseDto;
import com.ssafy.bridgetalkback.boards.dto.response.CustomBoardsListResponseDto;
import com.ssafy.bridgetalkback.boards.query.dto.BoardsListDto;
import com.ssafy.bridgetalkback.boards.query.dto.QBoardsListDto;
import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.querydsl.core.types.ExpressionUtils.count;
import static com.ssafy.bridgetalkback.boards.domain.QBoards.boards;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardsListQueryRepositoryImpl implements BoardsListQueryRepository {
    private final JPAQueryFactory query;

    @Override
    public CustomBoardsListResponseDto<BoardsListDto> getBoardsListOrderByTime(int page, BoardsSearchType boardSearchType,
                                                                               String searchWord, Language language) {
        Pageable pageable = PageRequest.of(page, 4);
        List<BoardsListDto> boardLists = query
                .selectDistinct(createQBoardsListDto(language))
                .from(boards)
                .where(searchLanguage(boardSearchType, searchWord, language))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(boards.createdAt.desc())
                .fetch();

        JPAQuery<Long> countQuery = query
                .select(count(boards.boardsId))
                .from(boards)
                .where(searchLanguage(boardSearchType, searchWord, language));

        return new CustomBoardsListResponseDto<>(PageableExecutionUtils.getPage(boardLists, pageable, countQuery::fetchOne));
    }

    @Override
    public CustomBoardsListResponseDto<BoardsListDto> getBoardsListOrderByLikes(int page, BoardsSearchType boardSearchType,
                                                                                String searchWord, Language language) {
        Pageable pageable = PageRequest.of(page, 4);
        List<BoardsListDto> boardLists = query
                .selectDistinct(createQBoardsListDto(language))
                .from(boards)
                .where(searchLanguage(boardSearchType, searchWord, language))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(boards.likes.desc())
                .fetch();

        JPAQuery<Long> countQuery = query
                .select(count(boards.boardsId))
                .from(boards)
                .where(searchLanguage(boardSearchType, searchWord, language));

        return new CustomBoardsListResponseDto<>(PageableExecutionUtils.getPage(boardLists, pageable, countQuery::fetchOne));
    }

    @Override
    public BoardsListResponseDto getMyBoardsListOrderByTime(Parents parents, Language language) {
        List<BoardsListDto> boardLists = query
                .selectDistinct(createQBoardsListDto(language))
                .from(boards)
                .where(boards.parents.eq(parents))
                .orderBy(boards.createdAt.desc())
                .fetch();

        return new BoardsListResponseDto(boardLists);
    }

    @Override
    public BoardsListResponseDto getMyBoardsListOrderByLikes(Parents parents, Language language) {
        List<BoardsListDto> boardLists = query
                .selectDistinct(createQBoardsListDto(language))
                .from(boards)
                .where(boards.parents.eq(parents))
                .orderBy(boards.likes.desc())
                .fetch();

        return new BoardsListResponseDto(boardLists);
    }

    private BooleanExpression searchLanguage(BoardsSearchType boardSearchType, String searchWord, Language language) {
        BooleanExpression booleanExpression = null;
        switch (language) {
            case kor -> booleanExpression = searchKor(boardSearchType, searchWord);
            case viet -> booleanExpression = searchViet(boardSearchType, searchWord);
            case ph -> booleanExpression = searchPh(boardSearchType, searchWord);
        }
        return booleanExpression;
    }

    private BooleanExpression searchKor(BoardsSearchType boardSearchType, String searchWord) {
        log.info(" { BoardsListQueryRepositoryImpl } : searchKor - "+boardSearchType);
        log.info(" { BoardsListQueryRepositoryImpl } : searchWord - "+searchWord);
        if (searchWord == null || searchWord.isEmpty()) {
            return null;
        } else {
            switch (boardSearchType) {
                case TITLE -> {
                    return boards.boardsTitleKor.contains(searchWord);
                }
                case CONTENT_AND_REPORTS_SUMMARY -> {
                    return boards.boardsContentKor.contains(searchWord).or(boards.reports.reportsSummaryKor.contains(searchWord));
                }
                case WRITER -> {
                    return boards.parents.parentsNickname.contains(searchWord);
                }
                case REPORTS_KEYWORD -> {
                    return boards.reports.reportsKeywordsKor.contains(searchWord);
                }
                case TITLE_AND_CONTENT_AND_REPORTS -> {
                    return boards.boardsTitleKor.contains(searchWord).or(boards.boardsContentKor.contains(searchWord)
                            .or(boards.reports.reportsSummaryKor.contains(searchWord)).or(boards.reports.reportsKeywordsKor.contains(searchWord)));
                }
                default -> {
                    return null;
                }
            }
        }
    }

    private BooleanExpression searchViet(BoardsSearchType boardSearchType, String searchWord) {
        log.info(" { BoardsListQueryRepositoryImpl } : searchViet - "+boardSearchType);
        log.info(" { BoardsListQueryRepositoryImpl } : searchWord - "+searchWord);
        if (searchWord == null || searchWord.isEmpty()) {
            return null;
        } else {
            switch (boardSearchType) {
                case TITLE -> {
                    return boards.boardsTitleViet.contains(searchWord);
                }
                case CONTENT_AND_REPORTS_SUMMARY -> {
                    return boards.boardsContentViet.contains(searchWord).or(boards.reports.reportsSummaryViet.contains(searchWord));
                }
                case WRITER -> {
                    return boards.parents.parentsNickname.contains(searchWord);
                }
                case REPORTS_KEYWORD -> {
                    return boards.reports.reportsKeywordsViet.contains(searchWord);
                }
                case TITLE_AND_CONTENT_AND_REPORTS -> {
                    return boards.boardsTitleViet.contains(searchWord).or(boards.boardsContentViet.contains(searchWord)
                            .or(boards.reports.reportsSummaryViet.contains(searchWord)).or(boards.reports.reportsKeywordsViet.contains(searchWord)));
                }
                default -> {
                    return null;
                }
            }
        }
    }

    private BooleanExpression searchPh(BoardsSearchType boardSearchType, String searchWord) {
        log.info(" { BoardsListQueryRepositoryImpl } : searchPh - "+boardSearchType);
        log.info(" { BoardsListQueryRepositoryImpl } : searchWord - "+searchWord);
        if (searchWord == null || searchWord.isEmpty()) {
            return null;
        } else {
            switch (boardSearchType) {
                case TITLE -> {
                    return boards.boardsTitlePh.contains(searchWord);
                }
                case CONTENT_AND_REPORTS_SUMMARY -> {
                    return boards.boardsContentPh.contains(searchWord).or(boards.reports.reportsSummaryPh.contains(searchWord));
                }
                case WRITER -> {
                    return boards.parents.parentsNickname.contains(searchWord);
                }
                case REPORTS_KEYWORD -> {
                    return boards.reports.reportsKeywordsPh.contains(searchWord);
                }
                case TITLE_AND_CONTENT_AND_REPORTS -> {
                    return boards.boardsTitlePh.contains(searchWord).or(boards.boardsContentPh.contains(searchWord)
                            .or(boards.reports.reportsSummaryPh.contains(searchWord)).or(boards.reports.reportsKeywordsPh.contains(searchWord)));
                }
                default -> {
                    return null;
                }
            }
        }
    }

    private QBoardsListDto createQBoardsListDto(Language language) {
        QBoardsListDto boardsListDto = null;

        switch (language) {
            case kor -> boardsListDto = new QBoardsListDto(boards.boardsId, boards.boardsTitleKor,
                    boards.boardsContentKor, boards.likes, boards.createdAt, boards.reports.reportsSummaryKor,
                    boards.reports.reportsKeywordsKor, boards.parents.parentsNickname);
            case viet -> boardsListDto = new QBoardsListDto(boards.boardsId, boards.boardsTitleViet,
                    boards.boardsContentViet, boards.likes, boards.createdAt, boards.reports.reportsSummaryViet,
                    boards.reports.reportsKeywordsViet, boards.parents.parentsNickname);
            case ph -> boardsListDto = new QBoardsListDto(boards.boardsId, boards.boardsTitlePh,
                    boards.boardsContentPh, boards.likes, boards.createdAt, boards.reports.reportsSummaryPh,
                    boards.reports.reportsKeywordsPh, boards.parents.parentsNickname);
        }
        return boardsListDto;
    }
}
