package com.ssafy.bridgetalkback.boards.service;

import com.ssafy.bridgetalkback.boards.domain.Boards;
import com.ssafy.bridgetalkback.boards.dto.request.BoardsRequestDto;
import com.ssafy.bridgetalkback.boards.dto.request.BoardsUpdateRequestDto;
import com.ssafy.bridgetalkback.boards.dto.response.BoardsResponseDto;
import com.ssafy.bridgetalkback.boards.exception.BoardsErrorCode;
import com.ssafy.bridgetalkback.boards.repository.BoardsRepository;
import com.ssafy.bridgetalkback.chatgpt.config.ChatGptRequestCode;
import com.ssafy.bridgetalkback.chatgpt.service.ChatGptService;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.parents.service.ParentsFindService;
import com.ssafy.bridgetalkback.reports.domain.Reports;
import com.ssafy.bridgetalkback.reports.service.ReportsFindService;
import com.ssafy.bridgetalkback.translation.service.TranslationService;
import com.ssafy.bridgetalkback.global.Language;
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
public class BoardsService {

    private final ParentsFindService parentsFindService;
    private final ReportsFindService reportsFindService;
    private final BoardsRepository boardsRepository;
    private final BoardsFindService boardsFindService;
    private final TranslationService translationService;
    private final ChatGptService chatGptService;


    public BoardsResponseDto createBoards(UUID uuid, BoardsRequestDto boardsRequestDto) {
        log.info("{ BoardsService } : boards 생성");
        Parents parents = parentsFindService.findParentsByUuidAndIsDeleted(uuid);
        Reports reports = reportsFindService.findByReportsIdAndIsDeleted(boardsRequestDto.reportsId());
        HashMap<String, String> translate = translateBoards(boardsRequestDto.boardsTitle(), boardsRequestDto.boardsContent(), boardsRequestDto.language());
        Boards boards = Boards.createBoards(reports, parents, translate.get("boardsTitleKor"), translate.get("boardsTitleViet"), translate.get("boardsTitlePh")
                , translate.get("boardsContentKor"), translate.get("boardsContentViet"), translate.get("boardsContentPh"));
        boardsRepository.save(boards);
        log.info("{ BoardsService } : boards 생성 성공");
        return BoardsResponseDto.fromBoards(boards, boardsRequestDto.language());
    }

    public BoardsResponseDto updateBoards(UUID uuid, Long boardsId, BoardsUpdateRequestDto boardsUpdateRequestDto) {
        log.info("{ BoardsService } : boards 수정");
        Parents parents = parentsFindService.findParentsByUuidAndIsDeleted(uuid);
        Boards boards = boardsFindService.findByBoardsIdAndIsDeleted(boardsId);
        if (!parents.getUuid().equals(boards.getParents().getUuid()))
            throw BaseException.type(BoardsErrorCode.USER_IS_NOT_BOARD_WRITER);
        HashMap<String, String> translate = translateBoards(boardsUpdateRequestDto.boardsTitle(), boardsUpdateRequestDto.boardsContent(), boardsUpdateRequestDto.language());
        boards.updateBoards(translate.get("boardsTitleKor"), translate.get("boardsTitleViet"), translate.get("boardsTitlePh")
                , translate.get("boardsContentKor"), translate.get("boardsContentViet"), translate.get("boardsContentPh"));
        log.info("{ BoardsService } : boards 수정 성공");
        return BoardsResponseDto.fromBoards(boards, boardsUpdateRequestDto.language());
    }

    public void deleteBoards(UUID uuid, Long boardsId) {
        log.info("{ BoardsService } : boards 삭제");
        Parents parents = parentsFindService.findParentsByUuidAndIsDeleted(uuid);
        Boards boards = boardsFindService.findByBoardsIdAndIsDeleted(boardsId);
        if (!parents.getUuid().equals(boards.getParents().getUuid()))
            throw BaseException.type(BoardsErrorCode.USER_IS_NOT_BOARD_WRITER);
        boards.updateIsDeleted();
        log.info("{ BoardsService } : boards 삭제 성공");
    }

