package com.shinsegae.project.dashboard.controller;

import com.shinsegae.project.dashboard.service.DashboardService;
import com.shinsegae.project.dashboard.vo.AreaChartInputDTO;
import com.shinsegae.project.dashboard.vo.AreaChartOutputDTO;
import com.shinsegae.project.dashboard.vo.BarChartDTO;
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

    @GetMapping("admin/home_admin/chart2")
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