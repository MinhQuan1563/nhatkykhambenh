package com.nhom27.nhatkykhambenh.mapper;

import com.nhom27.nhatkykhambenh.dto.NguoiDungDTO;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NguoiDungMapper {
    public NguoiDung toNguoiDung(NguoiDungDTO nguoiDungDTO);

    public NguoiDungDTO toNguoiDungDTO(NguoiDung nguoiDung);

    public List<NguoiDungDTO> toNguoiDungDtoList(List<NguoiDung> nguoiDungList);
}