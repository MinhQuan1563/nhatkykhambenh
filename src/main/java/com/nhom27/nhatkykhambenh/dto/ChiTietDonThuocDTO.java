package com.nhom27.nhatkykhambenh.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChiTietDonThuocDTO {
    private Integer maChiTietDonThuoc;
    private Integer maDonThuoc;
    private String tenThuoc;
    private Integer lieuLuong;
    private Integer soLuong;
    private Boolean trangThai;
}
