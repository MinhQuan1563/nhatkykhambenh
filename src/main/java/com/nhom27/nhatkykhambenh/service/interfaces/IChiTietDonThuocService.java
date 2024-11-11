package com.nhom27.nhatkykhambenh.service.interfaces;

import com.nhom27.nhatkykhambenh.dto.ChiTietDonThuocDTO;
import com.nhom27.nhatkykhambenh.model.ChiTietDonThuoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IChiTietDonThuocService {
    Page<ChiTietDonThuocDTO> getDSChiTietDonThuoc(Pageable pageable, String query);

    void saveChiTietDonThuoc(ChiTietDonThuocDTO chiTietDonThuocDTO);

    ChiTietDonThuocDTO findById(Integer id);

    void deleteById(Integer id);
}
