package com.ssafy.bridgetalkback.fixture;

import com.ssafy.bridgetalkback.boards.domain.Boards;
import com.ssafy.bridgetalkback.comments.domain.Comments;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommentsFixture {
    COMMENTS_01("첫번째 답변", "Câu trả lời đầu tiên", "Unang sagot"),
    COMMENTS_02("두번째 답변", "Câu trả lời thứ hai", "Pangalawang sagot"),
    COMMENTS_03("세번째 답변", "Câu trả lời thứ ba", "Ikatlong sagot"),
    COMMENTS_04("네번째 답변", "Câu trả lời thứ tư", "Pang-apat na sagot"),
    COMMENTS_05("다섯번째 답변", "Câu trả lời thứ năm", "Pang-limang sagot"),
    COMMENTS_06("여섯번째 답변", "Câu trả lời thứ sáu", "Pang-anim na sagot"),
    COMMENTS_07("일곱번째 답변", "Câu trả lời thứ hai", "Pangalawang sagot"),
    COMMENTS_08("여덟번째 답변", "Câu trả lời thứ hai", "Pangalawang sagot"),
    COMMENTS_09("아홉번째 답변", "Câu trả lời thứ hai", "Pangalawang sagot"),
    COMMENTS_10("열번째 답변", "Câu trả lời thứ hai", "Pangalawang sagot"),
    COMMENTS_11("열한번째 답변", "Câu trả lời thứ hai", "Pangalawang sagot"),
    COMMENTS_12("열두번째 답변", "Câu trả lời thứ mười hai", "Pang-labindalawang sagot")
    ;

    private final String commentsContentKor;
    private final String commentsContentViet;
    private final String commentsContentPh;

    public Comments toComments(Parents parents, Boards boards) {
        return Comments.createComments(parents, boards, commentsContentKor, commentsContentViet, commentsContentPh);
    }
}
