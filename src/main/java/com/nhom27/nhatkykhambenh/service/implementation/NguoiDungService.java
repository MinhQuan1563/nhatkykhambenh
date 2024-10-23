package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.dto.NguoiDungDTO;
import com.nhom27.nhatkykhambenh.service.interfaces.INguoiDungService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import com.nhom27.nhatkykhambenh.repository.INguoiDungRepo;
import com.nhom27.nhatkykhambenh.service.interfaces.INguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nhom27.nhatkykhambenh.mapper.NguoiDungMapper;

@Service
public class NguoiDungService implements INguoiDungService {
    @Autowired
    private INguoiDungRepo nguoiDungRepo;
  
    @Autowired
    private NguoiDungMapper nguoiDungMapper;
  
    @Override
    public Page<NguoiDungDTO> getDSNguoiDung(Pageable pageable, String query) {
        return null;
    }

    @Override
    public void saveNguoiDung(NguoiDungDTO nguoiDungDTO) {
        NguoiDung nguoiDung = nguoiDungMapper.toNguoiDung(nguoiDungDTO);
        try{
            nguoiDungRepo.save(nguoiDung);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public NguoiDungDTO findById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
