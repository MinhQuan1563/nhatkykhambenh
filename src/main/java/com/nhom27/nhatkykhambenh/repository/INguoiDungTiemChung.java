package com.nhom27.nhatkykhambenh.repository;

import com.nhom27.nhatkykhambenh.model.LichHenTiemChung;
import com.nhom27.nhatkykhambenh.model.NguoiDungTiemChung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface INguoiDungTiemChung extends JpaRepository<NguoiDungTiemChung, Integer> {
    public Set<NguoiDungTiemChung> findAllByLichHenTiemChung(LichHenTiemChung lichHenTiemChung);
    void deleteByLichHenTiemChung(LichHenTiemChung lichHenTiemChung);
}
