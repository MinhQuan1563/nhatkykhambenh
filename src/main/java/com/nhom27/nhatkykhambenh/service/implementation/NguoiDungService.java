package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.dto.NguoiDungDTO;
import com.nhom27.nhatkykhambenh.mapper.NguoiDungMapper;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import com.nhom27.nhatkykhambenh.repository.INguoiDungRepo;
import com.nhom27.nhatkykhambenh.service.interfaces.INguoiDungService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NguoiDungService implements INguoiDungService {

    @Autowired
    private INguoiDungRepo nguoiDungRepo;

    @Autowired
    private NguoiDungMapper nguoiDungMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<NguoiDungDTO> getDSNguoiDung(Pageable pageable, String query) {
        String searchTerm = "";

        String columnQuery = "SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS " +
                "WHERE TABLE_NAME = 'nguoi_dung' AND TABLE_SCHEMA = 'nhatkykhambenh'";
        Query columnNativeQuery = entityManager.createNativeQuery(columnQuery);
        String columns = (String) columnNativeQuery.getSingleResult();

        // Tạo truy vấn động với LIMIT và OFFSET
        String sql = "SELECT * FROM nguoi_dung WHERE trang_thai=1 AND CONCAT(" + columns + ") LIKE :searchTerm ";
        Query nativeQuery = entityManager.createNativeQuery(sql, NguoiDung.class);
        nativeQuery.setParameter("searchTerm", searchTerm);

        List<NguoiDung> results = nativeQuery.getResultList();

        System.out.println("result: " + results.size());

        long totalElements = results.size(); // Tổng số phần tử
        return new PageImpl<>(nguoiDungMapper.toNguoiDungDtoList(results), pageable, totalElements);
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
