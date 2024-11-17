package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.dto.NguoiDungDTO;
import com.nhom27.nhatkykhambenh.dto.RegistrationDTO;
import com.nhom27.nhatkykhambenh.dto.ResetPasswordDTO;
import com.nhom27.nhatkykhambenh.dto.TaiKhoanDTO;
import com.nhom27.nhatkykhambenh.model.PasswordResetToken;
import com.nhom27.nhatkykhambenh.repository.ITokenRepository;
import com.nhom27.nhatkykhambenh.service.implementation.TaiKhoanService;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import com.nhom27.nhatkykhambenh.model.TaiKhoan;
import com.nhom27.nhatkykhambenh.service.interfaces.IEmailService;
import com.nhom27.nhatkykhambenh.service.interfaces.INguoiDungService;
import com.nhom27.nhatkykhambenh.service.interfaces.ITaiKhoanService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private INguoiDungService nguoiDungService;

    @Autowired
    private ITaiKhoanService taiKhoanService;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private ITokenRepository tokenRepository;

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }

    @GetMapping("/login")
    public String login(Model model) {
        TaiKhoanDTO taiKhoanDTO = new TaiKhoanDTO();
        model.addAttribute("taiKhoan", taiKhoanDTO);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("taiKhoan") TaiKhoan taiKhoan, HttpSession session) {
        TaiKhoan tk = taiKhoanService.findBySoDienThoaiAndMatKhau(taiKhoan.getSoDienThoai(), taiKhoan.getMatKhau());
        if (tk == null) {
            return "login";
        }

        session.setAttribute("taikhoan", tk);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        TaiKhoanDTO taikhoanDTO = new TaiKhoanDTO();
        NguoiDungDTO nguoiDungDTO = new NguoiDungDTO();
        RegistrationDTO registrationDTO = new RegistrationDTO();
        registrationDTO.setNguoidung(nguoiDungDTO);
        registrationDTO.setTaikhoan(taikhoanDTO);
        model.addAttribute("registration", registrationDTO);
        return "register";
    }

    @PostMapping("/register")
    public String SaveTaiKhoan(@ModelAttribute("taikhoan")TaiKhoan taikhoan, HttpSession session) {
        NguoiDung nguoiDung = taiKhoanService.registerUser(taikhoan);

        session.setAttribute("nguoidung", nguoiDung);
        return "redirect:/";
    }

    @GetMapping("/users/thongtinkhac")
    public String getThongTinKhac(Model model,HttpSession session) {
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoidung");
        model.addAttribute("nguoiDung", nguoiDung);
        return "users/thongtinkhac";
    }

    @GetMapping("/forgotpassword")
    public String forgotPassword() {
        return "users/forgotpassword";
    }

    @PostMapping("/forgotpassword")
    public String forgotPassword(@ModelAttribute NguoiDungDTO nguoiDungDTO) {
        try {
            String output = "";
            NguoiDung nguoiDung = nguoiDungService.findByEmail(nguoiDungDTO.getEmail());
            if (nguoiDung != null) {
                output = emailService.sendEmail(nguoiDung);
            }

            if (output.equals("success"))
                return "redirect:/forgotpassword?success&email=" + nguoiDungDTO.getEmail();
            else {
                return "redirect:/login?error=This email does not exist";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return "redirect:/login?error=InternalError";
        }
    }

    @GetMapping("/resetpassword/{token}")
    public String resetPasswordForm(@PathVariable String token, Model model) {
        PasswordResetToken reset = tokenRepository.findByToken(token);
        if (reset != null && emailService.hasExpired(reset.getExpiryDateTime())) {
            model.addAttribute("email", reset.getNguoiDung().getEmail());
            return "users/resetpassword";
        }
        return "redirect:/forgotpassword?error";
    }

    @PostMapping("/resetpassword")
    public String passwordResetProcess(@ModelAttribute ResetPasswordDTO resetPasswordDTO) {
        NguoiDung nguoiDung = nguoiDungService.findByEmail(resetPasswordDTO.getEmail());
        TaiKhoan taiKhoan = taiKhoanService.findById(nguoiDung.getMaNguoiDung());

        if (taiKhoan != null) {
            taiKhoan.setMatKhau(resetPasswordDTO.getPassword());
            taiKhoanService.saveTaiKhoan(taiKhoan);
        }
        return "redirect:/login?success=Password changed successfully";
    }
}