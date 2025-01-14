package com.shinsegae.project.member.controller;

import com.shinsegae.project.member.service.AdminService;
import com.shinsegae.project.member.service.UserService;
import com.shinsegae.project.member.vo.AdminVO;
import com.shinsegae.project.member.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("user/member")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AdminService adminService;

    // 유저 로그인 GET 요청
    @GetMapping("login")
    @Operation(summary = "유저 로그인 페이지", description = "유저 및 관리자 세션 상태에 따라 로그인 페이지 또는 홈으로 이동")
    public String login(HttpSession session) {
        if (session == null) {
            return "redirect:/user/member/login";
        }
        String role = (String) session.getAttribute("role");
        if ("USER".equals(role)) {
            return "redirect:/user/home";  // 이미 로그인된 경우, 유저 홈으로 리디렉트
        } else if ("ADMIN".equals(role)) {
            return "redirect:/admin/home_admin";  // 관리자로 로그인되어 있다면, 관리자 홈으로 리디렉트
        }
        return "user/member/login";  // 로그인 페이지로 이동
    }

    // 유저 로그인 POST 요청
    @PostMapping("login")
    @Operation(summary = "로그인 처리", description = "유저 및 관리자의 로그인 요청을 처리")
    public String login(UserVO userVO, AdminVO adminVO,
                        HttpSession session, Model model) {
        boolean result = userService.login(userVO);
        if (result) {
            session.setAttribute("userId", userVO.getId());  // 사용자 ID 세션에 저장
            session.setAttribute("role", "USER");  // 사용자 역할 설정
            return "redirect:/user/home";  // 로그인 후 유저 홈으로 리디렉트
        }

        boolean result2 = adminService.login(adminVO);
        if (result2) {
            session.setAttribute("adminId", adminVO.getId());
            session.setAttribute("role", "ADMIN");
            return "redirect:/admin/home_admin";
        }

        model.addAttribute("result", "로그인에 실패하였습니다!");  // 로그인 실패 메시지
        return "user/member/login";  // 로그인 실패 시 로그인 페이지로 리디렉트
    }

    // 유저 로그아웃
    @GetMapping("logout")
    @Operation(summary = "로그아웃 처리", description = "현재 세션을 무효화하고 로그인 페이지로 이동")
    public String logout(HttpSession session) {
        session.invalidate();

        // 쿠키를 생성하여 삭제 처리 (유효 기간을 0으로 설정)
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        return "user/member/login";  // 로그인 페이지로 리디렉트
    }


    //유저 아이디 유효성 검증
    @GetMapping("checkId")
    @ResponseBody
    @Operation(summary = "아이디 유효성 검증", description = "입력된 아이디의 사용 가능 여부를 확인")
    public boolean checkId(@RequestParam String id) {
        System.out.println("user id >> " + id);
        boolean result = userService.checkId(id);
        return result;
    }

    //유저 이메일 유효성 검증
    @GetMapping("checkEmail")
    @ResponseBody
    @Operation(summary = "이메일 유효성 검증", description = "입력된 이메일의 사용 가능 여부를 확인")
    public boolean checkEmail(@RequestParam String email) {
        System.out.println("user email >> " + email);
        boolean isEmailAvailable = userService.checkEmail(email);
        return isEmailAvailable;
    }

    //유저 전화번호 유효성 검증
    @GetMapping("checkTel")
    @ResponseBody
    @Operation(summary = "전화번호 유효성 검증", description = "입력된 전화번호의 사용 가능 여부를 확인")
    public boolean checkTel(@RequestParam String tel) {
        System.out.println("user tel >> " + tel);
        boolean isTelAvailable = userService.checkTel(tel);
        return isTelAvailable;
    }

    //유저 회원가입
    @GetMapping("register")
    @Operation(summary = "회원가입 페이지", description = "회원가입 페이지를 반환")
    public String register() {
        return "user/member/register";
    }

    @PostMapping("register")
    @Operation(summary = "회원가입 처리", description = "유저 회원가입을 처리")
    public String register2(UserVO userVO) {
        System.out.println("userVO >> " + userVO);
        userService.insertUser(userVO);

        return "user/member/login";
    }

    //유저 ID 찾기
    @GetMapping("find_id")
    @Operation(summary = "ID 찾기 페이지", description = "유저 ID 찾기 페이지를 반환")
    public String find_id() {
        return "user/member/find_id";
    }

    @PostMapping("find_id")
    @ResponseBody
    @Operation(summary = "ID 찾기 처리", description = "전화번호를 통해 유저 ID를 조회")
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
    @Operation(summary = "비밀번호 찾기 페이지", description = "유저 비밀번호 찾기 페이지를 반환")
    public String find_pw() {

        return "user/member/find_pw";
    }

    // 유저 회원정보
    @GetMapping("info")
    @Operation(summary = "유저 정보 조회", description = "세션에서 유저 정보를 조회하여 반환")
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
    @Operation(summary = "회원정보 수정 페이지", description = "회원정보를 수정할 수 있는 페이지를 반환")
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
    @Operation(summary = "회원정보 수정 처리", description = "유저 정보를 수정하고 결과에 따라 페이지를 반환")
    public String update(UserVO userVO) {
        int result = userService.updateUser(userVO);

        if (result > 0) {
            return "redirect:/user/member/info";
        } else {
            return "/user/home";
        }
    }

    @GetMapping("delete")
    @Operation(summary = "회원탈퇴 페이지", description = "유저 탈퇴 페이지를 반환")
    public String delete() {
        return "user/member/delete";
    }

    @PostMapping("delete")
    @Operation(summary = "회원탈퇴 처리", description = "유저 탈퇴를 처리하고 결과에 따라 페이지를 반환")
    public String delete(String id, HttpSession session,
                         @RequestParam("agree") boolean agree,
                         Model model) {
        System.out.println("user id >>>>>>>>>>>>> " + id);

        if (!agree) {
            model.addAttribute("error", "탈퇴 동의 체크박스를 확인해주세요.");
            return "user/member/delete";
        }

        int result = userService.deleteUser(id);
        if (result > 0) { //성공하면, 세션 삭제후,
            session.invalidate();
            model.addAttribute("successMessage", "회원탈퇴를 성공적으로 마쳤습니다.");
            return "/user/member/login";
        } else {
            return "/user/member/delete";
        }
    }
}