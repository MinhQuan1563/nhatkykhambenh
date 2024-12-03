package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.dto.TongQuanDTO;
import com.nhom27.nhatkykhambenh.mapper.TongQuanMapper;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import com.nhom27.nhatkykhambenh.model.TongQuan;
import com.nhom27.nhatkykhambenh.service.interfaces.INguoiDungService;
import com.nhom27.nhatkykhambenh.service.interfaces.ITongQuanService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TongQuanController {

    @Autowired
    private ITongQuanService tongQuanService;

    @Autowired
    private TongQuanMapper tongQuanMapper;

    @Autowired
    private INguoiDungService nguoiDungService;

    @GetMapping("users/tongquan")
    public String GetAllTongQuan(Model model, HttpSession session,
                    @RequestParam("maNguoiDung") Integer maNguoiDung) {

        List<String> pageName = new ArrayList<>();
        pageName.add("Tổng quan");

        NguoiDung nguoiDung = nguoiDungService.getById(maNguoiDung);
        session.setAttribute("nguoidung", nguoiDung);

        TongQuan tongQuan = tongQuanService.findByNguoiDung(nguoiDung.getMaNguoiDung());
        TongQuanDTO tongQuanDTO = tongQuanMapper.toTongQuanDTO(tongQuan);
        String[][] dsChiSo = tongQuanService.updateChiSoForTongQuan(tongQuan);

        session.setAttribute("pageName", pageName);

        model.addAttribute("tongquan", tongQuanDTO);
        model.addAttribute("dsChiSo", dsChiSo);

        return "users/tongquan";
    }
}
