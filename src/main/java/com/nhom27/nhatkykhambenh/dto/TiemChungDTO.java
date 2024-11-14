package com.nhom27.nhatkykhambenh.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TiemChungDTO {

    private Integer maTiemChung;

    private String noiTiemChung;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ngayTiem;

    private String nguoiTiem;

    private Integer soMuiTiem;

    private Boolean trangThai;
}
