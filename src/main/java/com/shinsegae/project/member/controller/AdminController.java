package com.shinsegae.project.member.controller;

import com.shinsegae.project.member.service.AdminService;
import com.shinsegae.project.member.vo.AdminVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("login")
    public String login(HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (role != null) {
            if ("ADMIN".equals(role)) {
                return "redirect:/admin/home_admin";  // 관리자 홈으로 리다이렉트
            } else if ("USER".equals(role)) {
                return "redirect:/user/home";  // 유저 홈으로 리다이렉트
            }
        }
        return "user/member/login";  // 로그인 페이지로 리다이렉트
    }

    @PostMapping("login")
    public String login(AdminVO adminVO, HttpSession session, Model model) {

        boolean result = adminService.login(adminVO);

        if (result) {
            // 로그인 성공 시 세션에 관리자 정보 저장
            session.setAttribute("adminId", adminVO.getId());  // 관리자 ID 저장
            session.setAttribute("role", "ADMIN");  // 관리자 역할 설정
            return "redirect:/admin/home_admin";  // 관리자 홈으로 리다이렉트
        } else {
            // 로그인 실패 시 세션 초기화 불필요
            model.addAttribute("result", "로그인에 실패하였습니다!");
            return "user/member/login";  // 로그인 실패 시 로그인 페이지로 리다이렉트
        }
    }

    // 관리자 로그아웃
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("adminId");  // 관리자 세션 삭제
        session.removeAttribute("role");
        return "user/member/login";  // 로그인 페이지로 리다이렉트
    }


    //관리자 등록
    @GetMapping("register")
    public String register() {
        return "user/member/register";
    }

    @PostMapping("register")
    public String register2(AdminVO adminVO) {
        System.out.println("adminVO >> " + adminVO);
        adminService.insertAdmin(adminVO);

        return "user/member/login";
    }

    //관리자 PW 찾기
    @GetMapping("find_pw")
    public String find_pw(int id) {
        adminService.selectPwByAdminId(id);
        return "user/member/find_pw";
    }

    //관리자 정보 확인
    @GetMapping("info")
    public String info(int id, Model model, HttpSession session) {
        AdminVO adminVO = adminService.info(id);
        System.out.println("admin id >> " + id);
        model.addAttribute("adminVO", adminVO);

        // 세션에 id가 있으면 회원정보 페이지로 리다이렉트
        if (session.getAttribute("adminId") != null) {
            return "/user/member/info";
        }

        return "/user/member/login";  // 세션에 id가 없으면 로그인 페이지로
    }

    //관리자 정보 업데이트
    @PostMapping("update")
    public String update(AdminVO adminVO) {
        int result = adminService.updateAdmin(adminVO);
        if (result > 0) {
            return "user/member/info";
        } else {
            return "/index";
        }
    }

    //관리자 삭제
    @GetMapping("delete")
    public String delete(int id) {
        adminService.deleteAdmin(id);
        return "user/member/delete";
    }
}