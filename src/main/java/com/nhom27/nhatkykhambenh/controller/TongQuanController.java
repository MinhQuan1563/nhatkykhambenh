package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.dto.TongQuanDTO;
import com.nhom27.nhatkykhambenh.model.TongQuan;
import com.nhom27.nhatkykhambenh.service.implementation.TongQuanService;
import com.nhom27.nhatkykhambenh.utils.ModelUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TongQuanController {

    @Autowired
    private TongQuanService tongQuanService;

    private void populateTongQuanModel(Model model, int page, int size, String query) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TongQuanDTO> tongQuanPage = tongQuanService.getDSTongQuan(pageable, query);

        System.out.println(tongQuanPage.getTotalElements());

        for (TongQuanDTO tongQuanDTO : tongQuanPage.getContent()) {
            System.out.println("id: " + tongQuanDTO.getMaTongQuan());
        }

        model.addAttribute("dsTongQuan", tongQuanPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", tongQuanPage.getTotalPages());
        model.addAttribute("totalItems", tongQuanPage.getTotalElements());
        model.addAttribute("query", query);
    }

    @GetMapping("/admin/tongquan")
    public String GetAllTongQuan(Model model,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "5") int size,
                                 @RequestParam(defaultValue = "") String query) {

        populateTongQuanModel(model, page, size, query);

        return "admin/listTongQuan";
    }

    @GetMapping("users/tongquan")
    public String showTongQuan(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size,
                               @RequestParam(defaultValue = "") String query) {

        populateTongQuanModel(model, page, size, query);

        return "users/tongquan";
    }

    @GetMapping("users/chitiettongquan")
    public String showChiTietTongQuan() {
        return "users/chitiettongquan";
    }
}
