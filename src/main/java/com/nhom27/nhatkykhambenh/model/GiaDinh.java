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
@Table(name = "GiaDinh")
public class GiaDinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaGiaDinh")
    private Integer maGiaDinh;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "TrangThai")
    private Boolean trangThai;

    @OneToMany(mappedBy = "GiaDinh",cascade = CascadeType.ALL)
    private List<NguoiDung> nguoiDungs;
}
