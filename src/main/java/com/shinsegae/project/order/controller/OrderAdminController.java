package com.shinsegae.project.order.controller;

import com.shinsegae.project.order.service.OrderService;
import com.shinsegae.project.order.vo.OrderManagementDTO;
import com.shinsegae.project.order.vo.HandleDTO;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/order")
@AllArgsConstructor
public class OrderAdminController {

    private final OrderService orderService;

    @GetMapping("manage_outgoing_request")
    public String manage_outgoing_request(Model model, HttpSession session) {
        List<OrderManagementDTO> list = orderService.selectOutputConfirmTable();
        model.addAttribute("tableData", list);
        model.addAttribute("adminId", session.getAttribute("adminId"));
        return "admin/order/manage_outgoing_request";
    }

    @PostMapping("/handle")
    public String handleOrder(@RequestBody HandleDTO request, HttpSession session) {
        String adminId = (String) session.getAttribute("adminId");

        if ("approve".equals(request.getActionType())) {
            //orderService.approveOrder(request.getOrderId(), adminId);
        } else if ("reject".equals(request.getActionType())) {
            //orderService.rejectOrder(request.getOrderId());
        } else {
            return "INVALID_ACTION";
        }
        return "SUCCESS";
    }



}