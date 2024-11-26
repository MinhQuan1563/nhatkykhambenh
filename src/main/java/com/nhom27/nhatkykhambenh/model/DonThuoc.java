package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DonThuoc")
public class DonThuoc {

    public static final String OBJ_NAME = "DonThuoc";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDonThuoc")
    private Integer maDonThuoc;

    @Column(name = "BacSiKham")
    private String bacSiKham;

    @Column(name = "TrangThai")
    private Boolean trangThai;

//    @Column(name = "MaChiTietKhamBenh")
//    private Integer maChiTietKhamBenh;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "MaChiTietKhamBenh", referencedColumnName = "MaChiTietKhamBenh")
//    private ChiTietKhamBenh chiTietKhamBenh;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaChiTietKhamBenh", referencedColumnName = "MaChiTietKhamBenh")
    private ChiTietKhamBenh chiTietKhamBenh;

    @OneToMany(mappedBy = "donThuoc", fetch = FetchType.LAZY)
    private Set<ChiTietDonThuoc> danhSachChiTietDonThuoc = new HashSet<>();
}
