package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ChiTietKhamBenh")
public class ChiTietKhamBenh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaChiTietKhamBenh")
    private Integer maChiTietKhamBenh;

    @Column(name = "KhoaKham", length = 250)
    private String khoaKham;

    @Column(name = "BacSiKham", length = 250)
    private String bacSiKham;

    @Column(name = "ChiDinh", length = 250)
    private String chiDinh;

    @Column(name = "ChuanDoan", length = 250)
    private String chuanDoan;

    @Column(name = "NhomMau", length = 250)
    private String nhomMau;

    @Column(name = "TrangThai")
    private Boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "MaKhamBenh", nullable = true)
    private KhamBenh khamBenh;

    @OneToMany(mappedBy = "ChiTietKhamBenh",cascade = CascadeType.ALL)
    private List<DonThuoc> donThuocList;

    @OneToMany(mappedBy = "ChiTietKhamBenh",cascade = CascadeType.ALL)
    private List<HinhAnh> hinhAnhList;

    @OneToMany(mappedBy = "ChiTietKhamBenh",cascade = CascadeType.ALL)
    private List<XetNghiem> xetNghiemList;
}
