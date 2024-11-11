package com.nhom27.nhatkykhambenh.service.interfaces;

import com.nhom27.nhatkykhambenh.dto.DonThuocDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDonThuocService {
    Page<DonThuocDTO> getDSDonThuoc(Pageable pageable, String query);

    void saveDonThuoc(DonThuocDTO donThuocDTO);

    DonThuocDTO findById(Integer id);

    void deleteById(Integer id);
}
