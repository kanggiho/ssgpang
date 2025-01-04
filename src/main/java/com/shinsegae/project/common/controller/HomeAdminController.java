package com.shinsegae.project.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class HomeAdminController {

    @GetMapping("home_admin")
    public String home() {
        return "admin/home_admin";
    }
}