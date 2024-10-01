package com.nhom27.nhatkykhambenh.repository;

import com.nhom27.nhatkykhambenh.model.TiemChung;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

public interface ITiemChungRepo extends JpaRepository<TiemChung, Integer> {
    Page<TiemChung> findByTrangThai(Boolean trangThai, Pageable pageable);
}
