package com.ssafy.bridgetalkback.letters.service;

import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.global.Language;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.kids.domain.Kids;
import com.ssafy.bridgetalkback.letters.domain.Letters;
import com.ssafy.bridgetalkback.letters.exception.LettersErrorCode;
import com.ssafy.bridgetalkback.parents.domain.Parents;
import com.ssafy.bridgetalkback.reports.domain.Reports;
import com.ssafy.bridgetalkback.translation.service.TranslationService;
import com.ssafy.bridgetalkback.tts.service.TtsService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;

import static com.ssafy.bridgetalkback.fixture.KidsFixture.JIYEONG;
import static com.ssafy.bridgetalkback.fixture.ParentsFixture.SOYOUNG;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Letters [Service Layer] -> LettersService 테스트")
public class LettersServiceTest extends ServiceTest {

    @Autowired
    private LettersService lettersService;
    @Autowired
    private TranslationService translationService;
    @MockBean
    private TtsService ttsService;
    private Parents parents;
    private Letters existLetters, deletedLetters;
    private Kids kids;
    private Reports reports1, reports2;

    @BeforeEach
    void setup() {
        parents = parentsRepository.save(SOYOUNG.toParents());
        kids = kidsRepository.save(JIYEONG.toKids(parents));
        reports1 = reportsRepository.save(Reports.createReports(kids, "속마음 원본"));
        reports2 = reportsRepository.save(Reports.createReports(kids, "속마음 원본2"));
        existLetters = lettersRepository.save(Letters.createLetters(parents, reports1, "편지원문", "편지번역본"));
        deletedLetters = lettersRepository.save(Letters.createLetters(parents, reports2, "편지원문2", "편지번역본2"));
    }

    @Test
    @DisplayName("stt로 변환할 텍스트 파일의 존재여부 확인 : 음성 파일 url")
    void throwExceptionByExistFile() {
        //given
        String noneS3FileUrl = "https://bridge-talk.s3.ap-northeast-2.amazonaws.com/" + " " + "/" + " ";
        Long reportsId = 1L;

        //when-then
        assertThatThrownBy(() -> lettersService.createText(noneS3FileUrl, parents.getUuid().toString(), reportsId))
                .isInstanceOf(BaseException.class)
                .hasMessage(LettersErrorCode.LETTERS_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("올바른 s3의 음성파일로 stt를 실행")
    void startStt() {
        //given
        String fileName = "test/test-v6.mp3";

        //when
        String extractText = lettersService.stt(fileName, Language.viet);

        //given
        String results = "Alice, mình hiểu là cậu muốn đi công viên giải trí mà. Tôi xin lỗi vì luôn bận rộn và không thể nghe lời bạn một cách đúng đắn. Tôi hiểu cảm giác của anh, mình muốn chia sẻ những trải nghiệm thú.";
        assertThat(extractText).isNotEmpty();
    }

    @Test
    @DisplayName("chatgpt api 호출 시, 올바른 텍스트 입력 확인")
    void throwExceptionByEmptyFile() {
        // given
        String originalText = "";

        // when-then
        assertThatThrownBy(() -> lettersService.changeToConversation(originalText))
                .isInstanceOf(BaseException.class)
                .hasMessage(LettersErrorCode.CHATGPT_EMPTY_TEXT.getMessage());
    }

    @Test
    @DisplayName("chatgpt api 정상 호출 확인")
    void successChangeTonConversation() {
        //given
        String originalText = "Kim, mình hiểu là cậu muốn đi công viên giải trí mà. Tôi xin lỗi vì luôn bận rộn và không thể nghe lời bạn một cách đúng đắn. Tôi hiểu cảm giác của anh, mình muốn chia sẻ những trải nghiệm thú.";

        // when
        String result = lettersService.changeToConversation(originalText);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result).isNotNull();

    }

    @Test
    @DisplayName("papago 번역 api 번역 언어(target) 및 원본 언어(source) 미지정 오류")
    void throwExceptionByEmptylanguage() {
        // given
        String originalText = "Kim, mình hiểu là cậu muốn đi công viên giải trí mà. Tôi xin lỗi vì luôn bận rộn và không thể nghe lời bạn một cách đúng đắn. Tôi hiểu cảm giác của anh, mình muốn chia sẻ những trải nghiệm thú.";
        String source = "";
        String target = "";

        // when-then
        assertThatThrownBy(() -> translationService.translation(originalText, source, target))
                .isInstanceOf(BaseException.class)
                .hasMessage(LettersErrorCode.TRANSLATION_BAD_REQUEST.getMessage());
    }

    @Test
    @DisplayName("papago api 정상 호출 확인")
    void successTranslation() {
        //given
        String originalText = "Kim, mình hiểu là cậu muốn đi công viên giải trí mà. Tôi xin lỗi vì luôn bận rộn và không thể nghe lời bạn một cách đúng đắn. Tôi hiểu cảm giác của anh, mình muốn chia sẻ những trải nghiệm thú.";
        String source = "vi";
        String target = "ko";

        // when
        String result = translationService.translation(originalText, source, target);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("ID(PK)로 삭제되지 않은 편지 정보를 조회한다")
    void findById() {
        // given
        deletedLetters.updateIsDeleted();

        // when
        Letters findLetters = lettersService.findById(existLetters.getLettersId());

        // then
        assertThat(findLetters).isEqualTo(existLetters);

        Assertions.assertThatThrownBy(() -> lettersService.findById(deletedLetters.getLettersId()))
                .isInstanceOf(BaseException.class)
                .hasMessageContaining(LettersErrorCode.LETTERS_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("번역된 텍스트의 음성 데이터를 반환하고 읽음 여부가 체크된다.")
    void findLettersVoice() {
        // given
        String inputText = existLetters.getLettersTranslationContent();
        Resource expectedVoice = mock(Resource.class);
        when(ttsService.textToSpeech(inputText)).thenReturn(expectedVoice);

        // when
        Resource voiceResource = lettersService.findLettersVoice(existLetters.getLettersId());

        // then
        assertThat(voiceResource).isEqualTo(expectedVoice);
        assertThat(existLetters.getIsChecked()).isEqualTo(1);
    }

    @Test
    @DisplayName("음성 편지 삭제에 성공한다.")
    void deleteLettersSuccess() {
        // given
        deletedLetters.updateIsDeleted();

        // when
        lettersService.deleteLetters(existLetters.getLettersId());

        // then
        assertThat(existLetters.getIsDeleted()).isEqualTo(1);

        Assertions.assertThatThrownBy(() -> lettersService.findById(deletedLetters.getLettersId()))
                .isInstanceOf(BaseException.class)
                .hasMessageContaining(LettersErrorCode.LETTERS_NOT_FOUND.getMessage());
    }



}