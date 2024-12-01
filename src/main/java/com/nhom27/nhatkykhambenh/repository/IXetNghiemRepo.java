package com.nhom27.nhatkykhambenh.repository;

import com.nhom27.nhatkykhambenh.model.XetNghiem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IXetNghiemRepo extends JpaRepository<XetNghiem, Integer> {
    XetNghiem findByChiTietKhamBenh_MaChiTietKhamBenh(Integer maChiTietKhamBenh);
}
