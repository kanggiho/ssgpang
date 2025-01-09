package com.shinsegae.project.dashboard.controller;

import com.shinsegae.project.dashboard.service.DashboardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("user/home")
    public String home(HttpSession session, Model model) {
        session.setAttribute("id","giho");
        return "user/home";
    }

    @GetMapping("admin/home_admin")
    public String home_admin(Model model) {
        model.addAttribute("totalInventory",dashboardService.selectTotalInventory());
        model.addAttribute("inputQuantity",dashboardService.selectInputQuantity());
        model.addAttribute("outputQuantity",dashboardService.selectOutputQuantity());
        model.addAttribute("outputStatus",dashboardService.selectOutputByStatus());
        return "admin/home_admin";
    }
}