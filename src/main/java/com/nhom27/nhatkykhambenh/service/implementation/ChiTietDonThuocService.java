package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.dto.ChiTietDonThuocDTO;
import com.nhom27.nhatkykhambenh.exception.SaveDataException;
import com.nhom27.nhatkykhambenh.mapper.ChiTietDonThuocMapper;
import com.nhom27.nhatkykhambenh.model.ChiTietDonThuoc;
import com.nhom27.nhatkykhambenh.repository.IChiTietDonThuocRepo;
import com.nhom27.nhatkykhambenh.service.interfaces.IChiTietDonThuocService;
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
public class ChiTietDonThuocService implements IChiTietDonThuocService {
    @Autowired
    private IChiTietDonThuocRepo ChiTietDonThuocRepo;

    @Autowired
    private ChiTietDonThuocMapper ChiTietDonThuocMapper;

    @PersistenceContext
    private EntityManager entityManager;

//    @Override
//    public Page<ChiTietDonThuocDTO> getDSChiTietDonThuoc(Pageable pageable, String query) {
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
//        Query nativeQuery = entityManager.createNativeQuery(sql, ChiTietDonThuoc.class);
//        nativeQuery.setParameter("searchTerm", searchTerm);
//        nativeQuery.setParameter("limit", pageable.getPageSize());
//        nativeQuery.setParameter("offset", pageable.getPageNumber() * pageable.getPageSize());
//
//        List<ChiTietDonThuoc> results = nativeQuery.getResultList();
//
//        long totalElements = results.size(); // Tổng số phần tử
//        return new PageImpl<>(chitietdonThuocMapper.toChiTietDonThuocDtoList(results), pageable, totalElements);
//    }

    @Override
    public Page<ChiTietDonThuocDTO> getDSChiTietDonThuoc(Pageable pageable, String query) {
        String searchTerm = "%" + query + "%";
        String columnQuery = "SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS " +
                "WHERE TABLE_NAME = 'chi_tiet_don_thuoc' AND TABLE_SCHEMA = 'nhatkykhambenh'";
        Query columnNativeQuery = entityManager.createNativeQuery(columnQuery);
        String columns = (String) columnNativeQuery.getSingleResult();

        // Tạo truy vấn động với LIMIT và OFFSET
        String sql = "SELECT * FROM chi_tiet_don_thuoc WHERE trang_thai=1 AND CONCAT(" + columns + ") LIKE :searchTerm ";
        Query nativeQuery = entityManager.createNativeQuery(sql, ChiTietDonThuoc.class);
        nativeQuery.setParameter("searchTerm", searchTerm);

        List<ChiTietDonThuoc> results = nativeQuery.getResultList();

        long totalElements = results.size(); // Tổng số phần tử
        return new PageImpl<>(ChiTietDonThuocMapper.toChiTietDonThuocDtoList(results), pageable, totalElements);
    }


    @Override
    public void saveChiTietDonThuoc(ChiTietDonThuocDTO chitietdonThuocDTO) {
        ChiTietDonThuoc chitietdonThuoc = ChiTietDonThuocMapper.toChiTietDonThuoc(chitietdonThuocDTO);
        chitietdonThuoc.setTrangThai(true);
        try {
            ChiTietDonThuocRepo.save(chitietdonThuoc);
        } catch (Exception e) {
            throw new SaveDataException(ChiTietDonThuoc.OBJ_NAME);
        }
    }

    @Override
    public ChiTietDonThuocDTO findById(Integer id) {
        ChiTietDonThuoc chitietdonThuoc = ChiTietDonThuocRepo.findById(id).get();
        return ChiTietDonThuocMapper.toChiTietDonThuocDTO(chitietdonThuoc);
    }

    @Override
    public void deleteById(Integer id) {
        ChiTietDonThuoc chitietdonThuoc = ChiTietDonThuocRepo.findById(id).orElseThrow(() -> new SaveDataException(ChiTietDonThuoc.OBJ_NAME));
        chitietdonThuoc.setTrangThai(false);
        ChiTietDonThuocRepo.save(chitietdonThuoc);
    }

    public void deleteAllByIds(List<Integer> ids) {
        List<ChiTietDonThuoc> chitietdonThuocList = ChiTietDonThuocRepo.findAllById(ids);
        for (ChiTietDonThuoc chitietdonThuoc : chitietdonThuocList) {
            chitietdonThuoc.setTrangThai(false);
        }
        ChiTietDonThuocRepo.saveAll(chitietdonThuocList);
    }
}

