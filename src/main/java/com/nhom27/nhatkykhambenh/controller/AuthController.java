package com.nhom27.nhatkykhambenh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("admin")
    public String home() {
        return "admin/dashboard";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @GetMapping("/users/thongtinkhac")
    public String getThongTinKhac(Model model) {
        return "users/thongtinkhac";
    }

    @GetMapping("/users/themnguoithan")
    public String formThemNguoiThan(Model model) {
        return "users/themnguoithan";
    }
}
