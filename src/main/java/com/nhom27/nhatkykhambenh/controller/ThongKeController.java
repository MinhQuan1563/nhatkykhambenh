package com.nhom27.nhatkykhambenh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ThongKeController {
    @GetMapping("/admin/dashboard")
    public String Dashboard() {
        return "admin/thongke";
    }
}
