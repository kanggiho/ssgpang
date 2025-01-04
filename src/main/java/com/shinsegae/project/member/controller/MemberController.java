package com.shinsegae.project.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/member")
public class MemberController {

    @GetMapping("login")
    public String login() {
        return "user/member/login";
    }

    @GetMapping("find_pw")
    public String find_pw() {
        return "user/member/find_pw";
    }

    @GetMapping("register")
    public String register() {
        return "user/member/register";
    }

}