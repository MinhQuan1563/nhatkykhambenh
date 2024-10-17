package com.nhom27.nhatkykhambenh.repository;

import com.nhom27.nhatkykhambenh.model.GiaDinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGiaDinhRepo extends JpaRepository<GiaDinh, Integer> {
}