package com.ssafy.bridgetalkback.boards.domain;

import com.ssafy.bridgetalkback.parents.domain.Parents;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "boards_like")
public class BoardsLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boards_like_id")
    private Long boardsLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parents_id")
    private Parents parents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boards_id")
    private Boards boards;

    private BoardsLike(Parents parents, Boards boards) {
        this.parents = parents;
        this.boards = boards;
    }

    public static BoardsLike registerBoardsLike(Parents parents, Boards boards) {
        return new BoardsLike(parents, boards);
    }
}
