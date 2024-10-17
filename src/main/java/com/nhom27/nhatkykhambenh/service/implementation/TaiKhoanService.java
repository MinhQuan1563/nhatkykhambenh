package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.dto.TaiKhoanDTO;
import com.nhom27.nhatkykhambenh.model.TaiKhoan;
import com.nhom27.nhatkykhambenh.repository.ITaiKhoanRepo;
import com.nhom27.nhatkykhambenh.service.interfaces.ITaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaiKhoanService implements ITaiKhoanService {
    @Autowired
    private ITaiKhoanRepo taiKhoanRepo;
    @Override
    public void saveTaiKhoan(TaiKhoan taiKhoan) {
        try{
            taiKhoanRepo.save(taiKhoan);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public TaiKhoanDTO findById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public TaiKhoan findByTaiKhoan(String soDienThoai,String matKhau) {
        try{
            //TaiKhoan taikhoan=new TaiKhoan();
//            List<TaiKhoan> listTaiKhoan = taiKhoanRepo.findAll();
//            for (TaiKhoan tk : listTaiKhoan) {
//                if(tk.getSoDienThoai().equals(soDienThoai) && tk.getMatKhau().equals(matKhau)){
//                    return tk;
//                }
//            }
            return taiKhoanRepo.findBySoDienThoaiAndMatKhau(soDienThoai, matKhau);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
