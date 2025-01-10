package com.shinsegae.project.dashboard.controller;

import com.shinsegae.project.dashboard.service.DashboardService;
import com.shinsegae.project.dashboard.vo.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    //사용자
    @GetMapping("user/home")
    public String home(HttpSession session, Model model) {
        session.setAttribute("id", "giho");
        model.addAttribute("statusWaiting", dashboardService.selectOutputByStatusWaiting());
        model.addAttribute("statusApproval", dashboardService.selectOutputByStatusApproval());
        model.addAttribute("statusReject", dashboardService.selectOutputByStatusReject());
        model.addAttribute("totalOutputQuantity", dashboardService.selectTotalOutputQuantity());
        model.addAttribute("totalOutputCost", dashboardService.selectTotalOutputPrice());
        List<UserPieChartDTO> userPieChartData = dashboardService.selectUserPieChartData();
        model.addAttribute("userPieChartData", userPieChartData);

        return "user/home";
    }

    //bar chart
    @GetMapping("user/home/chart1")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> selectUserBarChartData() {
        List<UserBarChartQuantityDTO> UserOutputQuantity = dashboardService.selectUserOutputQuantity();
        List<UserBarChartPriceDTO> UserOutputPrice= dashboardService.selectUserOutputPrice();

        Map<String, Object> result = new HashMap<>();
        result.put("UserOutputQuantity", UserOutputQuantity);
        result.put("UserOutputPrice", UserOutputPrice);
        return ResponseEntity.ok(result);
    }

    //pie chart
    @GetMapping("user/home/chart3")
    @ResponseBody
    public ResponseEntity<List<UserPieChartDTO>> selectUserPieChartData() {
        List<UserPieChartDTO> userPieChartData = dashboardService.selectUserPieChartData();
        return ResponseEntity.ok(userPieChartData);
    }

    //관리자
    @GetMapping("admin/home_admin")
    public String home_admin(Model model) {
        model.addAttribute("totalInventory", dashboardService.selectTotalInventory());
        model.addAttribute("inputQuantity", dashboardService.selectInputQuantity());
        model.addAttribute("outputQuantity", dashboardService.selectOutputQuantity());
        model.addAttribute("outputStatus", dashboardService.selectOutputByStatus());
        return "admin/home_admin";
    }
    //Area chart
    @GetMapping("admin/home_admin/chart1")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> selectAreaChartData() {
        List<AreaChartInputDTO> areaChartInputData = dashboardService.selectAreaChartInputData();
        List<AreaChartOutputDTO> areaChartOutputData= dashboardService.selectAreaChartOutputData();

        Map<String, Object> result = new HashMap<>();
        result.put("areaChartInputData", areaChartInputData);
        result.put("areaChartOutputData", areaChartOutputData);

        return ResponseEntity.ok(result);
    }
    //Bar chart
    @GetMapping("admin/home_admin/chart3")
    @ResponseBody
    public ResponseEntity<List<BarChartDTO>> selectBarChartData() {
        List<BarChartDTO> barChartData = dashboardService.selectBarChartData();
        return ResponseEntity.ok(barChartData);
    }
}