package com.nhom27.nhatkykhambenh.service.interfaces;

import com.nhom27.nhatkykhambenh.dto.ChiTietKhamBenhDTO;
import com.nhom27.nhatkykhambenh.model.ChiTietKhamBenh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IChiTietKhamBenhService {
    Page<ChiTietKhamBenhDTO> getDSChiTietKhamBenh(Pageable pageable, String query);

    void saveChiTietKhamBenh(ChiTietKhamBenhDTO chiTietKhamBenhDTO);

    ChiTietKhamBenhDTO findById(Integer id);

    void deleteById(Integer id);

}
