package com.nhom27.nhatkykhambenh.mapper;

import com.nhom27.nhatkykhambenh.dto.DonThuocDTO;
import com.nhom27.nhatkykhambenh.model.DonThuoc;
import com.nhom27.nhatkykhambenh.model.DonThuoc;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface DonThuocMapper {
    public DonThuoc toDonThuoc(DonThuocDTO donThuocDTO);

    public DonThuocDTO toDonThuocDTO(DonThuoc donThuoc);

    public List<DonThuocDTO> toDonThuocDtoList(List<DonThuoc> donThuocList);

    @Mapping(target = "maDonThuoc", ignore = true)
    void updateDonThuocFromDTO(DonThuocDTO donThuocDTO, @MappingTarget DonThuoc donThuoc);
}
