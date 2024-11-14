package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.dto.ChiTietDonThuocDTO;
import com.nhom27.nhatkykhambenh.exception.SaveDataException;
import com.nhom27.nhatkykhambenh.service.implementation.ChiTietDonThuocService;
import com.nhom27.nhatkykhambenh.service.implementation.DonThuocService;
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
public class ChiTietDonThuocController {
    @Autowired
    private ChiTietDonThuocService chiTietDonThuocService;

    @GetMapping("/admin/chitietdonthuoc")
    public String GetListChiTietDonThuoc(Model model,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  @RequestParam(defaultValue = "") String query) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ChiTietDonThuocDTO> chitietdonThuocPage = chiTietDonThuocService.getDSChiTietDonThuoc(pageable,query);
        int count = 0;

        model.addAttribute("dsChiTietDonThuoc", chitietdonThuocPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", chitietdonThuocPage.getTotalPages());
        model.addAttribute("totalItems", chitietdonThuocPage.getTotalElements());

        model.addAttribute("query", query);

        int startItem = page * size + 1;
        int endItem = Math.min(startItem + size - 1, (int) chitietdonThuocPage.getTotalElements());

        model.addAttribute("startItem", startItem);
        model.addAttribute("endItem", endItem);
        model.addAttribute("currentCount", endItem - startItem + 1);

        System.out.println("currentPage: " + page);
        System.out.println("currentPage: " + size);
        System.out.println("currentPage: " + chitietdonThuocPage.getTotalPages());
        System.out.println("currentPage: " + chitietdonThuocPage.getTotalElements());
        System.out.println("currentPage: " + query);
        return "admin/chitietdonthuoc/listChiTietDonThuoc";
    }

    @GetMapping("/admin/chitietdonthuoc/add")
    public String addChTietDonThuocForm(Model model) {
        ChiTietDonThuocDTO donThuocDTO = new ChiTietDonThuocDTO();
        model.addAttribute("chitietdonthuoc", donThuocDTO);
        return "admin/chitietdonthuoc/addChiTietDonThuoc";
    }


    @GetMapping("/admin/chitietdonthuoc/update")
    public String updateChiTietDonThuocForm(@RequestParam("id") Integer id, Model model) {
        ChiTietDonThuocDTO donThuocDTO = chiTietDonThuocService.findById(id);
        model.addAttribute("chitietdonthuoc", donThuocDTO);
        return "admin/chitietdonthuoc/addChiTietDonThuoc";
    }

    @PostMapping("/admin/chitietdonthuoc/save")
    public String saveChiTietDonThuoc(@ModelAttribute("chitietdonthuoc") ChiTietDonThuocDTO chitietdonthuoc,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/chitietdonthuoc/addChiTietDonThuoc";
        }
        try {
            chiTietDonThuocService.saveChiTietDonThuoc(chitietdonthuoc);
            return "redirect:/admin/chitietdonthuoc";
        } catch (SaveDataException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/chitietdonthuoc/addChiTietDonThuoc";
        }
    }

    @PostMapping("/admin/chitietdonthuoc/delete")
    public String deleteChiTietDonThuoc(@RequestParam("maChiTietDonThuoc") Integer maChiTietDonThuoc, RedirectAttributes redirectAttributes) {
        try {
            chiTietDonThuocService.deleteById(maChiTietDonThuoc);
            redirectAttributes.addFlashAttribute("success", "Xóa thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi!! Xóa thông tin Đơn Thuốc thất bại");
        }
        return "redirect:/admin/chitietdonthuoc";
    }

    @PostMapping("/admin/chitietdonthuoc/deleteall")
    public String deleteAllByIds(@RequestParam("selectedIds") List<Integer> ids, RedirectAttributes redirectAttributes) {
        try {
            chiTietDonThuocService.deleteAllByIds(ids);
            redirectAttributes.addFlashAttribute("success", "Xóa thành công các mục đã chọn.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi xóa.");
        }
        return "redirect:/admin/chitietdonthuoc";
    }
}
