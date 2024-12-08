package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.dto.CustomOAuth2User;
import com.nhom27.nhatkykhambenh.enums.MoiQuanHe;
import com.nhom27.nhatkykhambenh.model.*;
import com.nhom27.nhatkykhambenh.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private ITaiKhoanRepo taiKhoanRepo;

    @Autowired
    private INguoiDungRepo nguoiDungRepo;

    @Autowired
    private IGiaDinhRepo giaDinhRepo;

    @Autowired
    private ITongQuanRepo tongQuanRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRoleRepo roleRepo;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String provider = userRequest.getClientRegistration().getRegistrationId(); // Xác định Google/Facebook
        String providerId = oAuth2User.getAttribute("sub");

        NguoiDung existNguoiDung  = nguoiDungRepo.findByEmail(email);

        if(existNguoiDung == null){
            GiaDinh giaDinh = new GiaDinh();
            giaDinhRepo.saveAndFlush(giaDinh);

            NguoiDung nguoiDung = new NguoiDung();
            nguoiDung.setEmail(email);
            nguoiDung.setTenNguoiDung(name);
            nguoiDung.setMoiQuanHe(MoiQuanHe.TOI);
            nguoiDung.setGiaDinh(giaDinh);
            nguoiDungRepo.save(nguoiDung);

            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setMaNguoiDung(nguoiDung.getMaNguoiDung());
            taiKhoan.setSoDienThoai(email);
            taiKhoan.setMatKhau(passwordEncoder.encode(providerId));
            taiKhoan.setGiaDinh(giaDinh);
            taiKhoan.setNguoiDung(nguoiDung);

            Role defaultRole = roleRepo.findByName("USER");
            taiKhoan.setDanhSachRole(List.of(defaultRole));
            taiKhoanRepo.save(taiKhoan);

            TongQuan tongQuan = new TongQuan();
            tongQuan.setNguoiDung(nguoiDung);
            tongQuanRepo.save(tongQuan);

            System.out.println("GiaDinh ID: " + giaDinh.getMaGiaDinh());
            System.out.println("NguoiDung ID: " + nguoiDung.getMaNguoiDung());
            System.out.println("TaiKhoan ID: " + taiKhoan.getMaNguoiDung());

            existNguoiDung = nguoiDung;
        }

        NguoiDung finalExistNguoiDung = existNguoiDung;
        TaiKhoan taiKhoan = taiKhoanRepo.findById(existNguoiDung.getMaNguoiDung())
                .orElseThrow(() -> new RuntimeException("Tai khoan not found for user id: " + finalExistNguoiDung.getMaNguoiDung()));

        CustomOAuth2User customUser = new CustomOAuth2User(oAuth2User, taiKhoan);
        return customUser;
    }
}
