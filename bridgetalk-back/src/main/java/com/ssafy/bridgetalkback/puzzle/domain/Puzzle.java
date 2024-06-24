package com.ssafy.bridgetalkback.puzzle.domain;

import com.ssafy.bridgetalkback.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="puzzle")
public class Puzzle extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long puzzleId;

    @Column(nullable = false, length = 30)
    private String puzzleNation;

    @Column(nullable = false, length = 500)
    private String puzzleImageUrl;

    @Column(nullable = false, length = 50)
    private String puzzleLandmarkName;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String puzzleLandmarkContent;

    private Puzzle(String puzzleNation, String puzzleImageUrl, String puzzleLandmarkName, String puzzleLandmarkContent) {
        this.puzzleNation = puzzleNation;
        this.puzzleImageUrl = puzzleImageUrl;
        this.puzzleLandmarkName = puzzleLandmarkName;
        this.puzzleLandmarkContent = puzzleLandmarkContent;
    }

    public static Puzzle createPuzzle(String puzzleNation, String puzzleImageUrl, String puzzleLandmarkName, String puzzleLandmarkContent) {
        return new Puzzle(puzzleNation, puzzleImageUrl, puzzleLandmarkName, puzzleLandmarkContent);
    }
}
