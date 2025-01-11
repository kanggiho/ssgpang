package com.shinsegae.project.member.controller;

import com.shinsegae.project.member.service.UserService;
import com.shinsegae.project.member.vo.UserVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user/member")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("login")
    public String login(HttpSession session) {
        System.out.printf("User login session id: %s\n", session.getId());
        String role = (String) session.getAttribute("role");
        if (role != null) {
            if ("USER".equals(role)) {
                return "redirect:/user/home";  // 유저 홈으로 리다이렉트
            } else if ("ADMIN".equals(role)) {
                return "redirect:/admin/home_admin";  // 관리자 홈으로 리다이렉트
            }
        }
        return "user/member/login";  // 로그인 페이지로 리다이렉트
    }

    @PostMapping("login")
    public String login(UserVO userVO, HttpSession session, Model model) {
        boolean result = userService.login(userVO);

        if (result) {
            // 로그인 성공 시 세션에 사용자 정보 저장
            session.setAttribute("userId", userVO.getId());  // 사용자 ID 저장
            session.setAttribute("role", "USER");  // 사용자 역할 설정
            return "redirect:/user/home";  // 사용자 홈으로 리다이렉트
        } else {
            model.addAttribute("result", "로그인에 실패하였습니다!");
            return "user/member/login";  // 로그인 실패 시 로그인 페이지로 리다이렉트
        }
    }

    // 유저 로그아웃
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userId");  // 사용자 세션 삭제
        session.removeAttribute("role");
        return "user/member/login";  // 로그인 페이지로 리다이렉트
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

    // 유저 회원정보
    @GetMapping("info")
    public String info(HttpSession session, Model model) {
        // 세션에서 id 가져오기
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            // 세션에 id가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/user/member/login";
        }

        UserVO userVO = userService.info(userId);
        if (userVO != null) {
            model.addAttribute("userVO", userVO);
            return "/user/member/info";
        } else {
            // 유저 정보를 찾지 못한 경우 예외 처리 페이지로 이동
            return "redirect:/user/member/error";
        }
    }


    // 회원정보수정
    @GetMapping("update")
    public String update(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");

        if (userId != null) {
            UserVO userVO = userService.selectUserById(userId);
            model.addAttribute("userVO", userVO);
            return "/user/member/update";  // 정보 수정 페이지로 이동
        }

        return "/user/member/login";  // 세션에 id가 없으면 로그인 페이지로 이동
    }

    // 회원정보수정
    @PostMapping("update")
    public String update(UserVO userVO) {
        int result = userService.updateUser(userVO);

        if (result > 0) {
            return "redirect:/user/member/info";
        } else {
            return "/user/home";
        }
    }


    //회원탈퇴
    @GetMapping("delete")
    public String delete() {

        return "user/member/delete";
    }

    @PostMapping("delete")
    public String deleteUser(String id, String password,
            @RequestParam("agree") boolean agree,
            Model model, HttpSession session, RedirectAttributes redirectAttributes) {

        // 동의 여부 확인
        if (!agree) {
            model.addAttribute("error", "탈퇴 동의 체크박스를 선택해야 합니다.");
            return "user/member/delete";
        }

        // 비밀번호 확인
        boolean checkPassword = userService.checkPassword(password);
        if (!checkPassword) {
            model.addAttribute("error", "비밀번호가 올바르지 않습니다.");
            return "user/member/delete";
        }

        // 회원 탈퇴 처리
        int result = userService.deleteUser(id);
        if (result > 0) {
            session.invalidate(); // 세션 완전 무효화
            redirectAttributes.addFlashAttribute("successMessage", "회원 탈퇴가 완료되었습니다.");
            return "redirect:/user/member/login"; // 탈퇴 후 로그인 페이지로 리다이렉트
        } else {
            model.addAttribute("error", "회원 탈퇴 중 문제가 발생했습니다.");
            return "user/member/delete"; // 탈퇴 실패 시 다시 폼으로
        }
    }

}