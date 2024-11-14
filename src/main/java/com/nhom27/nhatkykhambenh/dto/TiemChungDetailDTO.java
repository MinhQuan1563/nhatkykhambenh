package com.nhom27.nhatkykhambenh.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TiemChungDetailDTO {

    private Integer maTiemChung;

    private Integer maNguoiDung;

    private String noiTiemChung;

    private LocalDateTime ngayTiem;

    private String nguoiTiem;

    private Integer soMuiTiem;

    private String tenVacXin;
}
