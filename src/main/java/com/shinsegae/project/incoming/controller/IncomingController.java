package com.shinsegae.project.incoming.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/incoming")
public class IncomingController {

    @GetMapping("do_incoming")
    public String do_incoming() {
        return "admin/incoming/do_incoming";
    }

    @GetMapping("confirm_incoming_list")
    public String confirm_incoming_list() {
        return "admin/incoming/confirm_incoming_list";
    }

}