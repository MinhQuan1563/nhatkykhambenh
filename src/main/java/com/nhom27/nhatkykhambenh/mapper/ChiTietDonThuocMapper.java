package com.nhom27.nhatkykhambenh.mapper;

import com.nhom27.nhatkykhambenh.dto.ChiTietDonThuocDTO;
import com.nhom27.nhatkykhambenh.model.ChiTietDonThuoc;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface ChiTietDonThuocMapper {
    public ChiTietDonThuoc toChiTietDonThuoc(ChiTietDonThuocDTO chitietdonThuocDTO);

    public ChiTietDonThuocDTO toChiTietDonThuocDTO(ChiTietDonThuoc chitietdonThuoc);

    public List<ChiTietDonThuocDTO> toChiTietDonThuocDtoList(List<ChiTietDonThuoc> chitietdonThuocList);

    @Mapping(target = "maChiTietDonThuoc", ignore = true)
    void updateChiTietDonThuocFromDTO(ChiTietDonThuocDTO chitietdonThuocDTO, @MappingTarget ChiTietDonThuoc chitietdonThuoc);
}
