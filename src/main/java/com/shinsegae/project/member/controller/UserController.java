package com.shinsegae.project.member.controller;

import com.shinsegae.project.member.service.UserService;
import com.shinsegae.project.member.vo.UserVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("find_id")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> find_id2(@RequestBody Map<String, String> request) {
        String tel = request.get("tel");
        System.out.printf("user find_id >> " + tel);

        // 전화번호로 아이디 찾기
        String userId = userService.selectIdByUserTel(tel);

        Map<String, Object> response = new HashMap<>();
        if (userId != null) {
            response.put("userId", userId);  // 아이디가 있을 경우
        } else {
            response.put("userId", null);  // 아이디가 없을 경우
        }

        return ResponseEntity.ok(response);
    }

    //유저 PW 찾기
    @GetMapping("find_pw")
    public String find_pw() {
        return "user/member/find_pw";
    }

    // 유저 회원 정보
    @GetMapping("info")
    public String info(HttpSession session, Model model) {
        // 세션에서 id 가져오기
        String id = (String) session.getAttribute("id");

        if (id == null) {
            // 세션에 id가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/user/member/login";
        }

        UserVO userVO = userService.info(id);
        if (userVO != null) {
            model.addAttribute("userVO", userVO);
            return "/user/member/info";
        } else {
            // 유저 정보를 찾지 못한 경우 예외 처리 페이지로 이동
            return "redirect:/user/member/error";
        }
    }


    // 회원정보수정 GET 요청
    @GetMapping("update")
    public String update(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("id");

        if (userId != null) {
            UserVO userVO = userService.selectUserById(userId);
            model.addAttribute("userVO", userVO);
            return "/user/member/update";  // 정보 수정 페이지로 이동
        }

        return "/user/member/login";  // 세션에 id가 없으면 로그인 페이지로 이동
    }

    // 회원정보수정 POST 요청
    @PostMapping("update")
    public String update(HttpSession session, UserVO userVO) {

        int result = userService.updateUser(userVO);

        if (result > 0) {
            return "redirect:/user/member/info";  // 수정 성공 후 정보 페이지로 리다이렉트
        } else {
            return "redirect:/index";  // 수정 실패 시 홈 페이지로 리다이렉트
        }
    }


    //유저 회원탈퇴
    @GetMapping("delete")
    public String delete() {
        return "user/member/delete";
    }
}