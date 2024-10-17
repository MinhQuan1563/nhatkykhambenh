package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.model.TaiKhoan;
import com.nhom27.nhatkykhambenh.service.implementation.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class TaiKhoanController {
    @Autowired
    private TaiKhoanService taikhoanService;
//    @GetMapping("/register")
//    public String register(Model model, @ModelAttribute("TaiKhoan")TaiKhoan taiKhoan) {
//        //taikhoanService.saveTaiKhoan();
//        return "redirect:/home";
//    }
}
