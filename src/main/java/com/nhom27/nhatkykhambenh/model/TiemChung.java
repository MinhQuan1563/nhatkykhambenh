package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TiemChung")
public class TiemChung {
    public static final String OBJ_NAME = "TiemChung";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTiemChung")
    private Integer maTiemChung;

    @Column(name = "NoiTiemChung")
    private String noiTiemChung;

    @Column(name = "NgayTiem")
    private LocalDate ngayTiem;

    @Column(name = "NguoiTiem", length = 250)
    private String nguoiTiem;

    @Column(name = "SoMuiTiem")
    private Integer soMuiTiem;

    @Column(name = "TrangThai")
    private Boolean trangThai;

    @OneToMany(mappedBy = "tiemChung",cascade = CascadeType.ALL)
    private Set<ChiTietTiemChung> danhSachChiTietTiemChung = new HashSet<>();

}