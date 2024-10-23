package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.dto.NguoiDungDTO;
import com.nhom27.nhatkykhambenh.dto.TaiKhoanDTO;
import com.nhom27.nhatkykhambenh.exception.SaveDataException;
import com.nhom27.nhatkykhambenh.mapper.NguoiDungMapper;
import com.nhom27.nhatkykhambenh.mapper.TaiKhoanMapper;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import com.nhom27.nhatkykhambenh.model.TaiKhoan;
import com.nhom27.nhatkykhambenh.repository.INguoiDungRepo;
import com.nhom27.nhatkykhambenh.repository.ITaiKhoanRepo;
import com.nhom27.nhatkykhambenh.service.interfaces.ITaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TaiKhoanService implements ITaiKhoanService {

    @Autowired
    private ITaiKhoanRepo taiKhoanRepo;

    @Autowired
    private TaiKhoanMapper taiKhoanMapper;

    @Autowired
    private INguoiDungRepo nguoiDungRepo;

    @Autowired
    private NguoiDungMapper nguoiDungMapper;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public void saveTaiKhoan(TaiKhoanDTO taiKhoanDTO) {
        TaiKhoan taikhoan = taiKhoanMapper.toTaiKhoan(taiKhoanDTO);
        taikhoan.setTrangThai(true);
        try {
            taiKhoanRepo.save(taikhoan);
        } catch (Exception e) {
            throw new SaveDataException("TaiKhoan");
        }
    }

    @Override
    public TaiKhoanDTO findById(Integer id) {
        TaiKhoan taiKhoan = taiKhoanRepo.findById(id).get();
        return taiKhoanMapper.toTaiKhoanDTO(taiKhoan);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Boolean checkTaiKhoanExist(String tenTaiKhoan, String matKhau) {
        TaiKhoan taiKhoan = taiKhoanRepo.findByTaiKhoan(tenTaiKhoan);

        if(taiKhoan != null) {
            return taiKhoan.getMatKhau().equals(matKhau);
        }

        return false;
    }

    @Override
    public TaiKhoanDTO getTaiKhoanByName(String name) {
        TaiKhoan taiKhoan = taiKhoanRepo.findByTaiKhoan(name);

        if (taiKhoan != null) {
            return taiKhoanMapper.toTaiKhoanDTO(taiKhoan);
        }
        return null;
    }

    @Override
    public TaiKhoanDTO registerUser(TaiKhoanDTO taiKhoanDTO, NguoiDungDTO nguoiDungDTO) {
        if (taiKhoanRepo.findByTaiKhoan(nguoiDungDTO.getSoDienThoai()) != null) {
            throw new RuntimeException("Tài khoản đã tồn tại!");
        }

        NguoiDung nguoiDung = nguoiDungMapper.toNguoiDung(nguoiDungDTO);
        nguoiDung.setTrangThai(true);
        nguoiDungRepo.save(nguoiDung);

        TaiKhoan taiKhoan = taiKhoanMapper.toTaiKhoan(taiKhoanDTO);
        taiKhoan.setMaNguoiDung(nguoiDung.getMaNguoiDung());
        taiKhoan.setTaiKhoan(nguoiDungDTO.getSoDienThoai());
        taiKhoan.setTrangThai(true);
        taiKhoanRepo.save(taiKhoan);

        return taiKhoanDTO;
    }

    @Override
    public TaiKhoan findBySoDienThoaiAndMatKhau(String soDienThoai, String matKhau) {
        for (TaiKhoan i:taiKhoanRepo.findAll()){
            if(i.getMatKhau().equals(matKhau) && i.getSoDienThoai().equals(soDienThoai)){
                return i;
            }
        }
        return null;
    }
}
