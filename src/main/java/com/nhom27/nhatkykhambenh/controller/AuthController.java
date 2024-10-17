package com.nhom27.nhatkykhambenh.controller;

=======
import com.nhom27.nhatkykhambenh.dto.NguoiDungDTO;
import com.nhom27.nhatkykhambenh.dto.RegistrationDTO;
import com.nhom27.nhatkykhambenh.dto.TaiKhoanDTO;
import com.nhom27.nhatkykhambenh.service.implementation.TaiKhoanService;
import com.nhom27.nhatkykhambenh.utils.Validation;
=======
import com.nhom27.nhatkykhambenh.model.GiaDinh;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import com.nhom27.nhatkykhambenh.model.TaiKhoan;
import com.nhom27.nhatkykhambenh.service.implementation.GiaDinhService;
import com.nhom27.nhatkykhambenh.service.implementation.NguoiDungService;
import com.nhom27.nhatkykhambenh.service.implementation.TaiKhoanService;
=======
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
=======

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

=======
    @Autowired
    private TaiKhoanService taikhoanService;
    @Autowired
    private GiaDinhService giaDinhService;
    @Autowired
    private NguoiDungService nguoiDungService;
    @GetMapping("admin")
    public String home() {
        return "admin/dashboard";
    }

    @GetMapping("/login")
    public String login(Model model) {
        TaiKhoan taiKhoan=new TaiKhoan();
        model.addAttribute("taiKhoan", taiKhoan);
        return "login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute("taiKhoan") TaiKhoan taiKhoan, HttpSession session) {
        TaiKhoan tk=taikhoanService.findByTaiKhoan(taiKhoan.getSoDienThoai(),taiKhoan.getMatKhau());
        if (tk==null){
            return "login";
        }
        session.setAttribute("taiKhoan", tk);
        return "redirect:/";
    }
    @GetMapping("/register")
    public String register(Model model) {
        TaiKhoan taikhoan = new TaiKhoan();
        model.addAttribute("taikhoan", taikhoan);
        return "register";
    }
    @PostMapping("/register")
    public String SaveTaiKhoan(@ModelAttribute("taikhoan")TaiKhoan taikhoan, HttpSession session) {
        GiaDinh giaDinh=new GiaDinh();
        giaDinhService.saveGiaDinh(giaDinh);
        taikhoan.setGiaDinh(giaDinh);
        taikhoanService.saveTaiKhoan(taikhoan);
        NguoiDung nguoiDung=new NguoiDung();
        nguoiDung.setSoDienThoai(taikhoan.getSoDienThoai());
        nguoiDung.setTenNguoiDung(taikhoan.getTaiKhoan());
        nguoiDung.setMaNguoiDung(taikhoan.getMaNguoiDung());
        nguoiDungService.saveNguoiDung(nguoiDung);
        session.setAttribute("nguoidung", nguoiDung);
        return "redirect:/";
    }
=======
    @GetMapping("/users/thongtinkhac")
    public String getThongTinKhac(Model model,HttpSession session) {
        NguoiDung nguoiDung =(NguoiDung)session.getAttribute("nguoidung");
        return "users/thongtinkhac";
    }

//    @GetMapping("/users/themnguoithan")
//    public String formThemNguoiThan(Model model) {
//        return "users/themnguoithan";
//    }
}
