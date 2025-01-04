package com.shinsegae.project.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("user/home")
    public String home() {
        return "user/home";
    }

    @GetMapping("admin/home_admin")
    public String home_admin() {
        return "admin/home_admin";
    }
}