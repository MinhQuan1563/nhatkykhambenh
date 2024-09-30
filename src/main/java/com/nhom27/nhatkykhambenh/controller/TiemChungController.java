package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.dto.TiemChungDTO;
import com.nhom27.nhatkykhambenh.exception.SaveDataException;
import com.nhom27.nhatkykhambenh.service.implementation.TiemChungService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class TiemChungController {

    @Autowired
    private TiemChungService tiemChungService;

    @GetMapping("/admin/tiemchung")
    public String GetListTiemChung(Model model,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TiemChungDTO> tiemChungPage = tiemChungService.getDSTiemChung(pageable);

        model.addAttribute("dsTiemChung", tiemChungPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", tiemChungPage.getTotalPages());
        model.addAttribute("totalItems", tiemChungPage.getTotalElements());

        return "admin/tiemchung/listTiemChung";
    }

    @GetMapping("/admin/tiemchung/add")
    public String addTiemChungForm(Model model) {
        TiemChungDTO tiemChungDTO = new TiemChungDTO();
        model.addAttribute("tiemchung", tiemChungDTO);
        return "admin/tiemchung/addTiemChung";
    }

    @GetMapping("/admin/tiemchung/update")
    public String updateTiemChungForm(@RequestParam("id") Integer id, Model model) {
        TiemChungDTO tiemChungDTO = tiemChungService.findById(id);
        model.addAttribute("tiemchung", tiemChungDTO);
        return "admin/tiemchung/addTiemChung";
    }

    @PostMapping("/admin/tiemchung/save")
    public String saveTiemChung(@ModelAttribute("tiemchung") TiemChungDTO tiemchung,
                                @RequestParam("ngayTiem") String ngayTiemStr,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/tiemchung/addTiemChung";
        }
        try {
            tiemChungService.saveTiemChung(tiemchung);
            return "redirect:/admin/tiemchung";
        } catch (SaveDataException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/tiemchung/addTiemChung";
        }
    }

    @PostMapping("/admin/tiemchung/delete")
    public String deleteTiemChung(@RequestParam("maTiemChung") Integer maTiemChung, RedirectAttributes redirectAttributes) {
        try {
            tiemChungService.deleteById(maTiemChung);
            redirectAttributes.addFlashAttribute("success", "Xóa thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi!! Xóa thông tin tiêm chủng thất bại");
        }
        return "redirect:/admin/tiemchung";
    }

    @GetMapping("/admin/tongquan")
    public String GetAllTongQuan() {
        return "admin/listTongQuan";
    }


}
