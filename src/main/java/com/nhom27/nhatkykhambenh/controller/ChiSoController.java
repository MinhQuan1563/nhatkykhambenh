package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.dto.ChiSoDTO;
import com.nhom27.nhatkykhambenh.dto.ChiTietChiSoDTO;
import com.nhom27.nhatkykhambenh.dto.TongQuanDTO;
import com.nhom27.nhatkykhambenh.exception.SaveDataException;
import com.nhom27.nhatkykhambenh.model.ChiSo;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import com.nhom27.nhatkykhambenh.model.TaiKhoan;
import com.nhom27.nhatkykhambenh.model.TongQuan;
import com.nhom27.nhatkykhambenh.service.implementation.ChiSoService;
import com.nhom27.nhatkykhambenh.service.implementation.ChiTietChiSoService;
import com.nhom27.nhatkykhambenh.service.implementation.TongQuanService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChiSoController {

    @Autowired
    private ChiSoService chiSoService;

    @Autowired
    private ChiTietChiSoService chiTietChiSoService;

    @Autowired
    private TongQuanService tongQuanService;

    @GetMapping("users/chiso")
    public String showChiSo() {
        return "users/chiso";
    }

    @GetMapping("users/chiso/{loaiChiSo}")
    public String getListChiSoByTenLoai(@PathVariable String loaiChiSo, Model model, HttpSession session) {

        List<String> pageName = (List<String>) session.getAttribute("pageName");
        ChiSo chiSo = chiSoService.findByLoaiChiSo(loaiChiSo);
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoidung");
        TongQuan tongQuan = tongQuanService.findByNguoiDung(nguoiDung.getMaNguoiDung());

        List<ChiTietChiSoDTO> chiTietChiSoDTOList = null;
        if (chiSo != null && tongQuan != null) {
            chiTietChiSoDTOList = chiTietChiSoService.getDsChiTietChiSo(chiSo.getMaChiSo(), tongQuan.getMaTongQuan());
        }

        if(!pageName.contains(chiSo.getTenChiSo())) {
            pageName.add(chiSo.getTenChiSo());
        }

        session.setAttribute("pageName", pageName);
        model.addAttribute("dsChiTietChiSo", chiTietChiSoDTOList);
        assert chiSo != null;
        model.addAttribute("chiSo", chiSo);

        return "users/chiso";
    }

    @GetMapping("users/chiso/{loaiChiSo}/add")
    public String addCTChiSoForm(@PathVariable String loaiChiSo, Model model, HttpSession session) {
        ChiTietChiSoDTO chiTietChiSoDTO = new ChiTietChiSoDTO();

        model.addAttribute("ctchiso", chiTietChiSoDTO);
        model.addAttribute("loaichiso", loaiChiSo);

        return "users/themctchiso";
    }

    @PostMapping("/users/chiso/save")
    public String saveCTChiSo(@ModelAttribute("ctchiso") ChiTietChiSoDTO ctchiso,
                              @RequestParam("loaiChiSo") String loaiChiSo,
                              BindingResult bindingResult,
                              Model model,
                              HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "redirect:/error";
        }

        ChiSo chiSo = chiSoService.findByLoaiChiSo(loaiChiSo);

        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoidung");

        TongQuan tongQuan = tongQuanService.findByNguoiDung(nguoiDung.getMaNguoiDung());

        try {
            ctchiso.setMaChiSo(chiSo.getMaChiSo());
            ctchiso.setMaTongQuan(tongQuan.getMaTongQuan());
            chiTietChiSoService.saveCTChiSo(ctchiso);
            return "redirect:/users/chiso/" + loaiChiSo;
        } catch (SaveDataException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/error";
        }
    }

    @PostMapping("/users/chiso/{loaiChiSo}/delete")
    public String deleteCTChiSo(@PathVariable String loaiChiSo,
                                @RequestParam("maTongQuan") Integer maTongQuan,
                                @RequestParam("maChiSo") Integer maChiSo,
                                @RequestParam("thoiGianDo") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date thoiGianDo,
                                RedirectAttributes redirectAttributes) {

        try {
            chiTietChiSoService.deleteChiTietChiSo(maTongQuan, maChiSo, thoiGianDo);
            redirectAttributes.addFlashAttribute("success", "Xóa thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi!! Xóa thông tin chi tiet chi so thất bại");
        }
        return "redirect:/users/chiso/" + loaiChiSo;
    }
}