package com.shinsegae.project.dashboard.controller;

import com.shinsegae.project.dashboard.service.DashboardService;
import com.shinsegae.project.dashboard.vo.*;
import com.shinsegae.project.order.vo.OrderManagementDTO;
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

    //사용자 대시보드
    @GetMapping("user/home")
    public String home(HttpSession session, Model model) {
        session.setAttribute("id", "giho");
        //상단 카드: 발주 승인대기, 승인완료, 승인거절, 총발주건수, 총 발주비용
        model.addAttribute("statusWaiting", dashboardService.selectOutputByStatusWaiting());
        model.addAttribute("statusApproval", dashboardService.selectOutputByStatusApproval());
        model.addAttribute("statusReject", dashboardService.selectOutputByStatusReject());
        model.addAttribute("totalOutputQuantity", dashboardService.selectTotalOutputQuantity());
        model.addAttribute("totalOutputPrice", dashboardService.selectTotalOutputPrice());
        return "user/home";
    }

    //bar chart: 월별 발주 현황(발주량, 발주금액)
    @GetMapping("user/home/barchart_output")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> barchart_output(HttpSession session) {
        //String userId = session.getAttribute("id").toString();
        List<UserBarChartQuantityDTO> UserOutputQuantity = dashboardService.selectUserOutputQuantity();
        List<UserBarChartPriceDTO> UserOutputPrice= dashboardService.selectUserOutputPrice();
        Map<String, Object> result = new HashMap<>();
        result.put("UserOutputQuantity", UserOutputQuantity);
        result.put("UserOutputPrice", UserOutputPrice);
        return ResponseEntity.ok(result);
    }


    //pie chart: 나의 발주정보
    @GetMapping("user/home/pieChart_output")
    @ResponseBody
    public ResponseEntity<List<UserPieChartDTO>> pieChart_output() {
        List<UserPieChartDTO> userPieChartData = dashboardService.selectUserPieChartData();
        return ResponseEntity.ok(userPieChartData);
    }

    //관리자 대시보드
    //상단 카드: 총재고량, 입고수량, 출고수량, 발주 승인요청
    @GetMapping("admin/home_admin")
    public String home_admin(Model model) {
        model.addAttribute("totalInventory", dashboardService.selectTotalInventory());
        model.addAttribute("inputQuantity", dashboardService.selectInputQuantity());
        model.addAttribute("outputQuantity", dashboardService.selectOutputQuantity());
        model.addAttribute("outputStatus", dashboardService.selectOutputByStatus());
        //table: 오늘의 입고 상품
        List<TodayInputDTO> list = dashboardService.selectTodayInputProduct();
        model.addAttribute("inputList", list);
        return "admin/home_admin";
    }

    //area chart: 월별 입출고 현황
    @GetMapping("admin/home_admin/areaChart")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> areaChart() {
        List<AreaChartInputDTO> areaChartInputData = dashboardService.selectAreaChartInputData();
        List<AreaChartOutputDTO> areaChartOutputData= dashboardService.selectAreaChartOutputData();
        Map<String, Object> result = new HashMap<>();
        result.put("areaChartInputData", areaChartInputData);
        result.put("areaChartOutputData", areaChartOutputData);
        return ResponseEntity.ok(result);
    }

    //bar chart: 지점별 출고 현황
    @GetMapping("admin/home_admin/barchart_user_output")
    @ResponseBody
    public ResponseEntity<List<BarChartDTO>> barchart_user_output() {
        List<BarChartDTO> barChartData = dashboardService.selectBarChartData();
        return ResponseEntity.ok(barChartData);
    }
}