package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.dto.NguoiDungDTO;
import com.nhom27.nhatkykhambenh.service.interfaces.INguoiDungService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NguoiDungService implements INguoiDungService {
    @Override
    public Page<NguoiDungDTO> getDSNguoiDung(Pageable pageable, String query) {
        return null;
    }

    @Override
    public void saveNguoiDung(NguoiDungDTO nguoiDungDTO) {

    }

    @Override
    public NguoiDungDTO findById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
