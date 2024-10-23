package com.nhom27.nhatkykhambenh.model;

import com.nhom27.nhatkykhambenh.enums.MoiQuanHe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;
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

//    @Column(name = "HoTen")
//    private String hoTen;

    @Column(name = "HinhAnh")
    private String hinhAnh;
    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @Column(name = "CCCD")
    private String cccd;

//    @Column(name = "NgayThangNamSinh")
//    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "NgayThangNamSinh")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayThangNamSinh;

    @Column(name = "GioiTinh")
    private String gioiTinh;

    @Column(name = "DiaChi")
    private String diaChi;
    @Column(name="TenNguoiDung")
    private String tenNguoiDung;

    @Column(name = "Email")
    private String email;

//    @Column(name = "MatKhau")
//    private String matKhau;

//    @Column(name = "MoiQuanHe")
//    private MoiQuanHe moiQuanHe;
    @Column(name = "MoiQuanHe")
    private String moiQuanHe;

    @Column(name = "TrangThai")
    private Boolean trangThai=true;

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

    @OneToOne(mappedBy = "nguoiDung", fetch = FetchType.LAZY)
    @ToString.Exclude
    private TaiKhoan taiKhoan;
}
