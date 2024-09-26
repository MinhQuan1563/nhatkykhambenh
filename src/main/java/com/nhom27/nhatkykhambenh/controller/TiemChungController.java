package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.dto.PaginationResponse;
import com.nhom27.nhatkykhambenh.dto.TiemChungDTO;
import com.nhom27.nhatkykhambenh.exception.SaveDataException;
import com.nhom27.nhatkykhambenh.service.implementation.TiemChungService;
import com.nhom27.nhatkykhambenh.utils.OneBasedPageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class TiemChungController {

    @Autowired
    private TiemChungService tiemChungService;

    @GetMapping("/admin/tiemchung")
    public String GetAllTiemChung(Model model,
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
        model.addAttribute("tiemchung", new TiemChungDTO());
        return "admin/tiemchung/addTiemChung";
    }

    @PostMapping("/admin/tiemchung/save")
    public String saveTiemChung(@ModelAttribute("tiemchung") TiemChungDTO tiemchung,
                                @RequestParam("ngayTiem") String ngayTiemStr,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/tiemchung/addTiemChung";  // Trả về form với thông báo lỗi
        }
        try {
            LocalDate ngayTiem = LocalDate.parse(ngayTiemStr);
            tiemchung.setNgayTiem(Timestamp.valueOf(ngayTiem.atStartOfDay()));
            tiemChungService.saveTiemChung(tiemchung);
            return "admin/tiemchung/listTiemChung";  // Chuyển hướng sau khi lưu thành công
        } catch (SaveDataException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/tiemchung/addTiemChung";  // Trả về form với thông báo lỗi
        }
    }

    @PostMapping("/admin/tiemchung/delete")
    public String deleteMember(@RequestParam("id") Integer id) {
        tiemChungService.deleteById(id);
        return "admin/tiemchung/listTiemChung";
    }

    @GetMapping("/admin/tongquan")
    public String GetAllTongQuan() {
        return "admin/listTongQuan";
    }


}
