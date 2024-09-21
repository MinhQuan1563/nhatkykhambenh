package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ChiTietTiemChung")
public class ChiTietTiemChung {
    @Column(name = "TenVacXin", length = 250)
    private String tenVacXin;

    @Column(name = "TrangThai")
    private Boolean trangThai;

    @EmbeddedId
    private ChiTietTiemChungId id;

    @ManyToOne
    @MapsId("MaTiemChung")
    @JoinColumn(name = "MaTiemChung")
    private TiemChung tiemChung;

    @ManyToOne
    @MapsId("MaNguoiDung")
    @JoinColumn(name = "MaNguoiDung")
    private NguoiDung nguoiDung;
}

@Embeddable
class ChiTietTiemChungId implements Serializable {
    @Column(name = "MaTiemChung")
    private Integer MaTiemChung;

    @Column(name = "MaNguoiDung")
    private Integer MaNguoiDung;
}
