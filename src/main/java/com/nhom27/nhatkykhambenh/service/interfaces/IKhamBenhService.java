package com.nhom27.nhatkykhambenh.service.interfaces;

import com.nhom27.nhatkykhambenh.dto.KhamBenhDTO;
import com.nhom27.nhatkykhambenh.dto.NguoiDungDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IKhamBenhService {
    Page<KhamBenhDTO> getDSKhamBenh(Pageable pageable, String query);

    void saveKhamBenh(KhamBenhDTO khamBenhDTO);

    KhamBenhDTO findById(Integer id);

    void deleteById(Integer id);
}
