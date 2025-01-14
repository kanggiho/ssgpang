package com.shinsegae.project.order.controller;

import com.shinsegae.project.order.service.OrderService;
import com.shinsegae.project.order.vo.OrderInventoryManagementDTO;
import com.shinsegae.project.order.vo.OrderManagementDTO;
import com.shinsegae.project.order.vo.OutputVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user/order")
@AllArgsConstructor
@Tag(name = "사용자 재고처리")
public class  OrderController {

    private final OrderService orderService;

    public static class CancelRequest {
        private String outputId;
        private String productName;
        private String releaseQuantity;
        public String getOutputId() { return outputId; }
        public void setOutputId(String outputId) { this.outputId = outputId; }
        public String getProductName() { return productName; }
        public void setProductName(String productName) { this.productName = productName; }
        public String getReleaseQuantity() { return releaseQuantity; }
        public void setReleaseQuantity(String releaseQuantity) { this.releaseQuantity = releaseQuantity; }
    }




    @GetMapping("do_outgoing")
    @Operation(summary = "발주하기 페이지", description = "발주 가능한 재고 데이터를 조회하여 발주하기 페이지를 반환")
    public String do_outgoing(Model model) {
        List<OrderInventoryManagementDTO> list_confirm = orderService.selectOutputTable();
        model.addAttribute("tableDataConfirm", list_confirm);


        return "user/order/do_outgoing";
    }

    @GetMapping("confirm_outgoing_list")
    @Operation(summary = "발주 내역 확인 페이지", description = "발주 요청 데이터를 조회하여 발주 내역 확인 페이지를 반환")
    public String confirm_outgoing_list(Model model, HttpSession session) {
        List<OrderManagementDTO> list = orderService.selectOutputConfirmTable();
        model.addAttribute("tableData", list);
        model.addAttribute("userId", session.getAttribute("userId"));


        return "user/order/confirm_outgoing_list";
    }

    @PostMapping("/save")
    @Operation(summary = "발주 저장 처리", description = "입력된 데이터를 기반으로 발주 요청을 저장")
    public String saveOutput(@RequestBody OutputVO outputVO, HttpSession session) {
        orderService.saveOutput(outputVO,session);
        return "redirect:/user/order/do_outgoing";
    }

    @PostMapping("/cancelOrder")
    @ResponseBody
    @Operation(summary = "발주 취소 처리", description = "입력된 데이터를 기반으로 발주 요청을 취소")
    public String cancelOrder(@RequestBody CancelRequest request) {
        String outputId = request.getOutputId();
        String productName = request.getProductName();
        String releaseQuantity = request.getReleaseQuantity();
        orderService.cancelOrder(outputId,productName,releaseQuantity);
        // 취소 처리 완료 후 메시지나 상태를 return
        return "취소 완료";
    }


}