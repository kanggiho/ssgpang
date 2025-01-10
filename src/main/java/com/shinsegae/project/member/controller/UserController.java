package com.shinsegae.project.member.controller;

import com.shinsegae.project.member.service.UserService;
import com.shinsegae.project.member.vo.UserVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user/member")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    //유저 로그인
    @GetMapping("login")
    public String login(HttpSession session) {
        // 세션에 id가 있으면 홈 페이지로 리다이렉트
        if (session.getAttribute("id") != null) {
            return "redirect:/user/home";  // 로그인된 경우 홈으로 리다이렉트
        }
        return "/user/member/login";  // 세션에 id가 없으면 로그인 페이지로
    }

    @PostMapping("login")
    public String login(UserVO userVO, HttpSession session, Model model) {
        System.out.println("UserVO" + userVO);
        System.out.println("session" + session);

        // 로그인 검증: id와 비밀번호가 맞는지 체크
        boolean result = userService.login(userVO);
        System.out.println("============= result" + result + " =================");

        if (result) {
            // 로그인 성공: 세션에 사용자 정보 설정
            session.setAttribute("id", userVO.getId());
            return "redirect:/user/home";  // 로그인 성공 시 홈 페이지로 리다이렉트
        } else {
            // 로그인 실패: 에러 메시지 전달
            model.addAttribute("result", "로그인에 실패하였습니다!");
            return "/user/member/login";  // 로그인 페이지로 다시 돌아가도록
        }
    }

    //유저 아이디 유효성 검증
    @GetMapping("checkId")
    @ResponseBody
    public boolean checkId(@RequestParam String id) {
        System.out.println("user id >> " + id);
        boolean result = userService.checkId(id);
        return result;
    }

    //유저 이메일 유효성 검증
    @GetMapping("checkEmail")
    @ResponseBody
    public boolean checkEmail(@RequestParam String email) {
        System.out.println("user email >> " + email);
        boolean isEmailAvailable = userService.checkEmail(email);
        return isEmailAvailable;
    }

    //유저 로그아웃
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("id");
        System.out.println("logout >> " + session.getAttribute("id"));
        return "/user/member/login";
    }

    //유저 회원가입
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

    //유저 ID 찾기
    @GetMapping("find_id")
    public String find_id() {
        return "user/member/find_id";
    }

    //유저 PW 찾기
    @GetMapping("find_pw")
    public String find_pw() {
        return "user/member/find_pw";
    }

    //유저 회원 정보
    @GetMapping("info")
    public String info(String id, Model model, HttpSession session) {
        UserVO userVO = userService.info(id);
        System.out.println("user id >> " + id);
        model.addAttribute("userVO", userVO);

        if (session.getAttribute("id") != null) {
            return "/user/member/info";
        }

        return "user/member/login";  // 세션에 id가 없으면 로그인 페이지로
    }

    //유저 회원정보수정
    @PostMapping("update")
    public String update(UserVO userVO) {
        int result = userService.updateUser(userVO);
        if (result > 0) {
            return "user/member/info";
        } else {
            return "/index";
        }
    }

    //유저 회원탈퇴
    @GetMapping("delete")
    public String delete() {
        return "user/member/delete";
    }
}