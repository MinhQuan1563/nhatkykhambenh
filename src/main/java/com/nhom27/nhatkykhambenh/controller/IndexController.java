package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.dto.NguoiDungDTO;
import com.nhom27.nhatkykhambenh.mapper.NguoiDungMapper;
import com.nhom27.nhatkykhambenh.model.TaiKhoan;
import com.nhom27.nhatkykhambenh.service.interfaces.INguoiDungService;
import com.nhom27.nhatkykhambenh.service.interfaces.ITaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private INguoiDungService nguoiDungService;

    @Autowired
    private ITaiKhoanService taiKhoanService;

    @Autowired
    private NguoiDungMapper nguoiDungMapper;

    @GetMapping()
    public String home(Model model) {
        TaiKhoan taiKhoan = taiKhoanService.getCurrentUser();

        List<NguoiDungDTO> dsNguoiDungDTO = nguoiDungMapper.toNguoiDungDtoList(
            nguoiDungService.getDsNguoiDungByGiaDinh(taiKhoan.getGiaDinh())
        );

        if(dsNguoiDungDTO.size() > 0) {
            model.addAttribute("dsNguoiDung", dsNguoiDungDTO);
        }

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