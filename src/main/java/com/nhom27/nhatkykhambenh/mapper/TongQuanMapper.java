package com.nhom27.nhatkykhambenh.mapper;

import com.nhom27.nhatkykhambenh.dto.TongQuanDTO;
import com.nhom27.nhatkykhambenh.model.TongQuan;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TongQuanMapper {
    public TongQuan toTongQuan(TongQuanDTO tongQuanDTO);

    public TongQuanDTO toTongQuanDTO(TongQuan tongQuan);

    public List<TongQuanDTO> toTongQuanDtoList(List<TongQuan> tongQuanList);
}