package com.nhom27.nhatkykhambenh.service.interfaces;

import com.nhom27.nhatkykhambenh.dto.GiaDinhDTO;
import com.nhom27.nhatkykhambenh.dto.TiemChungDTO;
import com.nhom27.nhatkykhambenh.model.GiaDinh;

public interface IGiaDinhService {
    void saveGiaDinh(GiaDinh giaDinh);
    GiaDinhDTO findById(Integer id);
    void deleteById(Integer id);
}
