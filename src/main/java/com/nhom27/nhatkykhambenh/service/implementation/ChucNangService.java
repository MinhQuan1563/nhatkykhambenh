package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.model.ChucNang;
import com.nhom27.nhatkykhambenh.repository.IChucNangRepo;
import com.nhom27.nhatkykhambenh.service.interfaces.IChucNangService;
import org.springframework.beans.factory.annotation.Autowired;

public class ChucNangService implements IChucNangService {
    @Autowired
    private IChucNangRepo chucNangRepo;

    @Override
    public void SaveChucnangNang(ChucNang chucnang) {

    }
}
