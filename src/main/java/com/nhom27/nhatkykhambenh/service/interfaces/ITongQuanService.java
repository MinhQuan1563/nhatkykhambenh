package com.nhom27.nhatkykhambenh.service.interfaces;

import com.nhom27.nhatkykhambenh.dto.TongQuanDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITongQuanService {
    Page<TongQuanDTO> getDSTongQuan(Pageable pageable, String query);

    void saveTongQuan(TongQuanDTO tongQuanDTO);

    TongQuanDTO findById(Integer id);

    void deleteById(Integer id);
}
