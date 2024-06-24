package com.ssafy.bridgetalkback.comments.domain;

import com.ssafy.bridgetalkback.boards.domain.Boards;
import com.ssafy.bridgetalkback.global.BaseEntity;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comments")
public class Comments extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parents_uuid")
    private Parents parents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boards_id")
    private Boards boards;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String commentsContentKor;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String commentsContentViet;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String commentsContentPh;

    @Column(nullable = false)
    private int likes;

    private Comments(Parents parents, Boards boards, String commentsContentKor, String commentsContentViet,
                     String commentsContentPh) {
        this.parents = parents;
        this.boards = boards;
        this.commentsContentKor = commentsContentKor;
        this.commentsContentViet = commentsContentViet;
        this.commentsContentPh = commentsContentPh;
        this.likes = 0;
    }

    public static Comments createComments(Parents parents, Boards boards, String commentsContentKor, String commentsContentViet,
                                          String commentsContentPh)  {
        return new Comments(parents, boards, commentsContentKor, commentsContentViet, commentsContentPh);
    }

    public void updateComments(String commentsContentKor, String commentsContentViet, String commentsContentPh) {
        this.commentsContentKor = commentsContentKor;
        this.commentsContentViet = commentsContentViet;
        this.commentsContentPh = commentsContentPh;
    }

    public void increaseLikes() {
        this.likes = this.likes + 1;
    }

    public void decreaseLikes() {
        this.likes = this.likes - 1;
    }
}
