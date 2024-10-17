package com.nhom27.nhatkykhambenh.dto;
import com.nhom27.nhatkykhambenh.model.NguoiDungTiemChungId;
import com.nhom27.nhatkykhambenh.repository.INguoiDungTiemChung;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NguoiDungTiemChungDTO {
    private String tenVacXin;
    private Boolean trangThai;
    private NguoiDungDTO nguoiDung;
}