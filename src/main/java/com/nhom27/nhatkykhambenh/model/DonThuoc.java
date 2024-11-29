package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "TinhTrang")
    private String tinhTrang;

    @Column(name = "TrangThai")
    private Boolean trangThai;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaChiTietKhamBenh", referencedColumnName = "MaChiTietKhamBenh")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ChiTietKhamBenh chiTietKhamBenh;

    @OneToMany(mappedBy = "donThuoc", fetch = FetchType.LAZY)
    private Set<ChiTietDonThuoc> danhSachChiTietDonThuoc = new HashSet<>();
}
