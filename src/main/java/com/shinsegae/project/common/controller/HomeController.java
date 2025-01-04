package com.shinsegae.project.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class HomeController {

    @GetMapping("home")
    public String home() {
        return "user/home"; // 슬래시 제거
    }
}