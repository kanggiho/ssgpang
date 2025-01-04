package com.shinsegae.project.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/order")
public class OrderAdminController {

    @GetMapping("manage_outgoing_request")
    public String manage_outgoing_request() {
        return "admin/order/manage_outgoing_request";
    }
}