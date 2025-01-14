package com.shinsegae.project.search.controller;

import com.shinsegae.project.search.vo.LinkDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/search")
public class SearchController {

    // 모든 가능한 링크 목록을 정의
    private List<LinkDTO> allLinks = List.of(
            new LinkDTO("/admin/incoming/do_incoming", "입고하기"),
            new LinkDTO("/admin/incoming/confirm_incoming_list", "입고내역 확인"),
            new LinkDTO("/admin/inventory/confirm_inventory_list", "재고 확인"),
            new LinkDTO("/admin/inventory/edit_inventory", "재고 수정"),
            new LinkDTO("/admin/order/manage_outgoing_request", "발주 요청 관리"),
            new LinkDTO("/admin/map/map", "창고별 위치"),
            new LinkDTO("/admin/map/map", "게시판"),
            new LinkDTO("/user/order/do_outgoing", "발주하기"),
            new LinkDTO("/user/order/confirm_outgoing_list", "발주내역 확인"),
            new LinkDTO("/user/member/logout", "로그인"),
            new LinkDTO("/user/member/info", "회원정보"),
            new LinkDTO ("admin/home_admin", "관리자 대시보드"),
            new LinkDTO ("user/home", "사용자 대시보드")
    );

    @GetMapping("/search")
    public String searchPage(@RequestParam(value = "query", required = false) String query, Model model) {
        // 검색어에 따라 링크 목록 필터링
        List<LinkDTO> filteredLinks;
        if(query == null || query.trim().isEmpty()) {
            // 검색어가 없으면 전체 목록 반환 (또는 빈 목록 반환 가능)
            filteredLinks = allLinks;
        } else {
            String lowerQuery = query.trim().toLowerCase();
            filteredLinks = allLinks.stream()
                    .filter(link -> link.getText().toLowerCase().contains(lowerQuery))
                    .collect(Collectors.toList());
        }

        model.addAttribute("query", query);
        model.addAttribute("links", filteredLinks);

        return "admin/search/search"; //
    }
}
