package com.shinsegae.project.member.smtp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailService {

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")  // application.properties에 등록된 구글 SMTP 사용자명
    private String senderEmail;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendTempPassword(String Email, String tempPassword) {
        String subject = "임시 비밀번호 안내";
        String text = "안녕하세요,\n\n귀하의 임시 비밀번호는 " + tempPassword + " 입니다.\n\n로그인 후 비밀번호를 변경해주세요.";

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(senderEmail);
            message.setTo(Email);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 임시 비밀번호 생성 (UUID로 생성)
    public String tempPassword() {
        return UUID.randomUUID().toString().substring(0, 8);  // UUID에서 8자리만 잘라서 사용
    }
}
