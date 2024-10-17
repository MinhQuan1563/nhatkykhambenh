package com.nhom27.nhatkykhambenh.service.interfaces;

import com.nhom27.nhatkykhambenh.dto.NguoiDungDTO;
import com.nhom27.nhatkykhambenh.dto.TaiKhoanDTO;

public interface ITaiKhoanService {
    void saveTaiKhoan(TaiKhoanDTO taiKhoanDTO);

    TaiKhoanDTO findById(Integer id);

    void deleteById(Integer id);

    Boolean checkTaiKhoanExist(String tenTaiKhoan, String matKhau);

    TaiKhoanDTO getTaiKhoanByName(String name);

    public TaiKhoanDTO registerUser(TaiKhoanDTO taiKhoanDTO, NguoiDungDTO nguoiDungDTO);
}
