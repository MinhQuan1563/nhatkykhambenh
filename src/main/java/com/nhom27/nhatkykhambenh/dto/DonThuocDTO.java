package com.nhom27.nhatkykhambenh.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nhom27.nhatkykhambenh.model.KhamBenh;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DonThuocDTO {
    private Integer maDonThuoc;
    private String bacSiKham;
//    private String tenThuoc;
    private Boolean trangThai;
//    private KhamBenh khamBenh;
    private Integer maChiTietKhamBenh;
}
