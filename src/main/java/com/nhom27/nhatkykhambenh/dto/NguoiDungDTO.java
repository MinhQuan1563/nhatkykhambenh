package com.nhom27.nhatkykhambenh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NguoiDungDTO {
    private Integer maNguoiDung;
    private String hinhAnh;
    private String soDienThoai;
    private String cccd;
    private Timestamp ngayThangNamSinh;
    private String gioiTinh;
    private String diaChi;
    private String email;
    private Boolean trangThai;
    private Integer maGiaDinh; // Đại diện cho đối tượng GiaDinh
}
