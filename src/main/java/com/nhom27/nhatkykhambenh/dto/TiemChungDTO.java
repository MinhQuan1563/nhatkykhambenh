package com.nhom27.nhatkykhambenh.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nhom27.nhatkykhambenh.model.ChiTietTiemChung;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TiemChungDTO {

    private Integer maTiemChung;

    private String noiTiemChung;

    private Timestamp ngayTiem;

    private String nguoiTiem;

    private Integer soMuiTiem;

    private Boolean trangThai;

    private Set<ChiTietTiemChung> danhSachChiTietTiemChung;
}
