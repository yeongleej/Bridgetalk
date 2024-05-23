package com.ssafy.bridgetalkback.auth.service;

import com.ssafy.bridgetalkback.auth.domain.AuthCode;
import com.ssafy.bridgetalkback.auth.dto.request.EmailAuthRequestDto;
import com.ssafy.bridgetalkback.auth.dto.request.EmailRequestDto;
import com.ssafy.bridgetalkback.auth.exception.AuthErrorCode;
import com.ssafy.bridgetalkback.global.exception.BaseException;
import com.ssafy.bridgetalkback.parents.domain.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final AuthCodeService authCodeService;

    @Value("${MAIL_USERNAME}")
    private String senderEmail;

    public void sendAuthCode(EmailRequestDto requestDto) throws MessagingException {
        log.info("{ AuthCodeService } : 인증번호 발급 진입");
        Email email = Email.from(requestDto.email());
        String authCode = authCodeService.saveAuthCode(email);

        MimeMessage emailForm = createEmailForm(email, authCode);

        try {
            log.info("{ AuthCodeService } : 이메일 전송");
            javaMailSender.send(emailForm);
        } catch (RuntimeException e) {
            log.info("{ AuthCodeService } : 이메일 전송 실패");
            throw BaseException.type(AuthErrorCode.UNABLE_TO_SEND_EMAIL);
        }
    }

    public boolean verifyAuthCode(EmailAuthRequestDto requestDto) {
        log.info("{ AuthCodeService } : 인증번호 검증 진입");
        Email email = Email.from(requestDto.email());
        AuthCode code = authCodeService.findAuthCodeByEmail(email);
        log.info("{ AuthCodeService } : 실제 인증번호 - "+code.getAuthCode());
        log.info("{ AuthCodeService } : 입력받은 인증번호 - "+requestDto.authCode());
        if(!code.getAuthCode().equals(requestDto.authCode())) {
            throw BaseException.type(AuthErrorCode.WRONG_AUTH_CODE);
        }
        return true;
    }

    private MimeMessage createEmailForm(Email email, String authCode) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email.getValue());
        message.setSubject("BridgeTalk. 인증번호안내");
        message.setFrom(senderEmail);
        message.setText("10분후 만료될 예정입니다. \n"+authCode);
        return message;
    }
}
