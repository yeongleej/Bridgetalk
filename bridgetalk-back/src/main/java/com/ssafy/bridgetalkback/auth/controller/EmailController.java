package com.ssafy.bridgetalkback.auth.controller;

import com.ssafy.bridgetalkback.auth.dto.request.EmailAuthRequestDto;
import com.ssafy.bridgetalkback.auth.dto.request.EmailRequestDto;
import com.ssafy.bridgetalkback.auth.service.EmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Auth", description = "AuthApiController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mail")
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/authcode-send")
    public ResponseEntity<Void> sendAuthCode(@Valid @RequestBody EmailRequestDto requestDto) throws MessagingException {
        log.info("{ MailController } : 인증번호 전송 진입");
        emailService.sendAuthCode(requestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/authcode-verify")
    public ResponseEntity<Boolean> verifyAuthCode(@Valid @RequestBody EmailAuthRequestDto requestDto) {
        log.info("{ MailController } : 인증번호 검증 진입");
        emailService.verifyAuthCode(requestDto);
        return ResponseEntity.ok().build();
    }
}
