package com.shinsegae.project.dashboard.controller;

import com.shinsegae.project.dashboard.service.DashboardService;
import com.shinsegae.project.dashboard.vo.BarChartDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("user/home")
    public String home(HttpSession session, Model model) {
        session.setAttribute("id", "giho");
        return "user/home";
    }

    @GetMapping("admin/home_admin")
    public String home_admin(Model model) {
        model.addAttribute("totalInventory", dashboardService.selectTotalInventory());
        model.addAttribute("inputQuantity", dashboardService.selectInputQuantity());
        model.addAttribute("outputQuantity", dashboardService.selectOutputQuantity());
        model.addAttribute("outputStatus", dashboardService.selectOutputByStatus());

        return "admin/home_admin";
    }

    @GetMapping("admin/home_admin/chart1")
    @ResponseBody
    public ResponseEntity<List<BarChartDTO>> selectBarChartData() {
        List<BarChartDTO> barChartData = dashboardService.selectBarChartData();
        return ResponseEntity.ok(barChartData);
    }
}