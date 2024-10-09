package com.nhom27.nhatkykhambenh.service.interfaces;

import com.nhom27.nhatkykhambenh.dto.LichHenTiemChungDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ILichHenTiemChungService {
    public void CreateLichHenTiemChung(LichHenTiemChungDTO lichHenTiemChungDTO);
    public void UpdateLichHenTiemChung(LichHenTiemChungDTO lichHenTiemChungDTO);
    public void DeleteLichHenTiemChung(Integer id);
    public void DeleteLichHenTiemChungList(List<Integer> Ids);
    public List<LichHenTiemChungDTO> GetAllLichHenTiemChung();
}

