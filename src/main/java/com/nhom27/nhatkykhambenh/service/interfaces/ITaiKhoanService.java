package com.nhom27.nhatkykhambenh.service.interfaces;

import com.nhom27.nhatkykhambenh.dto.NguoiDungDTO;
import com.nhom27.nhatkykhambenh.dto.TaiKhoanDTO;
import com.nhom27.nhatkykhambenh.model.TaiKhoan;

public interface ITaiKhoanService {
    void saveTaiKhoan(TaiKhoanDTO taiKhoanDTO);

    TaiKhoanDTO findById(Integer id);

    void deleteById(Integer id);

    Boolean checkTaiKhoanExist(String tenTaiKhoan, String matKhau);

    TaiKhoanDTO getTaiKhoanByName(String name);

    TaiKhoan findBySoDienThoaiAndMatKhau(String soDienThoai, String matKhau);

    public TaiKhoanDTO registerUser(TaiKhoanDTO taiKhoanDTO, NguoiDungDTO nguoiDungDTO);
}
