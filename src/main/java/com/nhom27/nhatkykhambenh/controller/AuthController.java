package com.nhom27.nhatkykhambenh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("admin")
    public String home() {
//        return "admin/dashboard";
        return "admin/dashboard";
    }
}
