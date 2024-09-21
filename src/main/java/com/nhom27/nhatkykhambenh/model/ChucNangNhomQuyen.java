package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ChucNangNhomQuyen")
public class ChucNangNhomQuyen {

    @Id
    @Column(name = "MaChucNang")
    private Integer maChucNang;

    @Column(name = "TrangThai")
    private Boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "MaChucNang", insertable = false, updatable = false)
    private ChucNang chucNang;

    @ManyToOne
    @JoinColumn(name = "MaQuyen")
    private NhomQuyen nhomQuyen;
}
