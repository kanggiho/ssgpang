package com.shinsegae.project.search.controller;

import com.shinsegae.project.search.vo.LinkDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchController {

    // 모든 가능한 링크 목록을 정의
    private List<LinkDTO> allLinks = List.of(
            new LinkDTO("/admin/incoming/do_incoming", "입고하기"),
            new LinkDTO("/admin/incoming/confirm_incoming_list", "입고내역 확인"),
            new LinkDTO("/admin/inventory/confirm_inventory_list", "재고 확인"),
            new LinkDTO("/admin/inventory/edit_inventory", "재고 수정"),
            new LinkDTO("/admin/order/manage_outgoing_request", "발주 요청 관리"),
            new LinkDTO("/admin/map/map", "창고별 위치"),
            new LinkDTO("/admin/board/board", "게시판"),
            new LinkDTO("/admin/home_admin", "관리자 대시보드"),
            new LinkDTO("/user/map/map", "창고별 위치"),
            new LinkDTO("/user/board/board", "게시판"),
            new LinkDTO("/user/order/do_outgoing", "발주하기"),
            new LinkDTO("/user/order/confirm_outgoing_list", "발주내역 확인"),
            new LinkDTO("/user/member/delete", "회원탈퇴"),
            new LinkDTO("/user/member/info", "회원정보"),
//            new LinkDTO("/user/member/find_id", "아이디 찾기"),
//            new LinkDTO("/user/member/find_pw", "비밀번호 찾기"),
//            new LinkDTO("/user/member/login", "로그인"),
            new LinkDTO("/user/home", "사용자 대시보드"),
            new LinkDTO("/user/chatbot/chatbot","챗봇")
    );

    @GetMapping("/admin/search/search")
    @Operation(summary = "검색 페이지", description = "접근 가능한 링크를 검색어에 따라 필터링하여 반환")
    public String searchPage(@RequestParam(value = "query", required = false) String query, Model model,
                             HttpSession session) {
        // 세션에서 사용자 ID 확인
        String adminId = (String) session.getAttribute("adminId");
        String userId = (String) session.getAttribute("userId");
        List<LinkDTO> accessibleLinks;

        // 사용자 유형에 따라 접근 가능한 링크 필터링
        if (adminId != null) {
            accessibleLinks = allLinks.stream()
                    .filter(link -> link.getUrl().startsWith("/admin"))
                    .collect(Collectors.toList());
        } else if (userId != null) {
            accessibleLinks = allLinks.stream()
                    .filter(link -> link.getUrl().startsWith("/user"))
                    .collect(Collectors.toList());
        } else {
            // 잘못된 세션일 경우 빈 목록 반환
            accessibleLinks = new ArrayList<>();
        }

        // 검색어에 따라 링크 목록 필터링
        List<LinkDTO> filteredLinks;
        if (query == null || query.trim().isEmpty()) {
            filteredLinks = accessibleLinks;
        } else {
            String lowerQuery = query.trim().toLowerCase();
            filteredLinks = accessibleLinks.stream()
                    .filter(link -> link.getText().toLowerCase().contains(lowerQuery))
                    .collect(Collectors.toList());
        }

        model.addAttribute("query", query);
        model.addAttribute("links", filteredLinks);

        return "admin/search/search";
    }

    @GetMapping("user/search/search")
    public String searchPage2(@RequestParam(value = "query", required = false) String query, Model model,
                             HttpSession session) {
        // 세션에서 사용자 ID 확인
        String adminId = (String) session.getAttribute("adminId");
        String userId = (String) session.getAttribute("userId");
        List<LinkDTO> accessibleLinks;

        // 사용자 유형에 따라 접근 가능한 링크 필터링
        if (adminId != null) {
            accessibleLinks = allLinks.stream()
                    .filter(link -> link.getUrl().startsWith("/admin"))
                    .collect(Collectors.toList());
        } else if (userId != null) {
            accessibleLinks = allLinks.stream()
                    .filter(link -> link.getUrl().startsWith("/user"))
                    .collect(Collectors.toList());
        } else {
            // 잘못된 세션일 경우 빈 목록 반환
            accessibleLinks = new ArrayList<>();
        }

        // 검색어에 따라 링크 목록 필터링
        List<LinkDTO> filteredLinks;
        if (query == null || query.trim().isEmpty()) {
            filteredLinks = accessibleLinks;
        } else {
            String lowerQuery = query.trim().toLowerCase();
            filteredLinks = accessibleLinks.stream()
                    .filter(link -> link.getText().toLowerCase().contains(lowerQuery))
                    .collect(Collectors.toList());
        }

        model.addAttribute("query", query);
        model.addAttribute("links", filteredLinks);

        return "user/search/search";
    }
}
