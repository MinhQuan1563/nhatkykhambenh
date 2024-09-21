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
@Table(name = "TiemChung")
public class TiemChung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTiemChung")
    private Integer maTiemChung;

    @Column(name = "NoTiemChung")
    private Integer noTiemChung;

    @Column(name = "NgayTiem")
    private Timestamp ngayTiem;

    @Column(name = "NguoiTiem", length = 250)
    private String nguoiTiem;

    @Column(name = "SoMuiTiem")
    private Integer soMuiTiem;

    @Column(name = "TrangThai")
    private Boolean trangThai;

    @OneToMany(mappedBy = "TiemChung",cascade = CascadeType.ALL)
    private List<ChiTietTiemChung> chiTietTiemChungList;
}