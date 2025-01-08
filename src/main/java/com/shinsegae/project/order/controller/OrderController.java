package com.shinsegae.project.order.controller;

import com.shinsegae.project.order.service.OrderService;
import com.shinsegae.project.order.vo.OrderManagementDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("user/order")
@AllArgsConstructor
public class  OrderController {

    private final OrderService orderService;


    @GetMapping("do_outgoing")
    public String do_outgoing(Model model) {
        List<OrderManagementDTO> list = orderService.selectOutputTable();
        model.addAttribute("tableData", list);


        return "user/order/do_outgoing";
    }

    @GetMapping("confirm_outgoing_list")
    public String confirm_outgoing_list() {
        return "user/order/confirm_outgoing_list";
    }
}