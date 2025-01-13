package com.shinsegae.project.order.controller;

import com.shinsegae.project.order.service.OrderService;
import com.shinsegae.project.order.vo.OrderManagementDTO;
import com.shinsegae.project.order.vo.HandleDTO;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("handle")
    public ResponseEntity<String> handleOrder(@RequestBody HandleDTO request, HttpSession session) {
        int adminId = Integer.parseInt((String) session.getAttribute("adminId"));

        try {
            if ("approve".equals(request.getActionType())) {
                orderService.approveOrder(request.getOutputId(), adminId);
            } else if ("reject".equals(request.getActionType())) {
                orderService.rejectOrder(request.getOutputId());
            } else {
                return ResponseEntity.badRequest().body("INVALID_ACTION");
            }
            return ResponseEntity.ok("SUCCESS");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("ERROR: " + e.getMessage());
        }
    }



}