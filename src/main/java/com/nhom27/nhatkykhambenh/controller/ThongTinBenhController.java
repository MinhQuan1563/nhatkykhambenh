package com.nhom27.nhatkykhambenh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThongTinBenhController {
    @GetMapping("users/danhsachbenh")
    public String danhSachBenh() {
        return "users/danhsachbenh";
    }

    @GetMapping("users/chitietbenh")
    public String chiTietBenh() {
        return "users/chitietbenh";
    }

    @GetMapping("users/thongtinbenh")
    public String thongTinBenh() {
        return "users/thongtinbenh";
    }
}
