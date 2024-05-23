package com.ssafy.bridgetalkback.boards.domain;

import com.ssafy.bridgetalkback.comments.domain.Comments;
import com.ssafy.bridgetalkback.global.BaseEntity;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.reports.domain.Reports;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "boards")
public class Boards extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardsId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reports_id")
    private Reports reports;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parents_uuid")
    private Parents parents;

    @Column(nullable = false, length = 100)
    private String boardsTitleKor;

    @Column(nullable = false, length = 100)
    private String boardsTitleViet;

    @Column(nullable = false, length = 100)
    private String boardsTitlePh;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String boardsContentKor;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String boardsContentViet;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String boardsContentPh;

    @Column(nullable = false)
    private int likes;

    @OneToMany(mappedBy = "boards", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comments> commentsList = new ArrayList<>();


    private Boards(Reports reports, Parents parents, String boardsTitleKor, String boardsTitleViet, String boardsTitlePh,
                   String boardsContentKor, String boardsContentViet, String boardsContentPh) {
        this.reports = reports;
        this.parents = parents;
        this.boardsTitleKor = boardsTitleKor;
        this.boardsTitleViet = boardsTitleViet;
        this.boardsTitlePh = boardsTitlePh;
        this.boardsContentKor = boardsContentKor;
        this.boardsContentViet = boardsContentViet;
        this.boardsContentPh = boardsContentPh;
        this.likes = 0;
    }

    public static Boards createBoards(Reports reports, Parents parents, String boardsTitleKor, String boardsTitleViet, String boardsTitlePh,
                                      String boardsContentKor, String boardsContentViet, String boardsContentPh) {
        return new Boards(reports, parents, boardsTitleKor, boardsTitleViet, boardsTitlePh, boardsContentKor,
                boardsContentViet, boardsContentPh);
    }

    public void updateBoards(String boardsTitleKor, String boardsTitleViet, String boardsTitlePh, String boardsContentKor,
                             String boardsContentViet, String boardsContentPh) {
        this.boardsTitleKor = boardsTitleKor;
        this.boardsTitleViet = boardsTitleViet;
        this.boardsTitlePh = boardsTitlePh;
        this.boardsContentKor = boardsContentKor;
        this.boardsContentViet = boardsContentViet;
        this.boardsContentPh = boardsContentPh;
    }

    public void increaseLikes() {
        this.likes = this.likes + 1;
    }

    public void decreaseLikes() {
        this.likes = this.likes - 1;
    }
}
