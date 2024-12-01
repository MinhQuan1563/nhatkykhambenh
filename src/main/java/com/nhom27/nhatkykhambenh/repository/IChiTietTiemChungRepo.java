package com.nhom27.nhatkykhambenh.repository;

import com.nhom27.nhatkykhambenh.model.ChiTietTiemChung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IChiTietTiemChungRepo extends JpaRepository<ChiTietTiemChung, Integer> {
    Optional<ChiTietTiemChung> findByMaTiemChungAndMaNguoiDung(Integer maTiemChung, Integer maNguoiDung);
    List<ChiTietTiemChung> findAllByMaNguoiDung(Integer maNguoiDung);
}
