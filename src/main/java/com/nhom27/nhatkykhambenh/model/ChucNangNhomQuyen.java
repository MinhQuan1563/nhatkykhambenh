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
@Table(name = "ChucNangNhomQuyen")
public class ChucNangNhomQuyen {
    @Column(name = "TrangThai")
    private Boolean trangThai;

    @EmbeddedId
    private ChucNangNhomQuyenId id;

    @ManyToOne
    @MapsId("MaChucNang")
    @JoinColumn(name = "MaChucNang")
    private ChucNang chucNang;

    @ManyToOne
    @MapsId("MaQuyen")
    @JoinColumn(name = "MaQuyen")
    private NhomQuyen nhomQuyen;
}

@Embeddable
class ChucNangNhomQuyenId implements Serializable {
    @Column(name = "MaChucNang")
    private Integer MaChucNang;

    @Column(name = "MaQuyen")
    private Integer MaQuyen;
}
