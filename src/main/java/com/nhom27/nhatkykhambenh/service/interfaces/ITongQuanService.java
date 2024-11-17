package com.nhom27.nhatkykhambenh.service.interfaces;

import com.nhom27.nhatkykhambenh.model.TongQuan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITongQuanService {
    Page<TongQuan> getDSTongQuan(Pageable pageable, String query);

    TongQuan findByNguoiDung(Integer maNguoiDung);

    void deleteById(Integer id);

    String[][] updateChiSoForTongQuan(TongQuan tongQuan);
}