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
@Table(name = "KhamBenh")
public class KhamBenh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaKhamBenh")
    private Integer maKhamBenh;

    @Column(name = "BenhVien", length = 250)
    private String benhVien;

    @Column(name = "NgayKham")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp ngayKham;

    @Column(name = "TrangThai")
    private Boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "MaNguoiDung", nullable = true)
    private NguoiDung nguoiDung;

    @OneToMany(mappedBy = "khamBenh",cascade = CascadeType.ALL)
    private List<ChiTietKhamBenh> chiTietKhamBenhList;

    @ManyToOne
    @JoinColumn(name = "MaHenKham", nullable = true)
    private LichHenKham lichHenKham;
}
