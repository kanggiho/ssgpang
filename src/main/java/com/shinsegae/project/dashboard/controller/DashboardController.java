package com.shinsegae.project.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("user/home")
    public String home() {
        return "user/home";
    }
    //

    @GetMapping("admin/home_admin")
    public String home_admin() {
        return "admin/home_admin";
    }
}