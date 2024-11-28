package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.dto.ChiTietDonThuocDTO;
import com.nhom27.nhatkykhambenh.dto.DonThuocDTO;
import com.nhom27.nhatkykhambenh.dto.XetNghiemDTO;
import com.nhom27.nhatkykhambenh.exception.SaveDataException;
import com.nhom27.nhatkykhambenh.mapper.ChiTietDonThuocMapper;
import com.nhom27.nhatkykhambenh.model.ChiTietDonThuoc;
import com.nhom27.nhatkykhambenh.model.DonThuoc;
import com.nhom27.nhatkykhambenh.model.XetNghiem;
import com.nhom27.nhatkykhambenh.service.implementation.ChiTietDonThuocService;
import com.nhom27.nhatkykhambenh.service.implementation.DonThuocService;
import com.nhom27.nhatkykhambenh.service.interfaces.IChiTietDonThuocService;
import com.nhom27.nhatkykhambenh.service.interfaces.IDonThuocService;
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
    private IChiTietDonThuocService chiTietDonThuocService;

    @Autowired
    private ChiTietDonThuocMapper chiTietDonThuocMapper;

    @Autowired
    private IDonThuocService donThuocService;

    @GetMapping("/admin/khambenh/chitiet/donthuoc/chitietdonthuoc")
    public String GetListChiTietDonThuoc(Model model,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  @RequestParam(defaultValue = "") String query,
                                         @RequestParam Integer maDonThuoc) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ChiTietDonThuoc> chiTietDonThuocPage = chiTietDonThuocService.getDSChiTietDonThuoc(pageable, query, maDonThuoc);
        List<ChiTietDonThuocDTO> chiTietDonThuocDTOList = chiTietDonThuocMapper.toChiTietDonThuocDtoList(chiTietDonThuocPage.getContent());


        model.addAttribute("dsChiTietDonThuoc", chiTietDonThuocDTOList);
        model.addAttribute("maDonThuoc", maDonThuoc);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", chiTietDonThuocPage.getTotalPages());
        model.addAttribute("totalItems", chiTietDonThuocPage.getTotalElements());

        model.addAttribute("query", query);

        int startItem = page * size + 1;
        int endItem = Math.min(startItem + size - 1, (int) chiTietDonThuocPage.getTotalElements());

        model.addAttribute("startItem", startItem);
        model.addAttribute("endItem", endItem);
        model.addAttribute("currentCount", endItem - startItem + 1);

        return "admin/khambenh/listChiTietDonThuoc";
    }

    @GetMapping("/admin/khambenh/chitiet/donthuoc/chitietdonthuoc/add")
    public String addChTietDonThuocForm(Model model, @RequestParam Integer maDonThuoc) {
        ChiTietDonThuocDTO chiTietDonThuocDTO = new ChiTietDonThuocDTO();
        chiTietDonThuocDTO.setMaDonThuoc(maDonThuoc);

        model.addAttribute("chitietdonthuoc", chiTietDonThuocDTO);
        model.addAttribute("maDonThuoc2", maDonThuoc);
        return "admin/khambenh/addChiTietDonThuoc";
    }

    @GetMapping("/admin/khambenh/chitiet/donthuoc/chitietdonthuoc/update")
    public String updateChiTietDonThuocForm(@RequestParam Integer maChiTietDonThuoc,
                                            @RequestParam Integer maDonThuoc,
                                            Model model) {

        ChiTietDonThuoc chiTietDonThuoc = chiTietDonThuocService.findById(maChiTietDonThuoc);
        ChiTietDonThuocDTO chiTietDonThuocDTO = chiTietDonThuocMapper.toChiTietDonThuocDTO(chiTietDonThuoc);
        chiTietDonThuocDTO.setMaDonThuoc(maDonThuoc);

        model.addAttribute("chitietdonthuoc", chiTietDonThuocDTO);
        model.addAttribute("maDonThuoc2", maDonThuoc);
        return "admin/khambenh/addChiTietDonThuoc";
    }


    @PostMapping("/admin/khambenh/chitiet/donthuoc/chitietdonthuoc/save")
    public String saveChiTietDonThuoc(@ModelAttribute("chitietdonthuoc") ChiTietDonThuocDTO chiTietDonThuocDTO,
                               BindingResult bindingResult,
                               Model model,
                                      @RequestParam(required = false) Integer maDonThuoc,
                                      @RequestParam(required = false) Integer maChiTietDonThuoc,
                                      RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/khambenh/addChiTietDonThuoc";
        }
        try {
            if (maChiTietDonThuoc != null) {
                chiTietDonThuocDTO.setMaChiTietDonThuoc(maChiTietDonThuoc);
            }
            ChiTietDonThuoc chiTietDonThuoc = chiTietDonThuocMapper.toChiTietDonThuoc(chiTietDonThuocDTO);
            chiTietDonThuocService.saveChiTietDonThuoc(chiTietDonThuoc,maDonThuoc);

            redirectAttributes.addAttribute("maDonThuoc", maDonThuoc);
            return "redirect:/admin/khambenh/chitiet/donthuoc/chitietdonthuoc";
        } catch (SaveDataException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/khambenh/addChiTietKhamBenh";
        }
    }

    @PostMapping("/admin/khambenh/chitiet/donthuoc/chitietdonthuoc/delete")
    public String deleteChiTietDonThuoc(@RequestParam("maChiTietDonThuoc") Integer maChiTietDonThuoc,
                                        @RequestParam("maDonThuoc") Integer maDonThuoc,
                                        RedirectAttributes redirectAttributes) {
        try {
            chiTietDonThuocService.deleteById(maChiTietDonThuoc);
            redirectAttributes.addFlashAttribute("success", "Xóa thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi!! Xóa thông tin Loại Đơn Thuốc thất bại");
        }
        redirectAttributes.addAttribute("maDonThuoc", maDonThuoc);
        return "redirect:/admin/khambenh/chitiet/donthuoc/chitietdonthuoc";
    }

    @PostMapping("/admin/khambenh/chitiet/donthuoc/chitietdonthuoc/deleteall")
    public String deleteAllByIds(@RequestParam(value = "selectedIds", required = false) List<Integer> ids,
                                 @RequestParam("maDonThuoc") Integer maDonThuoc,
                                 RedirectAttributes redirectAttributes) {
        if (ids == null || ids.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không có mục nào được chọn để xóa.");
            redirectAttributes.addAttribute("maDonThuoc", maDonThuoc);
            return "redirect:/admin/khambenh/chitiet/donthuoc/chitietdonthuoc";
        }

        try {
            chiTietDonThuocService.deleteAllByIds(ids);
            redirectAttributes.addFlashAttribute("success", "Xóa thành công các mục đã chọn.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi xóa các mục đã chọn.");
        }

        redirectAttributes.addAttribute("maDonThuoc", maDonThuoc);
        return "redirect:/admin/khambenh/chitiet/donthuoc/chitietdonthuoc";
    }
}
