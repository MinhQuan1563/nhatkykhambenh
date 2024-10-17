package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.dto.NguoiDungDTO;
import com.nhom27.nhatkykhambenh.dto.RegistrationDTO;
import com.nhom27.nhatkykhambenh.dto.TaiKhoanDTO;
import com.nhom27.nhatkykhambenh.service.implementation.TaiKhoanService;
import com.nhom27.nhatkykhambenh.utils.Validation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private TaiKhoanService taikhoanService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("taikhoan", new TaiKhoanDTO());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("taikhoan") TaiKhoanDTO taiKhoanDTO, HttpSession session, Model model) {
        String taiKhoan = taiKhoanDTO.getTaiKhoan();
        String password = taiKhoanDTO.getMatKhau();

        TaiKhoanDTO taiKhoanFromDB = taikhoanService.getTaiKhoanByName(taiKhoan);

        if(taiKhoanFromDB != null && taikhoanService.checkTaiKhoanExist(taiKhoan, password)) {
            session.setAttribute("loggedInUser", taiKhoanFromDB);
            return "redirect:/";
        }
        else {
            model.addAttribute("errorMessage", "Tài khoản hoặc mật khẩu không chính xác!");
            return "login";
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setTaikhoan(new TaiKhoanDTO());
        registrationDTO.setNguoidung(new NguoiDungDTO());
        model.addAttribute("registration", registrationDTO);
        return "register";
    }

    @PostMapping("/register")
        public String register(@ModelAttribute("registration") RegistrationDTO registrationDTO,
                           Model model) {
        try {
            if (!Validation.checkPasswordMatch(registrationDTO.getTaikhoan().getMatKhau(),
                    registrationDTO.getRePassword())) {
                throw new Exception("Mật khẩu và mật khẩu nhập lại không khớp.");
            }

            if (!Validation.checkEmail(registrationDTO.getNguoidung().getEmail())) {
                throw new Exception("Địa chỉ email không hợp lệ.");
            }

//            if (!Validation.checkPassword(registrationDTO.getTaikhoan().getMatKhau())) {
//                throw new Exception("Mật khẩu không đủ tiêu chuẩn.");
//            }

            if (!Validation.checkPhone(registrationDTO.getNguoidung().getSoDienThoai())) {
                throw new Exception("Số điện thoại không hợp lệ.");
            }

            taikhoanService.registerUser(registrationDTO.getTaikhoan(), registrationDTO.getNguoidung());
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }

    @GetMapping("/users/thongtinkhac")
    public String getThongTinKhac(Model model) {
        return "users/thongtinkhac";
    }

    @GetMapping("/users/themnguoithan")
    public String formThemNguoiThan(Model model) {
        return "users/themnguoithan";
    }
}
