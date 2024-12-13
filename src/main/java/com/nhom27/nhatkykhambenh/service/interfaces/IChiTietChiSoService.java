package com.nhom27.nhatkykhambenh.service.interfaces;

import com.nhom27.nhatkykhambenh.dto.ChiTietChiSoDTO;
import com.nhom27.nhatkykhambenh.model.ChiTietChiSo;

import java.util.Date;
import java.util.List;

public interface IChiTietChiSoService {
    List<ChiTietChiSo> getDsChiTietChiSo(Integer maChiSo, Integer maTongQuan);

    void saveCTChiSo(ChiTietChiSo chiTietChiSo);

    void deleteChiTietChiSo(Integer maChiSo, Integer maTongQuan, Date thoiGianDo);
}
