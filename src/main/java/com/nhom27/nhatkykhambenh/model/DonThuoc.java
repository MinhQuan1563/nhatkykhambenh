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

    @Column(name = "BacSiKham", length = 250)
    private String bacSiKham;

    @Column(name = "TenThuoc", length = 250)
    private String tenThuoc;

    @Column(name = "TrangThai")
    private Boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "MaChiTietKhamBenh", insertable = false, updatable = false)
    private ChiTietKhamBenh chiTietKhamBenh;
}
