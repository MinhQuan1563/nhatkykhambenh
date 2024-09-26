package com.nhom27.nhatkykhambenh.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nhom27.nhatkykhambenh.model.ChiTietTiemChung;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TiemChungDTO {

    private Integer maTiemChung;

    private String noiTiemChung;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayTiem;

    private String nguoiTiem;

    private Integer soMuiTiem;

    private Boolean trangThai;

    private Set<ChiTietTiemChung> danhSachChiTietTiemChung;
}
