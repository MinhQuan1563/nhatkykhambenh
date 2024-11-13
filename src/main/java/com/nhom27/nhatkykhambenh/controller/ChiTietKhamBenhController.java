package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.dto.ChiTietKhamBenhDTO;
import com.nhom27.nhatkykhambenh.exception.SaveDataException;
import com.nhom27.nhatkykhambenh.service.implementation.ChiTietKhamBenhService;
import com.nhom27.nhatkykhambenh.service.implementation.ChiTietKhamBenhService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChiTietKhamBenhController {
    @Autowired
    private ChiTietKhamBenhService chiTietKhamBenhService;

    @GetMapping("/admin/chitietkhambenh")
    public String GetListChiTietKhamBenh(Model model,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "5") int size,
                                         @RequestParam(defaultValue = "") String query) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ChiTietKhamBenhDTO> chitietkhamBenhPage = chiTietKhamBenhService.getDSChiTietKhamBenh(pageable,query);
        int count = 0;

        model.addAttribute("dsChiTietKhamBenh", chitietkhamBenhPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", chitietkhamBenhPage.getTotalPages());
        model.addAttribute("totalItems", chitietkhamBenhPage.getTotalElements());

        model.addAttribute("query", query);

        int startItem = page * size + 1;
        int endItem = Math.min(startItem + size - 1, (int) chitietkhamBenhPage.getTotalElements());

        model.addAttribute("startItem", startItem);
        model.addAttribute("endItem", endItem);
        model.addAttribute("currentCount", endItem - startItem + 1);

        System.out.println("currentPage: " + page);
        System.out.println("currentPage: " + size);
        System.out.println("currentPage: " + chitietkhamBenhPage.getTotalPages());
        System.out.println("currentPage: " + chitietkhamBenhPage.getTotalElements());
        System.out.println("currentPage: " + query);
        return "admin/chitietkhambenh/listChiTietKhamBenh";
    }

    @GetMapping("/admin/chitietkhambenh/add")
    public String addChiTietKhamBenhForm(Model model) {
        ChiTietKhamBenhDTO donThuocDTO = new ChiTietKhamBenhDTO();
        model.addAttribute("chitietkhambenh", donThuocDTO);
        return "admin/chitietkhambenh/addChiTietKhamBenh";
    }


    @GetMapping("/admin/chitietkhambenh/update")
    public String updateChiTietKhamBenhForm(@RequestParam("id") Integer id, Model model) {
        ChiTietKhamBenhDTO donThuocDTO = chiTietKhamBenhService.findById(id);
        model.addAttribute("chitietkhambenh", donThuocDTO);
        return "admin/chitietkhambenh/addChiTietKhamBenh";
    }

    @PostMapping("/admin/chitietkhambenh/save")
    public String saveChiTietKhamBenh(@ModelAttribute("chitietkhambenh") ChiTietKhamBenhDTO chitietkhambenh,
                                      BindingResult bindingResult,
                                      Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/chitietkhambenh/addChiTietKhamBenh";
        }
        try {
            chiTietKhamBenhService.saveChiTietKhamBenh(chitietkhambenh);
            return "redirect:/admin/chitietkhambenh";
        } catch (SaveDataException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/chitietkhambenh/addChiTietKhamBenh";
        }
    }

    @PostMapping("/admin/chitietkhambenh/delete")
    public String deleteChiTietKhamBenh(@RequestParam("maChiTietKhamBenh") Integer maChiTietKhamBenh, RedirectAttributes redirectAttributes) {
        try {
            chiTietKhamBenhService.deleteById(maChiTietKhamBenh);
            redirectAttributes.addFlashAttribute("success", "Xóa thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi!! Xóa thông tin Đơn Thuốc thất bại");
        }
        return "redirect:/admin/chitietkhambenh";
    }

    @PostMapping("/admin/chitietkhambenh/deleteall")
    public String deleteAllByIds(@RequestParam("selectedIds") List<Integer> ids, RedirectAttributes redirectAttributes) {
        try {
            chiTietKhamBenhService.deleteAllByIds(ids);
            redirectAttributes.addFlashAttribute("success", "Xóa thành công các mục đã chọn.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi xóa.");
        }
        return "redirect:/admin/chitietkhambenh";
    }
}
