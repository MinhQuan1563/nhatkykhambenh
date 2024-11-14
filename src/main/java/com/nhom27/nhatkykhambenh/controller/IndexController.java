package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.dto.NguoiDungDTO;
import com.nhom27.nhatkykhambenh.mapper.NguoiDungMapper;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import com.nhom27.nhatkykhambenh.model.TaiKhoan;
import com.nhom27.nhatkykhambenh.service.implementation.NguoiDungService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private NguoiDungService nguoiDungService;

    @Autowired
    private NguoiDungMapper nguoiDungMapper;

    @GetMapping()
    public String home(Model theModel, HttpSession session) {
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("taikhoan");
        List<NguoiDung> dsNguoiDung = nguoiDungService.getDsNguoiDungByGiaDinh(taiKhoan.getGiaDinh());
        List<NguoiDungDTO> dsNguoiDungDTO = nguoiDungMapper.toNguoiDungDtoList(dsNguoiDung);

        if(dsNguoiDungDTO.size() > 0) {
            theModel.addAttribute("dsNguoiDung", dsNguoiDungDTO);
        }

//        return "home";
        return "users/danhsachnguoidung";
    }

    @GetMapping("admin")
    public String home() {
        return "admin/dashboard";
    }

    @GetMapping("base")
    public String base(Model theModel) {
        return "base";
    }
}