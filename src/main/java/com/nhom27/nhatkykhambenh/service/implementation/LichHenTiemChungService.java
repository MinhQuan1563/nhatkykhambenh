package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.dto.LichHenTiemChungDTO;
import com.nhom27.nhatkykhambenh.dto.NguoiDungTiemChungDTO;
import com.nhom27.nhatkykhambenh.mapper.LichHenTiemChungMapper;
import com.nhom27.nhatkykhambenh.model.LichHenTiemChung;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import com.nhom27.nhatkykhambenh.model.NguoiDungTiemChung;
import com.nhom27.nhatkykhambenh.repository.ILichHenTiemChungRepo;
import com.nhom27.nhatkykhambenh.repository.INguoiDungTiemChung;
import com.nhom27.nhatkykhambenh.service.interfaces.ILichHenTiemChungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

import java.util.ArrayList;
import java.util.List;

@Service
public class LichHenTiemChungService implements ILichHenTiemChungService {
    @Autowired
    private ILichHenTiemChungRepo lichHenTiemChungRepo;

    @Autowired
    private INguoiDungTiemChung nguoiDungTiemChungRepo;

    @Autowired
    private LichHenTiemChungMapper lichHenTiemChungMapper;

    @Override
    public void CreateLichHenTiemChung(LichHenTiemChungDTO lichHenTiemChungDTO, Set<NguoiDungTiemChung> nguoiDungTiemChungSet) {
        LichHenTiemChung lichHenTiemChung = this.lichHenTiemChungMapper.toLichHenTiemChung(lichHenTiemChungDTO);
        LichHenTiemChung savedLichHenTiemChung = lichHenTiemChungRepo.save(lichHenTiemChung);
        nguoiDungTiemChungSet.forEach(nguoiDungTiemChung -> {
            nguoiDungTiemChung.setLichHenTiemChung(savedLichHenTiemChung);
        });
         nguoiDungTiemChungRepo.saveAll(nguoiDungTiemChungSet);
        return;
    }

    @Override
    public void UpdateLichHenTiemChung(LichHenTiemChungDTO lichHenTiemChungDTO) {

    }

    @Override
    public void DeleteLichHenTiemChung(Integer id) {

    }

    @Override
    public void DeleteLichHenTiemChungList(List<Integer> Ids){
        this.lichHenTiemChungRepo.deleteAllByIdInBatch(Ids);
    }

    @Override
    public List<LichHenTiemChungDTO> GetAllLichHenTiemChung() {
        List<LichHenTiemChung> listLichHenTiemChung = lichHenTiemChungRepo.findAll();
        List<LichHenTiemChungDTO> listLichHenTiemChungDTO = this.lichHenTiemChungMapper.toListLichHenTiemChungDTO(listLichHenTiemChung);

        return listLichHenTiemChungDTO;
    }
}
