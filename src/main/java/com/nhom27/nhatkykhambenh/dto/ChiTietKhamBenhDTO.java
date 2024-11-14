package com.nhom27.nhatkykhambenh.dto;

import com.nhom27.nhatkykhambenh.model.HinhAnh;
import com.nhom27.nhatkykhambenh.model.KhamBenh;
import com.nhom27.nhatkykhambenh.model.XetNghiem;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

public class ChiTietKhamBenhDTO {
    private Integer maChiTietKhamBenh;

    private String khoaKham;

    private String bacSiKham;

    private String chiDinh;

    private String chuanDoan;

    private String nhomMau;

    private Boolean trangThai;

    private KhamBenh khamBenh;

    private Set<HinhAnh> danhSachHinhAnh = new HashSet<>();

    private Set<XetNghiem> danhSachXetNghiem = new HashSet<>();
}
