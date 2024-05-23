package com.ssafy.bridgetalkback.files.service;

import com.ssafy.bridgetalkback.common.ServiceTest;
import com.ssafy.bridgetalkback.files.config.S3MockConfig;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.files.exception.S3FileErrorCode;
import io.findify.s3mock.S3Mock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@Import(S3MockConfig.class)
@DisplayName("File [Service Layer] -> FileService 테스트")
class FileServiceTest extends ServiceTest {
    @Autowired
    private S3FileService fileService;

    @Autowired
    private S3Mock s3Mock;

    private final String FILE_PATH = "src/test/resources/files/";

    @AfterEach
    public void tearDown() {
        s3Mock.stop();
    }

    @Nested
    @DisplayName("Letters 부모 음성 편지 업로드")
    class uploadVoiceFiles {
        @Test
        @DisplayName("빈 파일이면 업로드에 실패한다")
        void throwExceptionByEmptyFile() {
            // given
            MultipartFile nullFile = null;
            MultipartFile emptyFile = new MockMultipartFile("letters", "empty.mp3", "audio/mpeg", new byte[]{});

            // when - then
            assertThatThrownBy(() -> fileService.uploadLettersFiles(nullFile))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(S3FileErrorCode.EMPTY_FILE.getMessage());
            assertThatThrownBy(() -> fileService.uploadLettersFiles(emptyFile))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(S3FileErrorCode.EMPTY_FILE.getMessage());
        }

        @Test
        @DisplayName("파일 업로드에 성공한다")
        void success() throws Exception {
            // given
            String fileName = "test.mp3";
            String contentType = "audio/mpeg";
            String dir = "letters";
            MultipartFile file = createMockMultipartFile(dir, fileName, contentType);

            // when
            String fileKey = fileService.uploadLettersFiles(file);

            // then
            assertThat(fileKey).contains("test.mp3");
            assertThat(fileKey).contains(dir);
        }
    }

    private MultipartFile createMockMultipartFile(String dir, String fileName, String contentType) throws IOException {
        try (FileInputStream stream = new FileInputStream(FILE_PATH + fileName)) {
            return new MockMultipartFile(dir, fileName, contentType, stream);
        }
    }
}
