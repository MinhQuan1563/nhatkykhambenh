package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NguoiDung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaNguoiDung")
    private Integer maNguoiDung;

    @Column(name = "HinhAnh")
    private String hinhAnh;

    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @Column(name = "CCCD")
    private String cccd;

    @Column(name = "NgayThangNamSinh")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp ngayThangNamSinh;

    @Column(name = "GioiTinh")
    private String gioiTinh;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "Email")
    private String email;

    @Column(name = "MatKhau")
    private String matKhau;

    @Column(name = "TrangThai")
    private Boolean trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaGiaDinh")
    private GiaDinh giaDinh;

    @OneToOne(mappedBy = "nguoiDung", fetch = FetchType.LAZY)
    private ThongTinKhac thongTinKhac;

    @OneToOne(mappedBy = "nguoiDung")
    private TongQuan tongQuan;

    @OneToMany(mappedBy = "nguoiDung", fetch = FetchType.LAZY)
    private Set<NguoiDungTiemChung> danhSachNguoiDungTiemChung = new HashSet<>();

    @OneToMany(mappedBy = "nguoiDung", fetch = FetchType.LAZY)
    private Set<ChiTietTiemChung> danhSachChiTietTiemChung = new HashSet<>();


    @OneToMany(mappedBy = "nguoiDung", fetch = FetchType.LAZY)
    private Set<ChiTietTiemChung> danhSachKhamBenh = new HashSet<>();
}
