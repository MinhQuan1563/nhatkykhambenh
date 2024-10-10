package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.dto.KhamBenhDTO;
import com.nhom27.nhatkykhambenh.exception.SaveDataException;
import com.nhom27.nhatkykhambenh.service.implementation.KhamBenhService;
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
public class KhamBenhController {
    @Autowired
    private KhamBenhService khamBenhService;

    @GetMapping("/admin/khambenh")
    public String GetListKhamBenh(Model model,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "5") int size,
                                   @RequestParam(defaultValue = "") String query) {
        Pageable pageable = PageRequest.of(page, size);
        Page<KhamBenhDTO> khamBenhPage = khamBenhService.getDSKhamBenh(pageable,query);
        int count = 0;

        model.addAttribute("dsKhamBenh", khamBenhPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", khamBenhPage.getTotalPages());
        model.addAttribute("totalItems", khamBenhPage.getTotalElements());

        model.addAttribute("query", query);

        int startItem = page * size + 1;
        int endItem = Math.min(startItem + size - 1, (int) khamBenhPage.getTotalElements());

        model.addAttribute("startItem", startItem);
        model.addAttribute("endItem", endItem);
        model.addAttribute("currentCount", endItem - startItem + 1);

        System.out.println("currentPage: " + page);
        System.out.println("currentPage: " + size);
        System.out.println("currentPage: " + khamBenhPage.getTotalPages());
        System.out.println("currentPage: " + khamBenhPage.getTotalElements());
        System.out.println("currentPage: " + query);
        return "admin/khambenh/listKhamBenh";
    }

    @GetMapping("/admin/khambenh/add")
    public String addKhamBenhForm(Model model) {
        KhamBenhDTO khamBenhDTO = new KhamBenhDTO();
        model.addAttribute("khambenh", khamBenhDTO);
        return "admin/khambenh/addKhamBenh";
    }

    @GetMapping("/admin/khambenh/update")
    public String updateKhamBenhForm(@RequestParam("id") Integer id, Model model) {
        KhamBenhDTO khamBenhDTO = khamBenhService.findById(id);
        model.addAttribute("khambenh", khamBenhDTO);
        return "admin/khambenh/addKhamBenh";
    }

    @PostMapping("/admin/khambenh/save")
    public String saveKhamBenh(@ModelAttribute("khambenh") KhamBenhDTO khambenh,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/khambenh/addKhamBenh";
        }
        try {
            khamBenhService.saveKhamBenh(khambenh);
            return "redirect:/admin/khambenh";
        } catch (SaveDataException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/khambenh/addKhamBenh";
        }
    }

    @PostMapping("/admin/khambenh/delete")
    public String deleteKhamBenh(@RequestParam("maKhamBenh") Integer maKhamBenh, RedirectAttributes redirectAttributes) {
        try {
            khamBenhService.deleteById(maKhamBenh);
            redirectAttributes.addFlashAttribute("success", "Xóa thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi!! Xóa thông tin khám bệnh thất bại");
        }
        return "redirect:/admin/khambenh";
    }

    @PostMapping("/admin/khambenh/deleteall")
    public String deleteAllByIds(@RequestParam("selectedIds") List<Integer> ids, RedirectAttributes redirectAttributes) {
        try {
            khamBenhService.deleteAllByIds(ids);
            redirectAttributes.addFlashAttribute("success", "Xóa thành công các mục đã chọn.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi xóa.");
        }
        return "redirect:/admin/khambenh";
    }
}
