package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.dto.DonThuocDTO;
import com.nhom27.nhatkykhambenh.dto.DonThuocDTO;
import com.nhom27.nhatkykhambenh.exception.SaveDataException;
import com.nhom27.nhatkykhambenh.mapper.DonThuocMapper;
import com.nhom27.nhatkykhambenh.model.DonThuoc;
import com.nhom27.nhatkykhambenh.model.DonThuoc;
import com.nhom27.nhatkykhambenh.repository.IDonThuocRepo;
import com.nhom27.nhatkykhambenh.service.interfaces.IDonThuocService;
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
public class DonThuocService implements IDonThuocService {
    @Autowired
    private IDonThuocRepo donThuocRepo;

    @Autowired
    private DonThuocMapper donThuocMapper;

    @PersistenceContext
    private EntityManager entityManager;

//    @Override
//    public Page<DonThuocDTO> getDSDonThuoc(Pageable pageable, String query) {
//        String searchTerm = "%" + query + "%";
//
//        String columnQuery = "SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS " +
//                "WHERE TABLE_NAME = 'tiem_chung' AND TABLE_SCHEMA = 'nhatkykhambenh'";
//        Query columnNativeQuery = entityManager.createNativeQuery(columnQuery);
//        String columns = (String) columnNativeQuery.getSingleResult();
//
//        // Tạo truy vấn động với LIMIT và OFFSET
//        String sql = "SELECT * FROM tiem_chung WHERE CONCAT(" + columns + ") LIKE :searchTerm " +
//                "LIMIT :limit OFFSET :offset";
//        Query nativeQuery = entityManager.createNativeQuery(sql, DonThuoc.class);
//        nativeQuery.setParameter("searchTerm", searchTerm);
//        nativeQuery.setParameter("limit", pageable.getPageSize());
//        nativeQuery.setParameter("offset", pageable.getPageNumber() * pageable.getPageSize());
//
//        List<DonThuoc> results = nativeQuery.getResultList();
//
//        long totalElements = results.size(); // Tổng số phần tử
//        return new PageImpl<>(donThuocMapper.toDonThuocDtoList(results), pageable, totalElements);
//    }

    @Override
    public Page<DonThuocDTO> getDSDonThuoc(Pageable pageable, String query) {
        String searchTerm = "%" + query + "%";
        String columnQuery = "SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS " +
                "WHERE TABLE_NAME = 'don_thuoc' AND TABLE_SCHEMA = 'nhatkykhambenh'";
        Query columnNativeQuery = entityManager.createNativeQuery(columnQuery);
        String columns = (String) columnNativeQuery.getSingleResult();

        // Tạo truy vấn động với LIMIT và OFFSET
        String sql = "SELECT * FROM don_thuoc WHERE trang_thai=1 AND CONCAT(" + columns + ") LIKE :searchTerm ";
        Query nativeQuery = entityManager.createNativeQuery(sql, DonThuoc.class);
        nativeQuery.setParameter("searchTerm", searchTerm);

        List<DonThuoc> results = nativeQuery.getResultList();

        long totalElements = results.size(); // Tổng số phần tử
        return new PageImpl<>(donThuocMapper.toDonThuocDtoList(results), pageable, totalElements);
    }


    @Override
    public void saveDonThuoc(DonThuocDTO donThuocDTO) {
        DonThuoc donThuoc = donThuocMapper.toDonThuoc(donThuocDTO);
        donThuoc.setTrangThai(true);
        try {
            donThuocRepo.save(donThuoc);
        } catch (Exception e) {
            throw new SaveDataException(DonThuoc.OBJ_NAME);
        }
    }

    @Override
    public DonThuocDTO findById(Integer id) {
        DonThuoc donThuoc = donThuocRepo.findById(id).get();
        return donThuocMapper.toDonThuocDTO(donThuoc);
    }

    @Override
    public void deleteById(Integer id) {
        DonThuoc donThuoc = donThuocRepo.findById(id).orElseThrow(() -> new SaveDataException(DonThuoc.OBJ_NAME));
        donThuoc.setTrangThai(false);
        donThuocRepo.save(donThuoc);
    }

    public void deleteAllByIds(List<Integer> ids) {
        List<DonThuoc> donThuocList = donThuocRepo.findAllById(ids);
        for (DonThuoc donThuoc : donThuocList) {
            donThuoc.setTrangThai(false);
        }
        donThuocRepo.saveAll(donThuocList);
    }
}
