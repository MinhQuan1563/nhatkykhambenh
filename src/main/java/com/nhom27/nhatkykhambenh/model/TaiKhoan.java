package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TaiKhoan")
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNguoiDung")
    private Integer maNguoiDung;

    @Column(name = "TaiKhoan")
    private String taiKhoan;

    @Column(name = "MatKhau")
    private String matKhau;
  
    @Column(name = "SoDienThoai")
    private String soDienThoai;
  
    @Column(name = "TrangThai")
    private Boolean trangThai=true;
  
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MaGiaDinh", referencedColumnName = "MaGiaDinh")
    @ToString.Exclude
    private GiaDinh giaDinh;
  
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaQuyen",nullable = true)
    private NhomQuyen nhomQuyen;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNguoiDung", referencedColumnName = "MaNguoiDung")
    @ToString.Exclude
    private NguoiDung nguoiDung;
}
