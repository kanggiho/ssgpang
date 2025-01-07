package com.shinsegae.project.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/order")
public class  OrderController {

    @GetMapping("do_outgoing")
    public String do_outgoing() {
        return "user/order/do_outgoing";
    }

    @GetMapping("confirm_outgoing_list")
    public String confirm_outgoing_list() {
        return "user/order/confirm_outgoing_list";
    }
}