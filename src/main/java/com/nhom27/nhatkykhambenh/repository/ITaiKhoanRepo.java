package com.nhom27.nhatkykhambenh.repository;


import com.nhom27.nhatkykhambenh.model.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaiKhoanRepo extends JpaRepository<TaiKhoan, Integer> {
    TaiKhoan findBySoDienThoaiAndMatKhau(String soDienThoai, String matKhau);
}
