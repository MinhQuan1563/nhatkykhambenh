package com.nhom27.nhatkykhambenh.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class NguoiDungTiemChungId implements Serializable {
    private Integer maNguoiDung;
    private Integer maLichHenTiemChung;
}