package com.nhom27.nhatkykhambenh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TongQuanController {

    @GetMapping("/admin/tongquan")
    public String GetAllTongQuan() {
        return "admin/listTongQuan";
    }

    @GetMapping("users/tongquan")
    public String showTongQuan() {
        return "users/tongquan";
    }

    @GetMapping("users/chitiettongquan")
    public String showChiTietTongQuan() {
        return "users/chitiettongquan";
    }
}
