package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DonThuoc")
public class DonThuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDonThuoc")
    private Integer maDonThuoc;

    @Column(name = "BacSiKham")
    private String bacSiKham;

    @Column(name = "TenThuoc")
    private String tenThuoc;

    @Column(name = "TrangThai")
    private Boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "MaChiTietKhamBenh", referencedColumnName = "MaChiTietKhamBenh", insertable = false, updatable = false)
    private ChiTietKhamBenh chiTietKhamBenh;
}
