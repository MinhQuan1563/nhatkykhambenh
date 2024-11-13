package com.nhom27.nhatkykhambenh.mapper;

import com.nhom27.nhatkykhambenh.dto.XetNghiemDTO;
import com.nhom27.nhatkykhambenh.model.XetNghiem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface XetNghiemMapper {
    public XetNghiem toXetNghiem(XetNghiemDTO xetNghiemDTO);

    public XetNghiemDTO toXetNghiemDTO(XetNghiem xetNghiem);

    public List<XetNghiemDTO> toXetNghiemDtoList(List<XetNghiem> xetNghiemList);

    @Mapping(target = "maXetNghiem", ignore = true)
    void updateXetNghiemFromDTO(XetNghiemDTO xetNghiemDTO, @MappingTarget XetNghiem xetNghiem);
}
