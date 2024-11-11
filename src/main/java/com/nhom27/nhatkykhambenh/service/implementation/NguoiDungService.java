package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.model.NguoiDung;
import com.nhom27.nhatkykhambenh.repository.INguoiDungRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NguoiDungService implements INguoiDungService {

    @Autowired
    private INguoiDungRepo nguoiDungRepo;

    @Override
    public List<NguoiDung> getAllNguoiDung() {
        return nguoiDungRepo.findAll();
    }

    @Override
    public List<NguoiDung> getDsNguoiDungByGiaDinh(GiaDinh giaDinh) {
        return nguoiDungRepo.findByGiaDinh(giaDinh);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public NguoiDung getById(Integer id) {
        return nguoiDungRepo.getById(id);
    }

    @Override
    public NguoiDung findByEmail(String email) {
        return nguoiDungRepo.findByEmail(email);
    }
}