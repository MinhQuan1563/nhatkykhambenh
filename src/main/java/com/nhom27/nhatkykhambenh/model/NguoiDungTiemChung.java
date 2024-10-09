package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NguoiDungTiemChung")
public class NguoiDungTiemChung {

    @EmbeddedId
    private NguoiDungTiemChungId id;

    @Column(name = "TenVacXin", length = 250)
    private String tenVacXin;

    @Column(name = "TrangThai")
    private Boolean trangThai;

    @ManyToOne
    @MapsId("maNguoiDung")
    @JoinColumn(name = "MaNguoiDung")
    private NguoiDung nguoiDung;

    @ManyToOne
    @MapsId("maLichHenTiemChung")
    @JoinColumn(name = "maLichHenTiemChung")
    private LichHenTiemChung lichHenTiemChung;


}
