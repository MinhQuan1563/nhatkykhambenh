package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.dto.ChiTietKhamBenhDTO;
import com.nhom27.nhatkykhambenh.exception.SaveDataException;
import com.nhom27.nhatkykhambenh.mapper.ChiTietKhamBenhMapper;
import com.nhom27.nhatkykhambenh.model.ChiTietKhamBenh;
import com.nhom27.nhatkykhambenh.repository.IChiTietKhamBenhRepo;
import com.nhom27.nhatkykhambenh.service.interfaces.IChiTietKhamBenhService;
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
public class ChiTietKhamBenhService implements IChiTietKhamBenhService {
    @Autowired
    private IChiTietKhamBenhRepo ChiTietKhamBenhRepo;

    @Autowired
    private ChiTietKhamBenhMapper chiTietKhamBenhMapper;

    @PersistenceContext
    private EntityManager entityManager;

//    @Override
//    public Page<ChiTietKhamBenhDTO> getDSChiTietKhamBenh(Pageable pageable, String query) {
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
//        Query nativeQuery = entityManager.createNativeQuery(sql, ChiTietKhamBenh.class);
//        nativeQuery.setParameter("searchTerm", searchTerm);
//        nativeQuery.setParameter("limit", pageable.getPageSize());
//        nativeQuery.setParameter("offset", pageable.getPageNumber() * pageable.getPageSize());
//
//        List<ChiTietKhamBenh> results = nativeQuery.getResultList();
//
//        long totalElements = results.size(); // Tổng số phần tử
//        return new PageImpl<>(chitietdonThuocMapper.toChiTietKhamBenh(results), pageable, totalElements);
//    }

    @Override
    public Page<ChiTietKhamBenhDTO> getDSChiTietKhamBenh(Pageable pageable, String query) {
        String searchTerm = "%" + query + "%";
        String columnQuery = "SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS " +
                "WHERE TABLE_NAME = 'chi_tiet_kham_benh' AND TABLE_SCHEMA = 'nhatkykhambenh'";
        Query columnNativeQuery = entityManager.createNativeQuery(columnQuery);
        String columns = (String) columnNativeQuery.getSingleResult();

        // Tạo truy vấn động với LIMIT và OFFSET
        String sql = "SELECT * FROM chi_tiet_kham_benh WHERE trang_thai=1 AND CONCAT(" + columns + ") LIKE :searchTerm ";
        Query nativeQuery = entityManager.createNativeQuery(sql, ChiTietKhamBenh.class);
        nativeQuery.setParameter("searchTerm", searchTerm);

        List<ChiTietKhamBenh> results = nativeQuery.getResultList();

        long totalElements = results.size(); // Tổng số phần tử
        return new PageImpl<>(chiTietKhamBenhMapper.toChiTietKhamBenhDtoList(results), pageable, totalElements);
    }


    @Override
    public void saveChiTietKhamBenh(ChiTietKhamBenhDTO chiTietKhamBenhDTO) {
        ChiTietKhamBenh chiTietKhamBenh = chiTietKhamBenhMapper.toChiTietKhamBenh(chiTietKhamBenhDTO);
        chiTietKhamBenh.setTrangThai(true);
        try {
            ChiTietKhamBenhRepo.save(chiTietKhamBenh);
        } catch (Exception e) {
            throw new SaveDataException(ChiTietKhamBenh.OBJ_NAME);
        }
    }

    @Override
    public ChiTietKhamBenhDTO findById(Integer id) {
        ChiTietKhamBenh chiTietKhamBenh = ChiTietKhamBenhRepo.findById(id).get();
        return chiTietKhamBenhMapper.toChiTietKhamBenhDTO(chiTietKhamBenh);
    }

    @Override
    public void deleteById(Integer id) {
        ChiTietKhamBenh chitietdonThuoc = ChiTietKhamBenhRepo.findById(id).orElseThrow(() -> new SaveDataException(ChiTietKhamBenh.OBJ_NAME));
        chitietdonThuoc.setTrangThai(false);
        ChiTietKhamBenhRepo.save(chitietdonThuoc);
    }

    public void deleteAllByIds(List<Integer> ids) {
        List<ChiTietKhamBenh> chitietdonThuocList = ChiTietKhamBenhRepo.findAllById(ids);
        for (ChiTietKhamBenh chitietdonThuoc : chitietdonThuocList) {
            chitietdonThuoc.setTrangThai(false);
        }
        ChiTietKhamBenhRepo.saveAll(chitietdonThuocList);
    }
}