    public HashMap<String, String> translateBoards(String boardsTitle, String boardsContent, Language language) {
        HashMap<String, String> translate = new HashMap<>(); // 한국어, 베트남어, 타갈로그어(필리핀어)
        String boardsTitleKor = "";
        String boardsTitleViet = "";
        String boardsTitlePh = "";
        String boardsContentKor = "";
        String boardsContentViet = "";
        String boardsContentPh = "";

        String boardsTitleEng = "";
        String boardsContentEng = "";
        if (language.equals(Language.kor)) {
            boardsTitleKor = boardsTitle;
            boardsContentKor = boardsContent;

            boardsTitleEng = translationService.translation(boardsTitle, "ko", "en");
            boardsTitleViet = translationService.translation(boardsTitleEng, "en", "vi");
            boardsTitlePh = createTranslatePh(boardsTitleEng);
            log.info(">> 게시글 제목 번역 성공 ko->vi : {}", boardsTitleViet);
            log.info(">> 게시글 제목 번역 성공 ko->ph : {}", boardsTitlePh);
            boardsContentEng = translationService.translation(boardsContent, "ko", "en");
            boardsContentViet = translationService.translation(boardsContentEng, "en", "vi");
            boardsContentPh = createTranslatePh(boardsContentEng);
            log.info(">> 게시글 내용 번역 성공 ko->vi : {}", boardsContentViet);
            log.info(">> 게시글 내용 번역 성공 ko->ph : {}", boardsContentPh);

        } else if (language.equals(Language.viet)) {
            boardsTitleViet = boardsTitle;
            boardsContentViet = boardsContent;

            boardsTitleEng = translationService.translation(boardsTitle, "vi", "en");
            boardsTitleKor = translationService.translation(boardsTitleEng, "en", "ko");
            boardsTitlePh = createTranslatePh(boardsTitleEng);
            log.info(">> 게시글 제목 번역 성공 vi->ko : {}", boardsTitleKor);
            log.info(">> 게시글 제목 번역 성공 vi->ph : {}", boardsTitlePh);
            boardsContentEng = translationService.translation(boardsContent, "vi", "en");
            boardsContentKor = translationService.translation(boardsContentEng, "en", "ko");
            boardsContentPh = createTranslatePh(boardsContentEng);
            log.info(">> 게시글 내용 번역 성공 vi->ko : {}", boardsContentKor);
            log.info(">> 게시글 내용 번역 성공 vi->ph : {}", boardsContentPh);

        } else if (language.equals(Language.ph)) {
            boardsTitlePh = boardsTitle;
            boardsContentPh = boardsContent;

            boardsTitleEng = createTranslateEng(boardsTitle);
            boardsTitleKor = translationService.translation(boardsTitleEng, "en", "ko");
            boardsTitleViet = translationService.translation(boardsTitleEng, "en", "vi");
            log.info(">> 게시글 제목 번역 성공 ph->ko : {}", boardsTitleKor);
            log.info(">> 게시글 제목 번역 성공 ph->vi : {}", boardsTitleViet);
            boardsContentEng = createTranslateEng(boardsContent);
            boardsContentKor = translationService.translation(boardsContentEng, "en", "ko");
            boardsContentViet = translationService.translation(boardsContentEng, "en", "vi");
            log.info(">> 게시글 내용 번역 성공 ph->ko : {}", boardsContentKor);
            log.info(">> 게시글 내용 번역 성공 ph->vi : {}", boardsContentViet);
        }
        translate.put("boardsTitleKor", boardsTitleKor);
        translate.put("boardsTitleViet", boardsTitleViet);
        translate.put("boardsTitlePh", boardsTitlePh);
        translate.put("boardsContentKor", boardsContentKor);
        translate.put("boardsContentViet", boardsContentViet);
        translate.put("boardsContentPh", boardsContentPh);
        return translate;
    }

    public BoardsResponseDto getBoardsDetail(Long boardsId, Language language) {
        log.info("{ BoardsService } : 게시글 상세조회 진입");
        Boards findBoards = boardsFindService.findByBoardsIdAndIsDeleted(boardsId);

        BoardsResponseDto responseDto = BoardsResponseDto.fromBoards(findBoards, language);
        log.info("{ BoardsService } : 게시글 상세조회 성공");
        return responseDto;
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
