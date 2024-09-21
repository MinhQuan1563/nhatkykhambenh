package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ChucNang")
public class ChucNang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaChucNang")
    private Integer maChucNang;

    @Column(name = "TenChucNang", length = 250)
    private String tenChucNang;

    @Column(name = "TrangThai")
    private Boolean trangThai;

     @ManyToMany(mappedBy = "danhSachChucNang", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<NhomQuyen> danhSachNhomQuyen = new HashSet<>();
}
