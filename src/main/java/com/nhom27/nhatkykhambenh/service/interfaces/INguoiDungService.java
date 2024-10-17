package com.nhom27.nhatkykhambenh.service.interfaces;


import com.nhom27.nhatkykhambenh.dto.NguoiDungDTO;
import com.nhom27.nhatkykhambenh.model.NguoiDung;

public interface INguoiDungService {
    void saveNguoiDung(NguoiDung nguoiDung);
    NguoiDungDTO findById(Integer id);
    void deleteById(Integer id);
}
