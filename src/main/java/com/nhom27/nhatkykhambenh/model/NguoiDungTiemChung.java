package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NguoiDungTiemChung")
public class NguoiDungTiemChung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaLichTiemChung")
    private Integer maLichTiemChung;

    @Column(name = "TenVacXin", length = 250)
    private String tenVacXin;

    @Column(name = "TrangThai")
    private Boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "MaNguoiDung", insertable = false, updatable = false)
    private NguoiDung nguoiDung;
}
