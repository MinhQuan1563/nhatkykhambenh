package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "MaGiaDinh", nullable = true)
    private GiaDinh giaDinh;

    @OneToMany(mappedBy = "NguoiDung",cascade = CascadeType.ALL)
    private List<KhamBenh> khamBenhs;

    @OneToMany(mappedBy = "NguoiDung",cascade = CascadeType.ALL)
    private List<NguoiDungTiemChung> nguoiDungTiemChungList;

    @OneToMany(mappedBy = "NguoiDung",cascade = CascadeType.ALL)
    private List<ChiTietTiemChung> chiTietTiemChungList;

    @OneToOne(mappedBy = "NguoiDung", cascade = CascadeType.ALL)
    private TongQuan tongQuan;

    @OneToOne
    @JoinColumn(name = "MaTheBHYT", unique = true)
    private ThongTinKhac thongTinKhac;

    @OneToOne(mappedBy = "TaiKhoan", cascade = CascadeType.ALL)
    private TaiKhoan taiKhoan;
}
