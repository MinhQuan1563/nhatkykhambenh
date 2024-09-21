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
    @EmbeddedId
    private ChiTietTiemChungId id;

    @Column(name = "TenVacXin", length = 250)
    private String tenVacXin;

    @Column(name = "TrangThai")
    private Boolean trangThai;

    @ManyToOne
    @MapsId("maTiemChung")
    @JoinColumn(name = "MaTiemChung", referencedColumnName = "MaTiemChung")
    private TiemChung tiemChung;

    @ManyToOne
    @MapsId("maNguoiDung")
    @JoinColumn(name = "MaNguoiDung", referencedColumnName = "MaNguoiDung")
    private NguoiDung nguoiDung;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public class ChiTietTiemChungId implements Serializable {
        private Integer maTiemChung;
        private Integer maNguoiDung;
    }
}
