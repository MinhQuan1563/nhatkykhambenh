package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TaiKhoan")
public class TaiKhoan {
    @Id
    @Column(name = "MaNguoiDung")
    private Integer maNguoiDung;

    @Column(name = "TaiKhoan")
    private String taiKhoan;

    @Column(name = "MatKhau")
    private String matKhau;

    @Column(name = "TrangThai")
    private Boolean trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaQuyen")
    private NhomQuyen nhomQuyen;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNguoiDung", referencedColumnName = "MaNguoiDung")
    private NguoiDung nguoiDung;
}
