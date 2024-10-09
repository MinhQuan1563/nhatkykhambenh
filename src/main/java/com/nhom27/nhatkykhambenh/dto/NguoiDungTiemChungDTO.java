package com.nhom27.nhatkykhambenh.dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NguoiDungTiemChungDTO {
    private Integer maNguoiDung; // Duy trì thông tin ID của người dùng
    private String tenVacXin;
    private Boolean trangThai;
}