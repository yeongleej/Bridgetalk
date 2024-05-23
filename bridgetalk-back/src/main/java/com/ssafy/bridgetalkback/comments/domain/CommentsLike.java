package com.ssafy.bridgetalkback.comments.domain;

import com.ssafy.bridgetalkback.boards.domain.Boards;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comments_like")
public class CommentsLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comments_like_id")
    private Long commentsLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parents_id")
    private Parents parents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comments_id")
    private Comments comments;

    private CommentsLike(Parents parents, Comments comments) {
        this.parents = parents;
        this.comments = comments;
    }
    public static CommentsLike registerCommentsLike(Parents parents, Comments comments){
        return new CommentsLike(parents, comments);
    }

}
