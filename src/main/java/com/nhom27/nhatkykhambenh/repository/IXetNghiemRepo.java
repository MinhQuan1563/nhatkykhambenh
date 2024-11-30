package com.nhom27.nhatkykhambenh.repository;

import com.nhom27.nhatkykhambenh.model.XetNghiem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IXetNghiemRepo extends JpaRepository<XetNghiem, Integer> {
    XetNghiem findByChiTietKhamBenh_MaChiTietKhamBenh(Integer maChiTietKhamBenh);

    @Query("SELECT xn FROM XetNghiem xn WHERE xn.maChiTietKhamBenh = :maChiTietKhamBenh")
    List<XetNghiem> findByMaChiTietKhamBenh(@Param("maChiTietKhamBenh") Integer maChiTietKhamBenh);
}
