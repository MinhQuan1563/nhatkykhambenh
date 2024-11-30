package com.nhom27.nhatkykhambenh.repository;

import com.nhom27.nhatkykhambenh.model.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IHinhAnhRepo extends JpaRepository<HinhAnh, Integer> {
    @Query("SELECT ha FROM HinhAnh ha WHERE ha.chiTietKhamBenh.maChiTietKhamBenh = :maChiTietKhamBenh")
    List<HinhAnh> findByMaChiTietKhamBenh(@Param("maChiTietKhamBenh") Integer maChiTietKhamBenh);
}
