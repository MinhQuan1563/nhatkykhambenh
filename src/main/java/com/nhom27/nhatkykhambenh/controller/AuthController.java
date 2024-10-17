package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.model.GiaDinh;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import com.nhom27.nhatkykhambenh.model.TaiKhoan;
import com.nhom27.nhatkykhambenh.service.implementation.GiaDinhService;
import com.nhom27.nhatkykhambenh.service.implementation.NguoiDungService;
import com.nhom27.nhatkykhambenh.service.implementation.TaiKhoanService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired
    private TaiKhoanService taikhoanService;
    @Autowired
    private GiaDinhService giaDinhService;
    @Autowired
    private NguoiDungService nguoiDungService;
    @GetMapping("admin")
    public String home() {
        return "admin/dashboard";
    }

    @GetMapping("/login")
    public String login(Model model) {
        TaiKhoan taiKhoan=new TaiKhoan();
        model.addAttribute("taiKhoan", taiKhoan);
        return "login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute("taiKhoan") TaiKhoan taiKhoan, HttpSession session) {
        TaiKhoan tk=taikhoanService.findByTaiKhoan(taiKhoan.getSoDienThoai(),taiKhoan.getMatKhau());
        if (tk==null){
            return "login";
        }
        session.setAttribute("taiKhoan", tk);
        return "redirect:/";
    }
    @GetMapping("/register")
    public String register(Model model) {
        TaiKhoan taikhoan = new TaiKhoan();
        model.addAttribute("taikhoan", taikhoan);
        return "register";
    }
    @PostMapping("/register")
    public String SaveTaiKhoan(@ModelAttribute("taikhoan")TaiKhoan taikhoan, HttpSession session) {
        GiaDinh giaDinh=new GiaDinh();
        giaDinhService.saveGiaDinh(giaDinh);
        taikhoan.setGiaDinh(giaDinh);
        taikhoanService.saveTaiKhoan(taikhoan);
        NguoiDung nguoiDung=new NguoiDung();
        nguoiDung.setSoDienThoai(taikhoan.getSoDienThoai());
        nguoiDung.setTenNguoiDung(taikhoan.getTaiKhoan());
        nguoiDung.setMaNguoiDung(taikhoan.getMaNguoiDung());
        nguoiDungService.saveNguoiDung(nguoiDung);
        session.setAttribute("nguoidung", nguoiDung);
        return "redirect:/";
    }
    @GetMapping("/users/thongtinkhac")
    public String getThongTinKhac(Model model,HttpSession session) {
        NguoiDung nguoiDung =(NguoiDung)session.getAttribute("nguoidung");
        return "users/thongtinkhac";
    }

//    @GetMapping("/users/themnguoithan")
//    public String formThemNguoiThan(Model model) {
//        return "users/themnguoithan";
//    }
}
