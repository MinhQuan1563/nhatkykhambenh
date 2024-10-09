package com.nhom27.nhatkykhambenh.mapper;


import com.nhom27.nhatkykhambenh.dto.LichHenTiemChungDTO;
import com.nhom27.nhatkykhambenh.dto.NguoiDungTiemChungDTO;
import com.nhom27.nhatkykhambenh.model.LichHenTiemChung;
import com.nhom27.nhatkykhambenh.model.NguoiDungTiemChung;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LichHenTiemChungMapper {
    public NguoiDungTiemChung toNguoiDungTiemChung(NguoiDungTiemChungDTO nguoiDungTiemChungDTO);
    public NguoiDungTiemChungDTO toNguoiDungTiemChungDTO(NguoiDungTiemChung nguoiDungTiemChung);
    public LichHenTiemChung toLichHenTiemChung(LichHenTiemChungDTO lichHenTiemChungDTO);

    @Mapping(source = "maLichHenTiemChung", target = "maLichHenTiemChung")
    @Mapping(source = "noiTiemChung", target = "noiTiemChung")
    @Mapping(source = "ngayHenTiem", target = "ngayHenTiem")
    public LichHenTiemChungDTO toLichHenTiemChungDTO(LichHenTiemChung lichHenTiemChung);

    public List<LichHenTiemChungDTO> toListLichHenTiemChungDTO(List<LichHenTiemChung> lichHenTiemChung);

}