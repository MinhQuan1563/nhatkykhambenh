package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NguoiDungTiemChung")
public class NguoiDungTiemChung {
    @Column(name = "TenVacXin", length = 250)
    private String tenVacXin;

    @Column(name = "TrangThai")
    private Boolean trangThai;

    @EmbeddedId
    private NguoiDungTiemChungId id;

    @ManyToOne
    @MapsId("MaNguoiDung")
    @JoinColumn(name = "MaNguoiDung")
    private NguoiDung nguoiDung;

    @ManyToOne
    @MapsId("MaLichHenTiemChung")
    @JoinColumn(name = "MaLichHenTiemChung")
    private LichHenTiemChung lichHenTiemChung;
}

@Embeddable
class NguoiDungTiemChungId implements Serializable {
    @Column(name = "MaNguoiDung")
    private Integer MaNguoiDung;

    @Column(name = "MaLichHenTiemChung")
    private Integer MaLichHenTiemChung;
}
