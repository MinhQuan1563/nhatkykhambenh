package com.nhom27.nhatkykhambenh.repository;

import com.nhom27.nhatkykhambenh.model.ChiTietDonThuoc;
import com.nhom27.nhatkykhambenh.model.DonThuoc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IChiTietDonThuocRepo extends JpaRepository<ChiTietDonThuoc, Integer> {
}
