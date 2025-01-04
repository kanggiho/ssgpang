package com.shinsegae.project.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class HomeAdminController {

    @GetMapping("home")
    public String home() {
        return "admin/home"; // 슬래시 제거
    }
}