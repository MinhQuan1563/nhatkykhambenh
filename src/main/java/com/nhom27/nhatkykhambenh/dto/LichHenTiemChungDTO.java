package com.nhom27.nhatkykhambenh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LichHenTiemChungDTO {
    private Integer maLichHenTiemChung;
    private String noiTiemChung;
    private LocalDateTime ngayHenTiem;
    private Set<NguoiDungTiemChungDTO> nguoiDungTiemChungList;
}
