package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.exception.SaveDataException;
import com.nhom27.nhatkykhambenh.model.GiaDinh;
import com.nhom27.nhatkykhambenh.model.TaiKhoan;
import com.nhom27.nhatkykhambenh.service.interfaces.INguoiDungService;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import com.nhom27.nhatkykhambenh.repository.INguoiDungRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NguoiDungService implements INguoiDungService {

    @Autowired
    private INguoiDungRepo nguoiDungRepo;

    @Override
    public List<NguoiDung> getAllNguoiDung() {
        return nguoiDungRepo.findByTrangThai(true);
    }

    @Override
    public List<NguoiDung> getDsNguoiDungByGiaDinh(GiaDinh giaDinh) {
        return nguoiDungRepo.findByGiaDinhAndTrangThai(giaDinh, true);
    }

    @Override
    public void saveNguoiDung(NguoiDung nguoiDung, TaiKhoan taiKhoan) {
        nguoiDung.setGiaDinh(taiKhoan.getGiaDinh());
        nguoiDung.setTrangThai(true);

        try {
            nguoiDungRepo.save(nguoiDung);
        } catch (Exception e) {
            throw new SaveDataException("NguoiDung");
        }
    }

    @Override
    public void deleteById(Integer id) {
        NguoiDung nguoiDung = nguoiDungRepo.findById(id).orElseThrow(() -> new SaveDataException("NguoiDung"));
        nguoiDung.setTrangThai(false);
        nguoiDungRepo.save(nguoiDung);
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