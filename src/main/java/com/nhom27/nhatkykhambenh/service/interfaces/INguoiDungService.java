package com.nhom27.nhatkykhambenh.service.interfaces;

import com.nhom27.nhatkykhambenh.dto.NguoiDungDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface INguoiDungService {
    Page<NguoiDungDTO> getDSNguoiDung(Pageable pageable, String query);

    void saveNguoiDung(NguoiDungDTO nguoiDungDTO);

    NguoiDungDTO findById(Integer id);

    void deleteById(Integer id);
}
