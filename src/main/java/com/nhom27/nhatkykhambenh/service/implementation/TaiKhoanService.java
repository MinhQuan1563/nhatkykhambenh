package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.mapper.NguoiDungMapper;
import com.nhom27.nhatkykhambenh.mapper.TaiKhoanMapper;
import com.nhom27.nhatkykhambenh.model.GiaDinh;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import com.nhom27.nhatkykhambenh.model.TaiKhoan;
import com.nhom27.nhatkykhambenh.model.TongQuan;
import com.nhom27.nhatkykhambenh.repository.IGiaDinhRepo;
import com.nhom27.nhatkykhambenh.repository.INguoiDungRepo;
import com.nhom27.nhatkykhambenh.repository.ITaiKhoanRepo;
import com.nhom27.nhatkykhambenh.repository.ITongQuanRepo;
import com.nhom27.nhatkykhambenh.service.interfaces.ITaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaiKhoanService implements ITaiKhoanService {

    @Autowired
    private ITaiKhoanRepo taiKhoanRepo;

    @Autowired
    private INguoiDungRepo nguoiDungRepo;

    @Autowired
    private IGiaDinhRepo giaDinhRepo;

    @Autowired
    private ITongQuanRepo tongQuanRepo;

    @Override
    public void saveTaiKhoan(TaiKhoan taiKhoan) {
        try{
            taiKhoanRepo.save(taiKhoan);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public NguoiDung registerUser(TaiKhoan taiKhoan) {
        if (taiKhoanRepo.findBySoDienThoai(taiKhoan.getSoDienThoai()) != null) {
            throw new RuntimeException("Tài khoản đã tồn tại!");
        }

        GiaDinh giaDinh = new GiaDinh();
        giaDinhRepo.save(giaDinh);

        taiKhoan.setGiaDinh(giaDinh);
        taiKhoanRepo.save(taiKhoan);

        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setSoDienThoai(taiKhoan.getSoDienThoai());
        nguoiDung.setTenNguoiDung(taiKhoan.getTaiKhoan());
        nguoiDung.setMaNguoiDung(taiKhoan.getMaNguoiDung());
        nguoiDungRepo.save(nguoiDung);

        TongQuan tongQuan = new TongQuan();
        tongQuan.setNguoiDung(nguoiDung);
        tongQuanRepo.save(tongQuan);

        return nguoiDung;
    }

    @Override
    public TaiKhoan findBySoDienThoaiAndMatKhau(String soDienThoai, String matKhau) {
        for (TaiKhoan i : taiKhoanRepo.findAll()) {
            if (i.getMatKhau() != null && i.getMatKhau().equals(matKhau) && i.getSoDienThoai().equals(soDienThoai)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public TaiKhoan findById(Integer maNguoiDung) {
        return taiKhoanRepo.findById(maNguoiDung).get();
    }
}