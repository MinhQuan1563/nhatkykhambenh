package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToMany(mappedBy = "NhomQuyen",cascade = CascadeType.ALL)
    private List<TaiKhoan> taiKhoanList;

    @OneToMany(mappedBy = "NhomQuyen",cascade = CascadeType.ALL)
    private List<ChucNangNhomQuyen> chucNangNhomQuyenList;
}
