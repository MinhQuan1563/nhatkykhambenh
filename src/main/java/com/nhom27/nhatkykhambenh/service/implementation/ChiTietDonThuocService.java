package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.dto.ChiTietDonThuocDTO;
import com.nhom27.nhatkykhambenh.exception.SaveDataException;
import com.nhom27.nhatkykhambenh.mapper.ChiTietDonThuocMapper;
import com.nhom27.nhatkykhambenh.model.ChiTietDonThuoc;
import com.nhom27.nhatkykhambenh.model.ChiTietKhamBenh;
import com.nhom27.nhatkykhambenh.model.DonThuoc;
import com.nhom27.nhatkykhambenh.model.XetNghiem;
import com.nhom27.nhatkykhambenh.repository.IChiTietDonThuocRepo;
import com.nhom27.nhatkykhambenh.repository.IChiTietKhamBenhRepo;
import com.nhom27.nhatkykhambenh.repository.IDonThuocRepo;
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

    @Autowired
    private IDonThuocRepo donThuocRepo;


    @Override
    public Page<ChiTietDonThuoc> getDSChiTietDonThuoc(Pageable pageable, String query,Integer maDonThuoc) {
        String searchTerm = "%" + query + "%";

        String columnQuery = "SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS " +
                "WHERE TABLE_NAME = 'chi_tiet_don_thuoc' AND TABLE_SCHEMA = 'nhatkykhambenh'";
        Query columnNativeQuery = entityManager.createNativeQuery(columnQuery);
        String columns = (String) columnNativeQuery.getSingleResult();

        // Tạo truy vấn động với LIMIT và OFFSET
        String sql = "SELECT * FROM chi_tiet_don_thuoc WHERE trang_thai = 1 AND ma_don_thuoc = :maDonThuoc AND CONCAT(" + columns + ") LIKE :searchTerm " +
                "LIMIT :limit OFFSET :offset";
        Query nativeQuery = entityManager.createNativeQuery(sql, ChiTietDonThuoc.class);
        nativeQuery.setParameter("maDonThuoc", maDonThuoc);
        nativeQuery.setParameter("searchTerm", searchTerm);
        nativeQuery.setParameter("limit", pageable.getPageSize());
        nativeQuery.setParameter("offset", pageable.getPageNumber() * pageable.getPageSize());

        List<ChiTietDonThuoc> results = nativeQuery.getResultList();

        String countQuery = "SELECT COUNT(*) FROM chi_tiet_don_thuoc WHERE CONCAT(" + columns + ") LIKE :searchTerm";
        Query countNativeQuery = entityManager.createNativeQuery(countQuery);
        countNativeQuery.setParameter("searchTerm", searchTerm);
        long totalElements = ((Number) countNativeQuery.getSingleResult()).longValue();

        return new PageImpl<>(results, pageable, totalElements);
    }


    @Override
    public void saveChiTietDonThuoc(ChiTietDonThuoc chiTietDonThuoc, Integer maDonThuoc) {
        DonThuoc donThuoc = donThuocRepo.findById(maDonThuoc)
                .orElseThrow(() -> new SaveDataException("DonThuoc không tồn tại với id: " + maDonThuoc));

        chiTietDonThuoc.setTrangThai(true); // Kích hoạt trạng thái
        chiTietDonThuoc.setDonThuoc(donThuoc); // Liên kết với DonThuoc

        try {
            ChiTietDonThuocRepo.save(chiTietDonThuoc);
        } catch (Exception e) {
            throw new SaveDataException("Lỗi khi lưu ChiTietDonThuoc: " + e.getMessage());
        }
    }


    @Override
    public ChiTietDonThuoc findById(Integer id) {
        return ChiTietDonThuocRepo.findById(id).get();
    }

    @Override
    public void deleteById(Integer id) {
        ChiTietDonThuoc chiTietDonThuoc = ChiTietDonThuocRepo.findById(id)
                .orElseThrow(() -> new SaveDataException(ChiTietDonThuoc.OBJ_NAME + " không tồn tại với id: " + id));

        chiTietDonThuoc.setTrangThai(false);
        ChiTietDonThuocRepo.save(chiTietDonThuoc);
    }


    @Override
    public void deleteAllByIds(List<Integer> ids) {
        List<ChiTietDonThuoc> chiTietDonThuocList = ChiTietDonThuocRepo.findAllById(ids);

        for (ChiTietDonThuoc chiTietDonThuoc : chiTietDonThuocList) {
            chiTietDonThuoc.setTrangThai(false);
        }

        ChiTietDonThuocRepo.saveAll(chiTietDonThuocList);
    }

}

