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
@Table(name = "LichHenTiemChung")
public class LichHenTiemChung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaLichHenTiemChung")
    private Integer maLichHenTiemChung;

    @Column(name = "NoiTiemChung")
    private Integer noiTiemChung;

    @Column(name = "NgayHenTiem")
    private Timestamp ngayHenTiem;

    @OneToMany(mappedBy = "LichHenTiemChung",cascade = CascadeType.ALL)
    private List<NguoiDungTiemChung> nguoiDungTiemChungList;
}