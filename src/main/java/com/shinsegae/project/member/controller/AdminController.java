package com.shinsegae.project.member.controller;

import com.shinsegae.project.member.service.AdminService;
import com.shinsegae.project.member.service.UserService;
import com.shinsegae.project.member.vo.AdminVO;
import com.shinsegae.project.member.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * AdminController
 *
 * 관리자 로그인, 등록, 정보 관리 등의 기능을 제공하는 컨트롤러 클래스입니다.
 */
@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;


    @GetMapping("login")
    @Operation(summary = "관리자 로그인 페이지", description = "관리자와 유저의 세션 상태에 따라 로그인 페이지 또는 홈으로 이동")
    public String login(HttpSession session) {
        if (session == null) {
            return "redirect:/user/member/login";
        }
        String role = (String) session.getAttribute("role");
        if ("ADMIN".equals(role)) {
            return "redirect:/admin/home_admin";
        } else if ("USER".equals(role)) {
            return "redirect:/user/home";
        }
        return "user/member/login";
    }


    @PostMapping("login")
    @Operation(summary = "로그인 처리", description = "관리자 또는 유저의 로그인 요청을 처리")
    public String login(AdminVO adminVO, UserVO userVO, HttpSession session, Model model) {
        boolean result = adminService.login(adminVO);
        if (result) {
            session.setAttribute("adminId", adminVO.getId());
            session.setAttribute("role", "ADMIN");
            return "redirect:/admin/home_admin";
        }
        boolean result2 = userService.login(userVO);
        if (result2) {
            session.setAttribute("userId", userVO.getId());
            session.setAttribute("role", "USER");
            return "redirect:/user/home";
        }
        model.addAttribute("result", "로그인에 실패하였습니다!");
        return "user/member/login";
    }


    @GetMapping("logout")
    @Operation(summary = "로그아웃 처리", description = "현재 세션을 무효화하고 로그인 페이지로 이동")
    public String logout(HttpSession session) {
        session.invalidate();
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        return "user/member/login";
    }


    @GetMapping("register")
    @Operation(summary = "관리자 등록 페이지", description = "관리자 등록 페이지를 반환")
    public String register() {
        return "user/member/register";
    }


    @PostMapping("register")
    @Operation(summary = "관리자 등록", description = "새로운 관리자를 등록")
    public String register2(AdminVO adminVO) {
        adminService.insertAdmin(adminVO);
        return "user/member/login";
    }

    @GetMapping("find_pw")
    @Operation(summary = "관리자 비밀번호 찾기", description = "관리자의 비밀번호를 조회")
    public String find_pw(String id) {
        adminService.selectPwByAdminId(id);
        return "user/member/find_pw";
    }


    @GetMapping("info")
    @Operation(summary = "관리자 정보 확인", description = "관리자의 상세 정보를 확인")
    public String info(String id, Model model, HttpSession session) {
        AdminVO adminVO = adminService.info(id);
        model.addAttribute("adminVO", adminVO);
        if (session.getAttribute("adminId") != null) {
            return "/user/member/info";
        }
        return "/user/member/login";
    }

    @PostMapping("update")
    @Operation(summary = "관리자 정보 업데이트", description = "관리자 정보를 업데이트")
    public String update(AdminVO adminVO) {
        int result = adminService.updateAdmin(adminVO);
        if (result > 0) {
            return "user/member/info";
        } else {
            return "/index";
        }
    }


    @GetMapping("delete")
    @Operation(summary = "관리자 삭제", description = "지정된 관리자를 삭제")
    public String delete(String id) {
        adminService.deleteAdmin(id);
        return "user/member/delete";
    }
}
