package com.nhom27.nhatkykhambenh.service.interfaces;

import com.nhom27.nhatkykhambenh.dto.TiemChungDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITiemChungService {

    Page<TiemChungDTO> getDSTiemChung(Pageable pageable, String query);

    List<TiemChungDTO> getAllTiemChung();

    void saveTiemChung(TiemChungDTO tiemChungDTO);

    TiemChungDTO findById(Integer id);

    void deleteById(Integer id);

    void deleteAllByIds(List<Integer> ids);
}
