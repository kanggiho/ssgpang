package com.shinsegae.project.order.controller;

import com.shinsegae.project.order.service.OrderService;
import com.shinsegae.project.order.vo.OrderManagementDTO;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        model.addAttribute("userId", session.getAttribute("userId"));
        return "admin/order/manage_outgoing_request";
    }
}