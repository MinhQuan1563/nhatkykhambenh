package com.nhom27.nhatkykhambenh.service.interfaces;

import com.nhom27.nhatkykhambenh.dto.TiemChungDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITiemChungService {
    Page<TiemChungDTO> getDSTiemChung(Pageable pageable);

    void saveTiemChung(TiemChungDTO tiemChungDTO);

    TiemChungDTO findById(Integer id);

    void deleteById(Integer id);
}
