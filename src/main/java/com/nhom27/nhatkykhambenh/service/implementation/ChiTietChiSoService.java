package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.dto.ChiTietChiSoDTO;
import com.nhom27.nhatkykhambenh.exception.SaveDataException;
import com.nhom27.nhatkykhambenh.mapper.ChiTietChiSoMapper;
import com.nhom27.nhatkykhambenh.model.ChiTietChiSo;
import com.nhom27.nhatkykhambenh.model.TiemChung;
import com.nhom27.nhatkykhambenh.repository.IChiTietChiSoRepo;
import com.nhom27.nhatkykhambenh.service.interfaces.IChiTietChiSoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ChiTietChiSoService implements IChiTietChiSoService {

    @Autowired
    private IChiTietChiSoRepo chiTietChiSoRepo;

    @Autowired
    private ChiTietChiSoMapper chiTietChiSoMapper;

    @Override
    public List<ChiTietChiSoDTO> getDsChiTietChiSo(Integer maChiSo, Integer maTongQuan) {
        List<ChiTietChiSo> chiTietChiSoList = chiTietChiSoRepo.findByMaTongQuanAndMaChiSo(maTongQuan, maChiSo);
        return chiTietChiSoMapper.toChiTietChiSoDtoList(chiTietChiSoList);
    }

    @Override
    public void saveCTChiSo(ChiTietChiSoDTO chiTietChiSoDTO) {
        ChiTietChiSo chiTietChiSo = chiTietChiSoMapper.toChiTietChiSo(chiTietChiSoDTO);
        try {
            chiTietChiSoRepo.save(chiTietChiSo);
        } catch (Exception e) {
            throw new SaveDataException(TiemChung.OBJ_NAME);
        }
    }

    @Transactional
    @Override
    public void deleteChiTietChiSo(Integer maTongQuan, Integer maChiSo, Date thoiGianDo) {
        chiTietChiSoRepo.deleteByMaTongQuanAndMaChiSoAndThoiGianDo(maTongQuan, maChiSo, thoiGianDo);
    }


}
