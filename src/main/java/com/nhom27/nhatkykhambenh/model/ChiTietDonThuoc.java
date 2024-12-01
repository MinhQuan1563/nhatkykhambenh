package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ChiTietDonThuoc")
public class ChiTietDonThuoc {
    public static final String OBJ_NAME = "ChiTietDonThuoc";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaChiTietDonThuoc")
    private Integer maChiTietDonThuoc;

    @Column(name = "TenThuoc", length = 250)
    private String tenThuoc;

    @Column(name = "LieuLuong", length = 250)
    private Integer lieuLuong;

    @Column(name = "SoLuong", length = 250)
    private Integer soLuong;

    @Column(name = "TrangThai")
    private Boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "MaDonThuoc", referencedColumnName = "MaDonThuoc")
    private DonThuoc donThuoc;
}