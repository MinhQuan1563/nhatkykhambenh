package com.nhom27.nhatkykhambenh.service.interfaces;

import com.nhom27.nhatkykhambenh.model.ChiTietBenh;
import com.nhom27.nhatkykhambenh.model.TongQuan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IChiTietBenhService {
    void suyDienBenhTuTongQuan(TongQuan tongQuan);

    List<ChiTietBenh> getAllByTongQuan(TongQuan tongQuan, Boolean trangThai);

    List<ChiTietBenh> getAll();

    List<ChiTietBenh> filterChiTietBenh(String maGiaDinh);

    Page<ChiTietBenh> getDSChiTietBenh(Pageable pageable, String query, Integer maTongQuan);
}
