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

//    private void populateTongQuanModel(Model model, int page, int size, String query) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<TongQuanDTO> tongQuanPage = tongQuanService.getDSTongQuan(pageable, query);
//
//        System.out.println(tongQuanPage.getTotalElements());
//
//        model.addAttribute("dsTongQuan", tongQuanPage.getContent());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("pageSize", size);
//        model.addAttribute("totalPages", tongQuanPage.getTotalPages());
//        model.addAttribute("totalItems", tongQuanPage.getTotalElements());
//        model.addAttribute("query", query);
//    }

    @GetMapping("users/tongquan")
    public String GetAllTongQuan(Model model, HttpSession session,
                    @RequestParam("maNguoiDung") Integer maNguoiDung) {

        List<String> pageName = new ArrayList<>();
        pageName.add("Tổng quan");

        NguoiDung nguoiDung = nguoiDungService.getById(maNguoiDung);
        session.setAttribute("nguoidung", nguoiDung);

        TongQuan tongQuan = tongQuanService.findByNguoiDung(nguoiDung.getMaNguoiDung());
        TongQuanDTO tongQuanDTO = tongQuanMapper.toTongQuanDTO(tongQuan);
        String[][] chiSo = tongQuanService.updateChiSoForTongQuan(tongQuan);

        session.setAttribute("pageName", pageName);

        model.addAttribute("tongquan", tongQuanDTO);
        model.addAttribute("chiso", chiSo);

        return "users/tongquan";
    }

//    @GetMapping("/admin/tongquan")
//    public String showTongQuan(Model model,
//                               @RequestParam(defaultValue = "0") int page,
//                               @RequestParam(defaultValue = "5") int size,
//                               @RequestParam(defaultValue = "") String query) {
//
//        populateTongQuanModel(model, page, size, query);
//
//        return "admin/listTongQuan";
//    }
}
