package com.nhom27.nhatkykhambenh.service.interfaces;

import com.nhom27.nhatkykhambenh.model.NguoiDung;
import com.nhom27.nhatkykhambenh.model.TaiKhoan;

public interface ITaiKhoanService {
    void saveTaiKhoan(TaiKhoan taiKhoan);

    void deleteById(Integer id);

    NguoiDung registerUser(TaiKhoan taiKhoan);

    TaiKhoan findBySoDienThoaiAndMatKhau(String soDienThoai, String matKhau);

    TaiKhoan findById(Integer maNguoiDung);
}