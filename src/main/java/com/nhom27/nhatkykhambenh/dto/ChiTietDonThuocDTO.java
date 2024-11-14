package com.nhom27.nhatkykhambenh.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nhom27.nhatkykhambenh.model.DonThuoc;
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
public class ChiTietDonThuocDTO {

    private Integer maChiTietDonThuoc;
    private String tenThuoc;
    private Integer lieuLuong;
    private Integer soLuong;
    private Boolean trangThai;
}
