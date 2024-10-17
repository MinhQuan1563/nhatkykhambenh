package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.dto.TongQuanDTO;
import com.nhom27.nhatkykhambenh.mapper.TongQuanMapper;
import com.nhom27.nhatkykhambenh.model.TongQuan;
import com.nhom27.nhatkykhambenh.repository.ITongQuanRepo;
import com.nhom27.nhatkykhambenh.service.interfaces.ITongQuanService;
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
public class TongQuanService implements ITongQuanService {

    @Autowired
    private ITongQuanRepo tongQuanRepo;

    @Autowired
    private TongQuanMapper tongQuanMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<TongQuanDTO> getDSTongQuan(Pageable pageable, String query) {
        String searchTerm = "%" + query + "%";

        String columnQuery = "SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS " +
                "WHERE TABLE_NAME = 'tong_quan' AND TABLE_SCHEMA = 'nhatkykhambenh'";
        Query columnNativeQuery = entityManager.createNativeQuery(columnQuery);
        String columns = (String) columnNativeQuery.getSingleResult();

        String sql = "SELECT * FROM tong_quan WHERE trang_thai=1 AND CONCAT(" + columns + ") LIKE :searchTerm ";
        Query nativeQuery = entityManager.createNativeQuery(sql, TongQuan.class);
        nativeQuery.setParameter("searchTerm", searchTerm);

        List<TongQuan> results = nativeQuery.getResultList();

        long totalElements = results.size();
        return new PageImpl<>(tongQuanMapper.toTongQuanDtoList(results), pageable, totalElements);
    }

    @Override
    public void saveTongQuan(TongQuanDTO tongQuanDTO) {

    }

    @Override
    public TongQuanDTO findById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
