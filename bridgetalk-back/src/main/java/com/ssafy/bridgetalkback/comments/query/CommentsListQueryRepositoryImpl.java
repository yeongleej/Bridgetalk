package com.ssafy.bridgetalkback.comments.query;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.bridgetalkback.boards.domain.Boards;
import com.ssafy.bridgetalkback.comments.dto.response.CommentsListResponseDto;
import com.ssafy.bridgetalkback.comments.dto.response.CustomCommentsListResponseDto;
import com.ssafy.bridgetalkback.comments.query.dto.CommentsListDto;
import com.ssafy.bridgetalkback.comments.query.dto.QCommentsListDto;
import com.ssafy.bridgetalkback.global.Language;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.querydsl.core.types.ExpressionUtils.count;
import static com.ssafy.bridgetalkback.comments.domain.QComments.comments;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentsListQueryRepositoryImpl implements CommentsListQueryRepository {
    private final JPAQueryFactory query;

    @Override
    public CustomCommentsListResponseDto<CommentsListDto> getCommentsListOrderByTime(Boards boards, int page, Language language) {
        Pageable pageable = PageRequest.of(page, 10);
        List<CommentsListDto> commentsList = query
                .selectDistinct(createQCommentsListDto(language))
                .from(comments)
                .where(comments.boards.eq(boards))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(comments.createdAt.desc())
                .fetch();

        JPAQuery<Long> countQuery = query
                .select(count(comments.commentsId))
                .from(comments);

        return new CustomCommentsListResponseDto<>(PageableExecutionUtils.getPage(commentsList, pageable, countQuery::fetchOne));
    }

    @Override
    public CustomCommentsListResponseDto<CommentsListDto> getCommentsListOrderByLikes(Boards boards, int page, Language language) {
        Pageable pageable = PageRequest.of(page, 10);
        List<CommentsListDto> commentsList = query
                .selectDistinct(createQCommentsListDto(language))
                .from(comments)
                .where(comments.boards.eq(boards))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(comments.likes.desc())
                .fetch();

        JPAQuery<Long> countQuery = query
                .select(count(comments.commentsId))
                .from(comments);

        return new CustomCommentsListResponseDto<>(PageableExecutionUtils.getPage(commentsList, pageable, countQuery::fetchOne));
    }

    @Override
    public CommentsListResponseDto getReportsCommentsListOrderByTime(Boards boards, Language language) {
        List<CommentsListDto> commentsList = query
                .selectDistinct(createQCommentsListDto(language))
                .from(comments)
                .where(comments.boards.eq(boards))
                .orderBy(comments.likes.desc())
                .fetch();

        return new CommentsListResponseDto(commentsList);
    }

    private QCommentsListDto createQCommentsListDto(Language language) {
        QCommentsListDto commentsListDto = null;

        switch (language) {
            case kor -> commentsListDto = new QCommentsListDto(comments.commentsId, comments.parents.parentsNickname,
                    comments.commentsContentKor, comments.likes, comments.createdAt);
            case viet -> commentsListDto = new QCommentsListDto(comments.commentsId, comments.parents.parentsNickname,
                    comments.commentsContentViet, comments.likes, comments.createdAt);
            case ph -> commentsListDto = new QCommentsListDto(comments.commentsId, comments.parents.parentsNickname,
                    comments.commentsContentPh, comments.likes, comments.createdAt);
        }
        return commentsListDto;
    }
}