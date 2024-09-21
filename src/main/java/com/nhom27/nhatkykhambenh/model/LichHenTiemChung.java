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
@Table(name = "LichHenTiemChung")
public class LichHenTiemChung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaLichHenTiemChung")
    private Integer maLichHenTiemChung;

    @Column(name = "NoTiemChung")
    private Integer noTiemChung;

    @Column(name = "NgayHenTiem")
    private Timestamp ngayHenTiem;
}