package com.ssafy.bridgetalkback.fixture;

import com.ssafy.bridgetalkback.boards.domain.Boards;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.reports.domain.Reports;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoardsFixture {
    BOARDS_01("제목이다1", "là tiêu đề.", "Ito ang pamagat", "Ito ang nilalaman", "내용이다1", "là nội dung."),
    BOARDS_02("제목이다2", "là tiêu đề.", "Ito ang pamagat", "Ito ang nilalaman", "내용이다2", "là nội dung."),
    BOARDS_03("제목이다3", "là tiêu đề.", "Ito ang pamagat", "Ito ang nilalaman", "내용이다3", "là nội dung."),
    BOARDS_04("제목이다4", "là tiêu đề.", "Ito ang pamagat", "Ito ang nilalaman", "내용이다4", "là nội dung."),
    BOARDS_05("제목이다5", "là tiêu đề.", "Ito ang pamagat", "Ito ang nilalaman", "내용이다5", "là nội dung."),
    BOARDS_06("제목이다6", "là tiêu đề.", "Ito ang pamagat", "Ito ang nilalaman", "내용이다6", "là nội dung."),
    BOARDS_07("제목이다7", "là tiêu đề.", "Ito ang pamagat", "Ito ang nilalaman", "내용이다7", "là nội dung."),
    BOARDS_08("제목이다8", "là tiêu đề.", "Ito ang pamagat", "Ito ang nilalaman", "내용이다8", "là nội dung."),
    BOARDS_09("제목이다9", "là tiêu đề.", "Ito ang pamagat", "Ito ang nilalaman", "내용이다9", "là nội dung."),
    BOARDS_10("제목이다10", "là tiêu đề.", "Ito ang pamagat", "Ito ang nilalaman", "내용이다10", "là nội dung."),
    BOARDS_11("제목이다11", "là tiêu đề.", "Ito ang pamagat", "Ito ang nilalaman", "내용이다11", "là nội dung."),
    BOARDS_12("제목이다12", "là tiêu đề.", "Ito ang pamagat", "Ito ang nilalaman", "내용이다12", "là nội dung.")
    ;

    private final String boardsTitleKor;
    private final String boardsTitleViet;
    private final String boardsTitlePh;
    private final String boardsContentKor;
    private final String boardsContentViet;
    private final String boardsContentPh;

    public Boards toBoards(Reports reports, Parents parents) {
        return Boards.createBoards(reports, parents, boardsTitleKor, boardsTitleViet, boardsTitlePh, boardsContentKor, boardsContentViet, boardsContentPh);
    }

}
