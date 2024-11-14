package com.nhom27.nhatkykhambenh.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nhom27.nhatkykhambenh.enums.MoiQuanHe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NguoiDungDTO {
    private Integer maNguoiDung;

    private String hoTen;

    private String hinhAnh;

    private String soDienThoai;

    private String cccd;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp ngayThangNamSinh;

    private String gioiTinh;

    private String diaChi;

    private String email;

    private String matKhau;

    private MoiQuanHe moiQuanHe;

    private Boolean trangThai;
}