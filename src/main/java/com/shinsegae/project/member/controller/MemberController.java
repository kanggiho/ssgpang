package com.shinsegae.project.member.controller;

import com.shinsegae.project.member.service.UserService;
import com.shinsegae.project.member.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/member")
@RequiredArgsConstructor
public class MemberController {
    private final UserService userService;

    @GetMapping("login")
    public String login() {
        System.out.println("========================login========================");
        return "user/member/login";
    }

    @GetMapping("register")
    public String register() {
        return "user/member/register";
    }

    @PostMapping("register")
    public String register2(UserVO userVO) {
        userService.insertUser(userVO);
        return "user/member/login";
    }


    @GetMapping("find_id")
    public String find_id() {
        return "user/member/find_id";
    }

    @GetMapping("find_pw")
    public String find_pw() {
        return "user/member/find_pw";
    }


    @GetMapping("update")
    public String update() {
        return "user/member/update";
    }

    @GetMapping("delete")
    public String delete() {
        return "user/member/delete";
    }
}