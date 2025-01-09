package com.shinsegae.project.member.controller;

import com.shinsegae.project.member.service.UserService;
import com.shinsegae.project.member.vo.MemberVO;
import com.shinsegae.project.member.vo.UserVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user/member")
@RequiredArgsConstructor
public class MemberController {
    private final UserService userService;

    @GetMapping("login")
    public String login() {
        System.out.println("login");
        return "user/member/login";
    }

    @PostMapping("login")
    public String login(UserVO userVO, HttpSession session, Model model) {
        System.out.println("UserVO" + userVO);
        System.out.println("session" + session);
        boolean result = userService.login(userVO); //싱글톤 객체 찾아서 메서드 호출해야할 것 같음
        System.out.println("============= result" + result + " =================");
        if (result) {
            session.setAttribute("id", userVO.getId());
        } else {
            model.addAttribute("result", "로그인에 실패하였습니다!");
        }
        System.out.println("세션값 설정 확인>> " + session.getAttribute("id"));
        return "/user/home";
    }

    @GetMapping("checkId")
    @ResponseBody
    public boolean checkId(@RequestParam String id){
        System.out.println("user id >> " + id);
        boolean result = userService.checkId(id);
        return result;
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("id");
        System.out.println("logout >> " + session.getAttribute("id"));
        return "/user/member/login";
    }

    @GetMapping("register")
    public String register() {
        return "user/member/register";
    }

    @PostMapping("register")
    public String register2(UserVO userVO) {
        System.out.println("userVO >> " + userVO);
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