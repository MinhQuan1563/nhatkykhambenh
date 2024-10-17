package com.nhom27.nhatkykhambenh.service.interfaces;


import com.nhom27.nhatkykhambenh.dto.TaiKhoanDTO;
import com.nhom27.nhatkykhambenh.model.TaiKhoan;

public interface ITaiKhoanService {
    void saveTaiKhoan(TaiKhoan taiKhoan);
    TaiKhoanDTO findById(Integer id);
    void deleteById(Integer id);
    TaiKhoan findByTaiKhoan(String soDienThoai,String matKhau);
}
