package com.shinsegae.project.dashboard.controller;

import com.shinsegae.project.dashboard.service.DashboardService;
import com.shinsegae.project.dashboard.vo.*;
import io.swagger.v3.oas.annotations.Operation;
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

    // 사용자 대시보드
    @GetMapping("user/home")
    @Operation(summary = "사용자 대시보드",
            description = "사용자의 대시보드 화면을 반환, 상단 카드에는 발주 승인대기, 승인완료, 승인거절, 총발주 건수 등의 정보를 제공"
    )
    public String home(HttpSession session, Model model) {
        String userId = session.getAttribute("userId").toString();
        model.addAttribute("statusWaiting", dashboardService.selectOutputByStatusWaiting(userId)); // 발주 승인대기
        model.addAttribute("statusApproval", dashboardService.selectOutputByStatusApproval(userId)); // 승인완료
        model.addAttribute("statusReject", dashboardService.selectOutputByStatusReject(userId)); // 승인거절
        model.addAttribute("totalOutputQuantity", dashboardService.selectTotalOutputQuantity(userId)); // 총 발주
        return "user/home";
    }

    // bar chart: 월별 발주 현황(발주량, 발주금액)
    @Operation(summary = "월별 발주 현황 대시보드",
            description = "사용자의 월별 발주량과 발주 금액 데이터를 JSON 형식으로 반환"
    )
    @GetMapping("user/home/barchart_output")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> barchart_output(HttpSession session) {
        String userId = session.getAttribute("userId").toString();
        List<UserBarChartQuantityDTO> UserOutputQuantity = dashboardService.selectUserOutputQuantity(userId); // 발주량
        List<UserBarChartPriceDTO> UserOutputPrice = dashboardService.selectUserOutputPrice(userId); // 발주금액
        Map<String, Object> result = new HashMap<>();
        result.put("UserOutputQuantity", UserOutputQuantity);
        result.put("UserOutputPrice", UserOutputPrice);
        return ResponseEntity.ok(result);
    }

    // pie chart: 나의 발주정보
    @Operation(summary = "나의 발주정보 대시보드",
            description = "사용자의 발주 정보를 파이 차트 형식으로 나타내기 위한 데이터를 JSON 형식으로 반환"
    )
    @GetMapping("user/home/pieChart_output")
    @ResponseBody
    public ResponseEntity<List<UserPieChartDTO>> pieChart_output(HttpSession session) {
        String userId = session.getAttribute("userId").toString();
        List<UserPieChartDTO> userPieChartData = dashboardService.selectUserPieChartData(userId);
        return ResponseEntity.ok(userPieChartData);
    }

    // 관리자 대시보드
    @Operation(summary = "총재고량, 입고수량, 출고수량, 발주 요청 대시보드",
            description = "관리자의 대시보드 화면을 반환, 상단 카드에는 총재고량, 입고수량, 출고수량, 발주 요청 등의 정보를 제공"
    )
    @GetMapping("admin/home_admin")
    public String home_admin(Model model, HttpSession session) {
        String adminId = session.getAttribute("adminId").toString();
        model.addAttribute("totalInventory", dashboardService.selectTotalInventory()); // 총 재고량
        model.addAttribute("inputQuantity", dashboardService.selectInputQuantity()); // 입고수량
        model.addAttribute("outputQuantity", dashboardService.selectOutputQuantity()); // 출고수량
        model.addAttribute("outputStatus", dashboardService.selectOutputByStatus()); // 발주 요청
        List<TodayInputDTO> list = dashboardService.selectTodayInputProduct();
        model.addAttribute("inputList", list);
        return "admin/home_admin";
    }

    // area chart: 월별 입출고 현황
    @Operation(summary = "월별 입출고 현황 대시보드",
            description = "월별 입출고 현황 데이터를 JSON 형식으로 반환, 입고량과 출고량 데이터를 포함"
    )
    @GetMapping("admin/home_admin/areaChart")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> areaChart() {
        List<AreaChartInputDTO> areaChartInputData = dashboardService.selectAreaChartInputData(); // 월별 입고량
        List<AreaChartOutputDTO> areaChartOutputData = dashboardService.selectAreaChartOutputData(); // 월별 출고량
        Map<String, Object> result = new HashMap<>();
        result.put("areaChartInputData", areaChartInputData);
        result.put("areaChartOutputData", areaChartOutputData);
        return ResponseEntity.ok(result);
    }

    // bar chart: 지점별 출고 현황
    @Operation(summary = "지점별 출고 현황",
            description = "각 지점의 출고 현황 데이터를 JSON 형식으로 반환"
    )
    @GetMapping("admin/home_admin/barchart_user_output")
    @ResponseBody
    public ResponseEntity<List<BarChartDTO>> barchart_user_output() {
        List<BarChartDTO> barChartData = dashboardService.selectBarChartData();
        return ResponseEntity.ok(barChartData);
    }
}
