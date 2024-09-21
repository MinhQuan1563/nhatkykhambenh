package com.nhom27.nhatkykhambenh.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NhomQuyen")
public class NhomQuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaQuyen")
    private Integer maQuyen;

    @Column(name = "TenQuyen", length = 250)
    private String tenQuyen;

    @Column(name = "TrangThai")
    private Boolean trangThai;

    // Cấu hình quan hệ Many-to-Many với ChucNang
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "NhomQuyen_ChucNang", // Tên bảng trung gian
        joinColumns = @JoinColumn(name = "MaQuyen"), // Khoá ngoại tham chiếu từ NhomQuyen
        inverseJoinColumns = @JoinColumn(name = "MaChucNang") // Khoá ngoại tham chiếu từ ChucNang
    )
    private Set<ChucNang> danhSachChucNang = new HashSet<>();

    @OneToMany(mappedBy = "nhomQuyen", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TaiKhoan> danhSachTaiKhoan = new HashSet<>();

}
