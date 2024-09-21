package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TongQuan")
public class TongQuan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTongQuan")
    private Integer maTongQuan;

    @Column(name = "DuongHuyet", length = 250)
    private String duongHuyet;

    @Column(name = "NhipTim", length = 250)
    private String nhipTim;

    @Column(name = "HuyetAp", length = 250)
    private String huyetAp;

    @Column(name = "NhietDo", length = 250)
    private String nhietDo;

    @Column(name = "ChieuCao", length = 250)
    private String chieuCao;

    @Column(name = "CanNang", length = 250)
    private String canNang;

    @Column(name = "ChiSoBMI", length = 250)
    private String chiSoBMI;

    @Column(name = "NhomMau", length = 250)
    private String nhomMau;

    @Column(name = "TrangThai")
    private Boolean trangThai;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNguoiDung", referencedColumnName = "MaNguoiDung")
    private NguoiDung nguoiDung;

    @OneToMany(mappedBy = "tongQuan", fetch = FetchType.LAZY)
    private Set<ChiTietBenh> danhSachChiTietBenh = new HashSet<>();

}
