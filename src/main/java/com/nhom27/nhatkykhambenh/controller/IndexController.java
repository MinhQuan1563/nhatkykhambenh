package com.nhom27.nhatkykhambenh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping()
    public String home(Model theModel) {
        return "home";
    }

    @GetMapping("base")
    public String base(Model theModel) {
        return "base";
    }
}