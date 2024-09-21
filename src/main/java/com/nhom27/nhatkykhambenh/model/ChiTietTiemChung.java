package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ChiTietTiemChung")
public class ChiTietTiemChung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTiemChung")
    private Integer maTiemChung;

    @Column(name = "MaNguoiDung")
    private Integer maNguoiDung;

    @Column(name = "TenVacXin", length = 250)
    private String tenVacXin;

    @Column(name = "TrangThai")
    private Boolean trangThai;
}
