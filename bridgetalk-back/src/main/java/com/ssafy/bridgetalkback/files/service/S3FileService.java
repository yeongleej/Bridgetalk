package com.ssafy.bridgetalkback.files.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ssafy.bridgetalkback.files.exception.S3FileErrorCode;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3FileService {
    private static final String LETTERS = "letters";
    private static final String REPORTS = "reports";
    private static final String PUZZLE = "game-puzzle";
    private static final String ICONS = "icons";
    private final AmazonS3 amazonS3;

    @Value("${S3_BUCKET_NAME}")
    private String bucket;

    /**
     * uploadVoiceFiles() : 음성 파일 S3 업로드 함수
     *
     * @param file : 입력 파일
     * @return String : 업로드 된 S3 주소
     */
    public String uploadLettersFiles(MultipartFile file) {
        log.info("{ S3FileService } : S3 파일 업로드 서비스");
        log.info(">> 파일 존재 여부 확인");
        validateFileExists(file);
        log.info(">> 파일 타입 확인 : {}", file.getContentType());
        validateAudioContentType(file);
        return uploadFile(LETTERS, file);
    }

    /**
     * uploadReportsFiles() : 음성 파일 S3 업로드 함수
     *
     * @param file : 입력 파일
     * @return String : 업로드 된 S3 주소
     */
    public String uploadReportsFiles(MultipartFile file) {
        log.info("{ S3FileService } : S3 파일 업로드 서비스");
        log.info(">> 파일 존재 여부 확인");
        validateFileExists(file);
        log.info(">> 파일 타입 확인 : {}", file.getContentType());
        validateAudioContentType(file);
        return uploadFile(REPORTS, file);
    }

    /**
     * uploadPuzzleFiles() : 이미지 파일 S3 업로드 함수
     *
     * @param file : 입력 파일
     * @return String : 업로드 된 S3 주소
     */
    public String uploadPuzzleFiles(MultipartFile file) {
        log.info("{ S3FileService } : S3 파일 업로드 서비스");
        log.info(">> 파일 존재 여부 확인");
        validateFileExists(file);
        log.info(">> 파일 타입 확인 : {}", file.getContentType());
        validateImageContentType(file);
        return uploadFile(PUZZLE, file);
    }

    /**
     * deleteFiles() : S3 파일 삭제
     *
     * @param uploadFileUrl : S3 파일 경로
     * @return : None
     */
    public void deleteFiles(String uploadFileUrl) {
        log.info("{ S3FileService } : S3 파일 삭제 서비스");
        if (uploadFileUrl == null || uploadFileUrl.isEmpty()) {
            throw BaseException.type(S3FileErrorCode.INVALID_DIR);
        }
        log.info(">> 파일 존재 : yes | 삭제 진행");
        deleteFile(uploadFileUrl);
    }

    /**
     * validateAudioContentType() : 입력 음성 파일 content type 검사
     *
     * @param file : 입력 파일
     * @return : None
     */
    private void validateAudioContentType(MultipartFile file) {
        String contentType = file.getContentType();
        if (!((contentType.equals("audio/mpeg")) || (contentType.equals("audio/mp4")) || (contentType.equals("audio/mp3")))) {
            throw BaseException.type(S3FileErrorCode.NOT_AN_AUDIO);
        }
    }

    /**
     * validateImageContentType() : 입력 이미지 파일 content type 검사
     *
     * @param file : 입력 파일
     * @return : None
     */
    private void validateImageContentType(MultipartFile file) {
        String contentType = file.getContentType();
        if (!(contentType.equals("image/jpeg") || contentType.equals("image/png") ||
                contentType.equals("image/jpg"))) {
            throw BaseException.type(S3FileErrorCode.NOT_AN_IMAGE);
        }
    }


    /**
     * validateFileExixts() : 입력 파일 존재 여부 검사
     *
     * @param file : 입력 파일
     * @return : None
     */
    private void validateFileExists(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw BaseException.type(S3FileErrorCode.EMPTY_FILE);
        }
    }

    /**
     * uploadFile() : 파일 S3 업로드 실행
     *
     * @param dir  : 파일 카테고리
     * @param file : 입력 파일
     * @return String : 업로드 S3 url 주소
     */
    private String uploadFile(String dir, MultipartFile file) {
        log.info(">> uploadFile() : S3 파일 업로드 실행 메서드");
        String fileKey = createFilePath(dir, file.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());
        try {
            // putObject(버킷명, 파일명, 파일데이터, 메타데이터)로 S3에 객체 등록
            log.info(">> s3 파일 put");
            amazonS3.putObject(
                    bucket, fileKey, file.getInputStream(), objectMetadata
            );
        } catch (IOException e) {
            log.error("S3 파일 업로드 실패: {}", e.getMessage());
            throw BaseException.type(S3FileErrorCode.S3_UPLOAD_FAILED);
        }

        log.info(">> S3 파일 업로드 성공 : {}", amazonS3.getUrl(bucket, fileKey).toString());
        return amazonS3.getUrl(bucket, fileKey).toString();
    }

    /**
     * createFilePath() : 파일의 새로운 path 생성
     *
     * @param dir              : 파일 카테고리
     * @param originalFileName : 원본 파일 이름
     * @return String : 새로운 파일 이름(주소)
     */
    private String createFilePath(String dir, String originalFileName) {
        String uuidName = UUID.randomUUID() + "_" + originalFileName;

        return switch (dir) {
            case LETTERS -> String.format("letters/%s", uuidName);
            case REPORTS -> String.format("reports/%s", uuidName);
            case PUZZLE -> String.format("game-puzzle/%s", uuidName);
            case ICONS -> String.format("icon/%s", uuidName);
            default -> throw BaseException.type(S3FileErrorCode.INVALID_DIR);
        };
    }

    /**
     * deleteFile() : S3 파일 삭제 실행
     *
     * @param uploadFileUrl : S3 파일 주소
     * @return : None
     */
    private void deleteFile(String uploadFileUrl) {
        log.info(">> deleteFile() : S3 파일 업로드 삭제 메서드");
        String fileKey = uploadFileUrl.substring(52);
        try {
            amazonS3.deleteObject(bucket, fileKey);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
        log.info(">> S3 파일 업로드 삭제 성공!");
    }

}

