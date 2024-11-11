package com.nhom27.nhatkykhambenh.repository;

import com.nhom27.nhatkykhambenh.model.GiaDinh;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INguoiDungRepo extends JpaRepository<NguoiDung, Integer> {
    List<NguoiDung> findByGiaDinh(GiaDinh giaDinh);
    NguoiDung findByEmail(String email);
}
