package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.dto.PaginationResponse;
import com.nhom27.nhatkykhambenh.dto.TiemChungDTO;
import com.nhom27.nhatkykhambenh.exception.SaveDataException;
import com.nhom27.nhatkykhambenh.mapper.TiemChungMapper;
import com.nhom27.nhatkykhambenh.model.TiemChung;
import com.nhom27.nhatkykhambenh.repository.ITiemChungRepo;
import com.nhom27.nhatkykhambenh.service.interfaces.ITiemChungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TiemChungService implements ITiemChungService {

    @Autowired
    private ITiemChungRepo tiemChungRepo;
    @Autowired
    private TiemChungMapper tiemChungMapper;

    @Override
    public Page<TiemChungDTO> getDSTiemChung(Pageable pageable) {
        Page<TiemChung> tiemChungPage = tiemChungRepo.findByTrangThai(true, pageable);

        List<TiemChungDTO> tiemChungDTOList = tiemChungMapper.toTiemChungDtoList(tiemChungPage.getContent());

        return new PageImpl<>(tiemChungDTOList, pageable, tiemChungPage.getTotalElements());
    }

    @Override
    public void saveTiemChung(TiemChungDTO tiemChungDTO) {
        TiemChung tiemChung = tiemChungMapper.toTiemChung(tiemChungDTO);
        tiemChung.setTrangThai(true);
        try {
            tiemChungRepo.save(tiemChung);
        } catch (Exception e) {
            throw new SaveDataException(TiemChung.OBJ_NAME);
        }
    }

    @Override
    public TiemChungDTO findById(Integer id) {
        TiemChung tiemChung = tiemChungRepo.findById(id).get();
        return tiemChungMapper.toTiemChungDTO(tiemChung);
    }

    @Override
    public void deleteById(Integer id) {
        TiemChung tiemChung = tiemChungRepo.findById(id).orElseThrow(() -> new SaveDataException(TiemChung.OBJ_NAME));
        tiemChung.setTrangThai(false);
        tiemChungRepo.save(tiemChung);
    }
}
