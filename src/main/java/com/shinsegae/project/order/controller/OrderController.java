package com.shinsegae.project.order.controller;

import com.shinsegae.project.order.service.OrderService;
import com.shinsegae.project.order.vo.OrderInventoryManagementDTO;
import com.shinsegae.project.order.vo.OrderManagementDTO;
import com.shinsegae.project.order.vo.OutputVO;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user/order")
@AllArgsConstructor
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
    public String do_outgoing(Model model) {
        List<OrderInventoryManagementDTO> list_confirm = orderService.selectOutputTable();
        model.addAttribute("tableDataConfirm", list_confirm);


        return "user/order/do_outgoing";
    }

    @GetMapping("confirm_outgoing_list")
    public String confirm_outgoing_list(Model model, HttpSession session) {
        List<OrderManagementDTO> list = orderService.selectOutputConfirmTable();
        model.addAttribute("tableData", list);
        model.addAttribute("userId", session.getAttribute("userId"));


        return "user/order/confirm_outgoing_list";
    }

    @PostMapping("/save")
    public String saveOutput(@RequestBody OutputVO outputVO, HttpSession session) {
        orderService.saveOutput(outputVO,session);
        return "redirect:/user/order/do_outgoing";
    }

    @PostMapping("/cancelOrder")
    @ResponseBody
    public String cancelOrder(@RequestBody CancelRequest request) {
        String outputId = request.getOutputId();
        String productName = request.getProductName();
        String releaseQuantity = request.getReleaseQuantity();
        orderService.cancelOrder(outputId,productName,releaseQuantity);
        // 취소 처리 완료 후 메시지나 상태를 return
        return "취소 완료";
    }


}