package com.nhom27.nhatkykhambenh.mapper;

import com.nhom27.nhatkykhambenh.dto.TiemChungDTO;
import com.nhom27.nhatkykhambenh.model.TiemChung;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TiemChungMapper {
    public TiemChung toTiemChung(TiemChungDTO tiemChungDTO);

    public TiemChungDTO toTiemChungDTO(TiemChung tiemChung);

    public List<TiemChungDTO> toTiemChungDtoList(List<TiemChung> tiemChungList);
}
