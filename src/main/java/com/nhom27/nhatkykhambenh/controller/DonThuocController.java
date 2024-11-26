package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.dto.DonThuocDTO;
import com.nhom27.nhatkykhambenh.dto.XetNghiemDTO;
import com.nhom27.nhatkykhambenh.exception.SaveDataException;
import com.nhom27.nhatkykhambenh.mapper.DonThuocMapper;
import com.nhom27.nhatkykhambenh.mapper.XetNghiemMapper;
import com.nhom27.nhatkykhambenh.model.DonThuoc;
import com.nhom27.nhatkykhambenh.model.XetNghiem;
import com.nhom27.nhatkykhambenh.service.implementation.DonThuocService;
import com.nhom27.nhatkykhambenh.service.interfaces.IChiTietKhamBenhService;
import com.nhom27.nhatkykhambenh.service.interfaces.IDonThuocService;
import com.nhom27.nhatkykhambenh.service.interfaces.IXetNghiemService;
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
    private IDonThuocService donThuocService;

    @Autowired
    private DonThuocMapper donThuocMapper;

    @Autowired
    private IChiTietKhamBenhService chiTietKhamBenhService;

    @GetMapping("/admin/khambenh/chitiet/donthuoc")
    public String GetListDonThuoc(Model model,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "5") int size,
                                   @RequestParam(defaultValue = "") String query,
                                   @RequestParam Integer maChiTietKhamBenh) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DonThuoc> donThuocPage = donThuocService.getDSDonThuoc(pageable, query, maChiTietKhamBenh);
        List<DonThuocDTO> donThuocDTOList = donThuocMapper.toDonThuocDtoList(donThuocPage.getContent());


        model.addAttribute("dsXetNghiem", donThuocDTOList);
        model.addAttribute("maChiTietKhamBenh", maChiTietKhamBenh);
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

        return "admin/khambenh/listDonThuoc";
    }

    @GetMapping("/admin/khambenh/chitiet/donthuoc/add")
    public String addDonThuocForm(Model model, @RequestParam Integer maChiTietKhamBenh) {
        DonThuocDTO donThuocDTO = new DonThuocDTO();
        donThuocDTO.setMaChiTietKhamBenh(maChiTietKhamBenh);
        model.addAttribute("donthuoc", donThuocDTO);
        model.addAttribute("maChiTietKhamBenh2", maChiTietKhamBenh);

        return "admin/donthuoc/addDonThuoc";
    }


    @GetMapping("/admin/khambenh/chitiet/donthuoc/update")
    public String updateDonThuocForm(@RequestParam Integer maDonThuoc,
                                     @RequestParam Integer maChiTietKhamBenh,
                                     Model model) {
        DonThuoc donThuoc = donThuocService.findById(maDonThuoc);
        DonThuocDTO donThuocDTO = donThuocMapper.toDonThuocDTO(donThuoc);
        donThuocDTO.setMaChiTietKhamBenh(maChiTietKhamBenh);

        model.addAttribute("donthuoc", donThuocDTO);
        model.addAttribute("maChiTietKhamBenh2", maChiTietKhamBenh);
        return "admin/khambenh/addDonThuoc";
    }

    @PostMapping("/admin/khambenh/chitiet/donthuoc/save")
    public String saveDonThuoc(@ModelAttribute("donthuoc") DonThuocDTO donThuocDTO,
                               BindingResult bindingResult,
                               Model model,
                               @RequestParam(required = false) Integer maChiTietKhamBenh,
                               @RequestParam(required = false) Integer maDonThuoc,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/khambenh/addDonThuoc";
        }
        try {
            if (maDonThuoc != null) {
                donThuocDTO.setMaDonThuoc(maDonThuoc);
            }
            DonThuoc donThuoc = donThuocMapper.toDonThuoc(donThuocDTO);
            donThuocService.saveDonThuoc(donThuoc,maChiTietKhamBenh);
            redirectAttributes.addAttribute("maChiTietKhamBenh", maChiTietKhamBenh);
            return "redirect:/admin/khambenh/chitiet/donthuoc";
        } catch (SaveDataException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/khambenh/addDonThuoc";
        }
    }

    @PostMapping("/admin/khambenh/chitiet/donthuoc/delete")
    public String deleteDonThuoc(@RequestParam("maDonThuoc") Integer maDonThuoc,@RequestParam("maChiTietKhamBenh") Integer maChiTietKhamBenh,
                                 RedirectAttributes redirectAttributes) {
        try {
            donThuocService.deleteById(maDonThuoc);
            redirectAttributes.addFlashAttribute("success", "Xóa thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi!! Xóa thông tin Đơn Thuốc thất bại");
        }
        redirectAttributes.addAttribute("maChiTietKhamBenh", maChiTietKhamBenh);
        return "redirect:/admin/khambenh/chitiet/donthuoc";
    }

    @PostMapping("/admin/khambenh/chitiet/donthuoc/deleteall")
    public String deleteAllByIds(@RequestParam(value = "selectedIds", required = false) List<Integer> ids,
                                 @RequestParam("maChiTietKhamBenh") Integer maChiTietKhamBenh,
                                RedirectAttributes redirectAttributes) {
        if (ids == null || ids.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không có mục nào được chọn để xóa.");
            redirectAttributes.addAttribute("maChiTietKhamBenh", maChiTietKhamBenh);  // Thêm maKhamBenh vào redirect
            return "redirect:/admin/khambenh/chitiet/donthuoc";
        }
        try {
            donThuocService.deleteAllByIds(ids);
            redirectAttributes.addFlashAttribute("success", "Xóa thành công các mục đã chọn.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi xóa các mục đã chọn.");
        }
        redirectAttributes.addAttribute("maChiTietKhamBenh", maChiTietKhamBenh);
        return "redirect:/admin/khambenh/chitiet/donthuoc";
    }
}
