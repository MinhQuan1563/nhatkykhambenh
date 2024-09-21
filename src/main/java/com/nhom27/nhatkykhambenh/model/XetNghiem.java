package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "XetNghiem")
public class XetNghiem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaXetNghiem")
    private Integer maXetNghiem;

    @Column(name = "KetQuaXetNghiem", length = 250)
    private String ketQuaXetNghiem;

    @Column(name = "TrangThai")
    private Boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "MaChiTietKhamBenh",referencedColumnName="MaChiTietKhamBenh", insertable = false, updatable = false)
    private ChiTietKhamBenh chiTietKhamBenh;
}
