package com.nhom27.nhatkykhambenh.repository;

import com.nhom27.nhatkykhambenh.model.KhamBenh;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IKhamBenhRepo extends JpaRepository<KhamBenh, Integer> {
    List<KhamBenh> findAllByNguoiDung(NguoiDung nguoiDung);

    @Query("SELECT kb FROM KhamBenh kb JOIN kb.danhSachChiTietKhamBenh ctkb WHERE ctkb.maChiTietKhamBenh = :maChiTietKhamBenh")
    KhamBenh findByChiTietKhamBenh(@Param("maChiTietKhamBenh") Integer maChiTietKhamBenh);
}
