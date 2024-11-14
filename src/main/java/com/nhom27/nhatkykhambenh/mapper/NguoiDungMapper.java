package com.nhom27.nhatkykhambenh.mapper;

import com.nhom27.nhatkykhambenh.dto.NguoiDungDTO;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NguoiDungMapper {
    NguoiDung toNguoiDung(NguoiDungDTO nguoiDungDTO);
    NguoiDungDTO toNguoiDungDTO(NguoiDung nguoiDung);
}
