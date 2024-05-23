package com.ssafy.bridgetalkback.files.controller;


import com.ssafy.bridgetalkback.files.service.S3FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
@Slf4j
public class S3FileController {
    private final S3FileService s3FileService;

    // 실제로 사용x 테스트용 controller
    @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@RequestPart(value = "lettersFile") MultipartFile multipartFile) {
        log.info("{ S3FileController } : s3 파일 업로드 테스트 Controller");
        log.info(">> lettersFile : {}", multipartFile);
        String uploadFileUrl = s3FileService.uploadLettersFiles(multipartFile);
        return ResponseEntity.ok(uploadFileUrl);
    }

    // 실제로 사용x 테스트용 controller
    @DeleteMapping
    public ResponseEntity<Void> delete( @RequestParam(value = "url") String uploadFileUrl) {
        s3FileService.deleteFiles(uploadFileUrl);
        return ResponseEntity.ok().build();
    }
}

