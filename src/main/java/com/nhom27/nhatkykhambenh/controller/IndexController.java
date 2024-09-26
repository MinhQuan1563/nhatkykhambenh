package com.nhom27.nhatkykhambenh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping()
    public String home() {
//        return "admin/dashboard";
        return "users/khambenh";
    }

    @GetMapping("users/home2")
    public String home2() {
//        return "admin/dashboard";
        return "users/chitietkhambenh";
    }

    @GetMapping("users/thongtinbenh")
    public String thongTinBenh() {
//        return "admin/dashboard";
        return "users/thongtinbenh";
    }
}