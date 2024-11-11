package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.dto.NguoiDungDTO;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import com.nhom27.nhatkykhambenh.model.TaiKhoan;
import com.nhom27.nhatkykhambenh.service.implementation.NguoiDungService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NguoiDungController {

    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping("/users/themnguoithan")
    public String formThemNguoiThan(Model model) {
        NguoiDungDTO nguoiDungDTO = new NguoiDungDTO();

        model.addAttribute("nguoiDung", nguoiDungDTO);
        return "users/themnguoithan";
    }

    @PostMapping("/users/themnguoithan")
    public String saveThemNguoiThan(@ModelAttribute("nguoiDung")NguoiDung nguoiDung, HttpSession session) {
        TaiKhoan tk = (TaiKhoan)session.getAttribute("taikhoan");
        NguoiDung nd = nguoiDung;
        //NguoiDung nguoiDung=new NguoiDung();
        return "redirect:/";
    }
}
