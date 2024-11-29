package com.nhom27.nhatkykhambenh.repository;

import com.nhom27.nhatkykhambenh.model.KhamBenh;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IKhamBenhRepo extends JpaRepository<KhamBenh, Integer> {
    List<KhamBenh> findAllByNguoiDung(NguoiDung nguoiDung);
}
