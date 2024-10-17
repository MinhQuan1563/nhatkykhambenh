package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.dto.NguoiDungDTO;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import com.nhom27.nhatkykhambenh.repository.INguoiDungRepo;
import com.nhom27.nhatkykhambenh.service.interfaces.INguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NguoiDungService implements INguoiDungService {
    @Autowired
    private INguoiDungRepo nguoiDungRepo;
    @Override
    public void saveNguoiDung(NguoiDung nguoiDung) {
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
