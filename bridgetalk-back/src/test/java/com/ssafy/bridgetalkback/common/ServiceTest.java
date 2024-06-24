package com.ssafy.bridgetalkback.common;

import com.ssafy.bridgetalkback.auth.repository.AuthCodeRedisRepository;
import com.ssafy.bridgetalkback.auth.repository.RefreshTokenRedisRepository;
import com.ssafy.bridgetalkback.boards.repository.BoardsLikeRepository;
import com.ssafy.bridgetalkback.boards.repository.BoardsRepository;
import com.ssafy.bridgetalkback.comments.repository.CommentsLikeRepository;
import com.ssafy.bridgetalkback.comments.repository.CommentsRepository;
import com.ssafy.bridgetalkback.kids.repository.KidsRepository;
import com.ssafy.bridgetalkback.letters.repository.LettersRepository;
import com.ssafy.bridgetalkback.notification.repository.NotificationRepository;
import com.ssafy.bridgetalkback.notification.repository.SseRepositoryImpl;
import com.ssafy.bridgetalkback.parentingInfo.repository.ParentingInfoBoardNumRepository;
import com.ssafy.bridgetalkback.parentingInfo.repository.ParentingInfoRepository;
import com.ssafy.bridgetalkback.parents.repository.ParentsRepository;
import com.ssafy.bridgetalkback.puzzle.repository.PuzzleRepository;
import com.ssafy.bridgetalkback.reports.repository.ReportsRepository;
import com.ssafy.bridgetalkback.slang.repository.SlangRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class ServiceTest {
    @Autowired
    protected ParentsRepository parentsRepository;

    @Autowired
    protected KidsRepository kidsRepository;

    @Autowired
    protected ReportsRepository reportsRepository;

    @Autowired
    protected RefreshTokenRedisRepository refreshTokenRedisRepository;

    @Autowired
    protected LettersRepository lettersRepository;

    @Autowired
    protected PuzzleRepository puzzleRepository;

    @Autowired
    protected SlangRepository slangRepository;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    protected ParentingInfoRepository parentingInfoRepository;

    @Autowired
    protected ParentingInfoBoardNumRepository boardNumRepository;

    @Autowired
    protected BoardsRepository boardsRepository;

    @Autowired
    protected NotificationRepository notificationRepository;

    @Autowired
    protected SseRepositoryImpl sseRepository;

    @Autowired
    protected CommentsRepository commentsRepository;

    @Autowired
    protected BoardsLikeRepository boardsLikeRepository;

    @Autowired
    protected AuthCodeRedisRepository authCodeRedisRepository;

    @Autowired
    protected CommentsLikeRepository commentsLikeRepository;

    @AfterEach
    void clearDatabase() {
        databaseCleaner.cleanUpDatabase();
    }

    public void flushAndClear() {
        databaseCleaner.flushAndClear();
    }
}