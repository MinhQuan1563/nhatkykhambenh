package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "MaNguoiDung")
    private Integer maNguoiDung;

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
}
