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


    //관리자 로그인
    @GetMapping("login")
    public String login(HttpSession session) {
        if (session.getAttribute("id") != null) {
            return "/admin/home_admin";  // 로그인된 경우 Admin 홈으로 리다이렉트
        }
        return "/user/member/login";  // 세션에 id가 없으면 로그인 페이지로
    }

    @PostMapping("login")
    public String login(AdminVO adminVO, HttpSession session, Model model) {
        System.out.println("AdminVO" + adminVO);
        System.out.println("session" + session);

        // 로그인 검증: id와 비밀번호가 맞는지 체크
        boolean result = adminService.login(adminVO);
        System.out.println("============= result" + result + " =================");

        if (result) {
            // 로그인 성공: 세션에 사용자 정보 설정
            session.setAttribute("id", adminVO.getId());
            return "/admin/home_admin";  // 로그인 성공 시 홈 페이지로 리다이렉트
        } else {
            // 로그인 실패: 에러 메시지 전달
            model.addAttribute("result", "로그인에 실패하였습니다!");
            return "/user/member/login";  // 로그인 페이지로 다시 돌아가도록
        }
    }


    //관리자 로그아웃
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("id");
        System.out.println("logout >> " + session.getAttribute("id"));
        return "/user/member/login";
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
    public String info (int id, Model model, HttpSession session) {
        AdminVO adminVO = adminService.info(id);
        System.out.println("admin id >> " + id);
        model.addAttribute("adminVO", adminVO);

        // 세션에 id가 있으면 회원정보 페이지로 리다이렉트
        if (session.getAttribute("id") != null) {
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