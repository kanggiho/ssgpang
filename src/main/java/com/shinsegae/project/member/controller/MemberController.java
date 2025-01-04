package com.shinsegae.project.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/member")
public class MemberController {

    // localhost:8080/member/forgot-password
    @GetMapping("login")
    public String login() {
        System.out.println("========================================");
        System.out.println("GET request to login received...");

        return "user/member/login";
    }

    // localhost:8080/member/forgot-password
    @GetMapping("forgot-password")
    public String forgotPw() {
        System.out.println("========================================");
        System.out.println("GET request to find password received...");

        return "user/member/forgot-password";
    }

    // localhost:8080/member/register
    @GetMapping("register")
    public String register() {
        System.out.println("========================================");
        System.out.println("GET request to register received...");

        return "user/member/register";
    }

}