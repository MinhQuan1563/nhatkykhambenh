package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.dto.DonThuocDTO;
import com.nhom27.nhatkykhambenh.exception.SaveDataException;
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
public class DonThuocController {
    @Autowired
    private DonThuocService donThuocService;

    @GetMapping("/admin/donthuoc")
    public String GetListDonThuoc(Model model,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  @RequestParam(defaultValue = "") String query) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DonThuocDTO> donThuocPage = donThuocService.getDSDonThuoc(pageable,query);
        int count = 0;

        model.addAttribute("dsDonThuoc", donThuocPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", donThuocPage.getTotalPages());
        model.addAttribute("totalItems", donThuocPage.getTotalElements());

        model.addAttribute("query", query);

        int startItem = page * size + 1;
        int endItem = Math.min(startItem + size - 1, (int) donThuocPage.getTotalElements());

        model.addAttribute("startItem", startItem);
        model.addAttribute("endItem", endItem);
        model.addAttribute("currentCount", endItem - startItem + 1);

        System.out.println("currentPage: " + page);
        System.out.println("currentPage: " + size);
        System.out.println("currentPage: " + donThuocPage.getTotalPages());
        System.out.println("currentPage: " + donThuocPage.getTotalElements());
        System.out.println("currentPage: " + query);
        return "admin/donthuoc/listDonThuoc";
    }

    @GetMapping("/admin/donthuoc/add")
    public String addDonThuocForm(Model model) {
        DonThuocDTO donThuocDTO = new DonThuocDTO();
        model.addAttribute("donthuoc", donThuocDTO);
        return "admin/donthuoc/addDonThuoc";
    }


    @GetMapping("/admin/donthuoc/update")
    public String updateDonThuocForm(@RequestParam("id") Integer id, Model model) {
        DonThuocDTO donThuocDTO = donThuocService.findById(id);
        model.addAttribute("donthuoc", donThuocDTO);
        return "admin/donthuoc/addDonThuoc";
    }

    @PostMapping("/admin/donthuoc/save")
    public String saveDonThuoc(@ModelAttribute("donthuoc") DonThuocDTO donthuoc,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/donthuoc/addDonThuoc";
        }
        try {
            donThuocService.saveDonThuoc(donthuoc);
            return "redirect:/admin/donthuoc";
        } catch (SaveDataException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/donthuoc/addDonThuoc";
        }
    }

    @PostMapping("/admin/donthuoc/delete")
    public String deleteDonThuoc(@RequestParam("maDonThuoc") Integer maDonThuoc, RedirectAttributes redirectAttributes) {
        try {
            donThuocService.deleteById(maDonThuoc);
            redirectAttributes.addFlashAttribute("success", "Xóa thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi!! Xóa thông tin Đơn Thuốc thất bại");
        }
        return "redirect:/admin/donthuoc";
    }

    @PostMapping("/admin/donthuoc/deleteall")
    public String deleteAllByIds(@RequestParam("selectedIds") List<Integer> ids, RedirectAttributes redirectAttributes) {
        try {
            donThuocService.deleteAllByIds(ids);
            redirectAttributes.addFlashAttribute("success", "Xóa thành công các mục đã chọn.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi xóa.");
        }
        return "redirect:/admin/donthuoc";
    }
}
