package com.shinsegae.project.member.smtp.controller;

import com.shinsegae.project.member.service.UserService;
import com.shinsegae.project.member.smtp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("user/member")
public class EmailController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;  // 사용자 서비스, DB 업데이트용

    // 비밀번호 찾기 처리 (이메일 입력받고 임시 비밀번호 전송)
    @PostMapping("/find_pw")
    public String findPassword(@RequestParam("email") String email, Model model) {
        // 이메일이 DB에 존재하는지 확인
        if (userService.checkEmail(email)) {
            // 임시 비밀번호 생성
            String tempPassword = emailService.tempPassword();

            // 이메일로 임시 비밀번호 전송
            emailService.sendTempPassword(email, tempPassword);

            // DB에 임시 비밀번호 저장 (암호화 후 저장)
            userService.updateTemporaryPassword(email, tempPassword);

            model.addAttribute("message", "이메일로 임시 비밀번호를 전송했습니다. 이메일을 확인해주세요.");
            return "user/member/login";  // 임시 비밀번호 전송 후 login 페이지로 이동
        } else {
            model.addAttribute("error", "등록되지 않은 이메일입니다.");
            return "user/member/find_pw";  // 이메일이 없는 경우 비밀번호 찾기 페이지로 돌아감
        }
    }
}
