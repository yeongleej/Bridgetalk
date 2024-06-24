package com.ssafy.bridgetalkback.boards.dto.response;

import com.ssafy.bridgetalkback.boards.domain.Boards;
import com.ssafy.bridgetalkback.global.Language;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record BoardsResponseDto(
        Long boardsId,
        String boardsTitle,
        String boardsContent,
        int likes,
        LocalDateTime createdAt,
        String reportsSummary,
        List<String> reportsKeywords,
        String parentsNickname
) {
    public static BoardsResponseDto fromBoards(Boards boards, Language language) {
        return BoardsResponseDto.builder()
                .boardsId(boards.getBoardsId())
                .boardsTitle(selectBoardsTitle(boards, language))
                .boardsContent(selectBoardsContent(boards, language))
                .likes(boards.getLikes())
                .createdAt(boards.getCreatedAt())
                .reportsSummary(selectReportsSummary(boards, language))
                .reportsKeywords(selectReportsKeywords(boards, language))
                .parentsNickname(boards.getParents().getParentsNickname())
                .build();
    }

    private static String selectBoardsTitle(Boards boards, Language language) {
        String boardsTitle = null;
        switch (language) {
            case kor -> boardsTitle = boards.getBoardsTitleKor();
            case viet -> boardsTitle = boards.getBoardsTitleViet();
            case ph -> boardsTitle = boards.getBoardsTitlePh();
        }
        return boardsTitle;
    }

    private static String selectBoardsContent(Boards boards, Language language) {
        String boardsContent = null;
        switch (language) {
            case kor -> boardsContent = boards.getBoardsContentKor();
            case viet -> boardsContent = boards.getBoardsContentViet();
            case ph -> boardsContent = boards.getBoardsContentPh();
        }
        return boardsContent;
    }

    private static String selectReportsSummary(Boards boards, Language language) {
        String reportsSummary = null;
        switch (language) {
            case kor -> reportsSummary = boards.getReports().getReportsSummaryKor();
            case viet -> reportsSummary = boards.getReports().getReportsSummaryViet();
            case ph -> reportsSummary = boards.getReports().getReportsSummaryPh();
        }
        return reportsSummary;
    }

    private static List<String> selectReportsKeywords(Boards boards, Language language) {
        List<String> keywordsList = null;
        switch (language) {
            case kor -> keywordsList = boards.getReports().getReportsKeywordsKor();
            case viet -> keywordsList = boards.getReports().getReportsKeywordsViet();
            case ph -> keywordsList = boards.getReports().getReportsKeywordsPh();
        }
        return keywordsList;
    }
}
