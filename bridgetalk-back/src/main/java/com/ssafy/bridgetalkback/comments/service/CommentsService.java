package com.ssafy.bridgetalkback.comments.service;

import com.ssafy.bridgetalkback.boards.domain.Boards;
import com.ssafy.bridgetalkback.boards.service.BoardsFindService;
import com.ssafy.bridgetalkback.chatgpt.config.ChatGptRequestCode;
import com.ssafy.bridgetalkback.chatgpt.service.ChatGptService;
import com.ssafy.bridgetalkback.comments.domain.Comments;
import com.ssafy.bridgetalkback.comments.dto.request.CommentsRequestDto;
import com.ssafy.bridgetalkback.comments.dto.request.CommentsUpdateRequestDto;
import com.ssafy.bridgetalkback.comments.dto.response.CommentsResponseDto;
import com.ssafy.bridgetalkback.comments.exception.CommentsErrorCode;
import com.ssafy.bridgetalkback.comments.repository.CommentsRepository;
import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.parents.service.ParentsFindService;
import com.ssafy.bridgetalkback.translation.service.TranslationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentsService {

    private final ParentsFindService parentsFindService;
    private final BoardsFindService boardsFindService;
    private final CommentsFindService commentsFindService;
    private final CommentsRepository commentsRepository;
    private final TranslationService translationService;
    private final ChatGptService chatGptService;

    public CommentsResponseDto createComments(UUID uuid, CommentsRequestDto commentsRequestDto) {
        log.info("{ CommentsService } : comments 생성");
        Parents parents = parentsFindService.findParentsByUuidAndIsDeleted(uuid);
        Boards boards = boardsFindService.findByBoardsIdAndIsDeleted(commentsRequestDto.boardsId());
        HashMap<String, String> translate = translateComments(commentsRequestDto.commentsContent(), commentsRequestDto.language());
        Comments comments = Comments.createComments(parents, boards, translate.get("commentsContentKor"), translate.get("commentsContentViet"),
                translate.get("commentsContentPh"));
        commentsRepository.save(comments);
        log.info("{ CommentsService } : comments 생성 성공");
        return CommentsResponseDto.fromComments(comments, commentsRequestDto.language());
    }

    public CommentsResponseDto updateComments(UUID uuid, Long commentsId, CommentsUpdateRequestDto commentsUpdateRequestDto) {
        log.info("{ CommentsService } : comments 수정");
        Parents parents = parentsFindService.findParentsByUuidAndIsDeleted(uuid);
        Comments comments = commentsFindService.findByCommentsIdAndIsDeleted(commentsId);
        if (!parents.getUuid().equals(comments.getParents().getUuid()))
            throw BaseException.type(CommentsErrorCode.INVALID_USER);
        HashMap<String, String> translate = translateComments(commentsUpdateRequestDto.commentsContent(), commentsUpdateRequestDto.language());
        comments.updateComments(translate.get("commentsContentKor"), translate.get("commentsContentViet"), translate.get("commentsContentPh"));
        log.info("{ CommentsService } : comments 수정 성공");
        return CommentsResponseDto.fromComments(comments, commentsUpdateRequestDto.language());
    }

    public void deleteComments(UUID uuid, Long commentsId) {
        log.info("{ CommentsService } : comments 삭제");
        Parents parents = parentsFindService.findParentsByUuidAndIsDeleted(uuid);
        Comments comments = commentsFindService.findByCommentsIdAndIsDeleted(commentsId);
        if (!parents.getUuid().equals(comments.getParents().getUuid()))
            throw BaseException.type(CommentsErrorCode.INVALID_USER);
        comments.updateIsDeleted();
        log.info("{ CommentsService } : comments 삭제 성공");
    }

    private HashMap<String, String> translateComments(String commentsContent, Language language) {
        HashMap<String, String> translate = new HashMap<>();
        String commentsContentKor = "";
        String commentsContentViet = "";
        String commentsContentPh = "";

        String commentsContentEng = "";
        if (language.equals(Language.kor)) {
            commentsContentKor = commentsContent;
            commentsContentEng = translationService.translation(commentsContent, "ko", "en");
            commentsContentViet = translationService.translation(commentsContentEng, "en", "vi");
            commentsContentPh = createTranslatePh(commentsContentEng);
            log.info(">> 답변 번역 성공 ko->vi : {}", commentsContentViet);
            log.info(">> 답변 번역 성공 ko->ph : {}", commentsContentPh);
        } else if (language.equals(Language.viet)) {
            commentsContentViet = commentsContent;
            commentsContentEng = translationService.translation(commentsContent, "vi", "en");
            commentsContentKor = translationService.translation(commentsContentEng, "en", "ko");
            commentsContentPh = createTranslatePh(commentsContentEng);
            log.info(">> 답변 번역 성공 vi->ko : {}", commentsContentKor);
            log.info(">> 답변 번역 성공 vi->ph : {}", commentsContentPh);
        } else if (language.equals(Language.ph)) {
            commentsContentPh = commentsContent;
            commentsContentEng =createTranslateEng(commentsContentPh);
            commentsContentKor = translationService.translation(commentsContentEng, "en", "ko");
            commentsContentViet = translationService.translation(commentsContentEng, "en", "vi");
            log.info(">> 답변 번역 성공 ph->ko : {}", commentsContentKor);
            log.info(">> 답변 번역 성공 ph->vi : {}", commentsContentViet);
        }
        translate.put("commentsContentKor", commentsContentKor);
        translate.put("commentsContentViet", commentsContentViet);
        translate.put("commentsContentPh", commentsContentPh);
        return translate;
    }

    private String createTranslatePh(String text) {
        log.info("{ BoardsService.createTranslate }");
        String transformedText = "";
        transformedText = chatGptService.createPrompt(text, ChatGptRequestCode.TRANSLATE_PH);
        log.info(">> transformedText : {}", transformedText);

        return transformedText;
    }

    private String createTranslateEng(String text) {
        log.info("{ BoardsService.createTranslate }");
        String transformedText = "";
        transformedText = chatGptService.createPrompt(text, ChatGptRequestCode.TRANSLATE_ENG);
        log.info(">> transformedText : {}", transformedText);

        return transformedText;
    }
}
