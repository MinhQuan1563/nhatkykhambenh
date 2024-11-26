package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.exception.SaveDataException;
import com.nhom27.nhatkykhambenh.mapper.XetNghiemMapper;
import com.nhom27.nhatkykhambenh.model.ChiTietKhamBenh;
import com.nhom27.nhatkykhambenh.model.XetNghiem;
import com.nhom27.nhatkykhambenh.model.KhamBenh;
import com.nhom27.nhatkykhambenh.model.XetNghiem;
import com.nhom27.nhatkykhambenh.repository.IChiTietKhamBenhRepo;
import com.nhom27.nhatkykhambenh.repository.IXetNghiemRepo;
import com.nhom27.nhatkykhambenh.repository.IXetNghiemRepo;
import com.nhom27.nhatkykhambenh.service.interfaces.IXetNghiemService;
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
public class XetNghiemService implements IXetNghiemService {
    @Autowired
    private IXetNghiemRepo XetNghiemRepo;

    @Autowired
    private IChiTietKhamBenhRepo ChiTietKhamBenhRepo;

    @Autowired
    private XetNghiemMapper xetNghiemMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<XetNghiem> getDSXetNghiem(Pageable pageable, String query, Integer maChiTietKhamBenh) {
        String searchTerm = "%" + query + "%";

        String columnQuery = "SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS " +
                "WHERE TABLE_NAME = 'xet_nghiem' AND TABLE_SCHEMA = 'nhatkykhambenh'";
        Query columnNativeQuery = entityManager.createNativeQuery(columnQuery);
        String columns = (String) columnNativeQuery.getSingleResult();

        // Tạo truy vấn động với LIMIT và OFFSET
        String sql = "SELECT * FROM xet_nghiem WHERE trang_thai = 1 AND ma_chi_tiet_kham_benh = :maChiTietKhamBenh AND CONCAT(" + columns + ") LIKE :searchTerm " +
                "LIMIT :limit OFFSET :offset";
        Query nativeQuery = entityManager.createNativeQuery(sql, XetNghiem.class);
        nativeQuery.setParameter("maChiTietKhamBenh", maChiTietKhamBenh);
        nativeQuery.setParameter("searchTerm", searchTerm);
        nativeQuery.setParameter("limit", pageable.getPageSize());
        nativeQuery.setParameter("offset", pageable.getPageNumber() * pageable.getPageSize());

        List<XetNghiem> results = nativeQuery.getResultList();

        String countQuery = "SELECT COUNT(*) FROM xet_nghiem WHERE CONCAT(" + columns + ") LIKE :searchTerm";
        Query countNativeQuery = entityManager.createNativeQuery(countQuery);
        countNativeQuery.setParameter("searchTerm", searchTerm);
        long totalElements = ((Number) countNativeQuery.getSingleResult()).longValue();

        return new PageImpl<>(results, pageable, totalElements);
    }

    @Override
    public void saveXetNghiem(XetNghiem xetNghiem, Integer maChiTietKhamBenh) {
        ChiTietKhamBenh chiTietKhamBenh = ChiTietKhamBenhRepo.findById(maChiTietKhamBenh).get();
        xetNghiem.setTrangThai(true);
        xetNghiem.setMaChiTietKhamBenh(maChiTietKhamBenh);//thêm
        xetNghiem.setChiTietKhamBenh(chiTietKhamBenh);

        try {
            XetNghiemRepo.save(xetNghiem);
        } catch (Exception e) {
            throw new SaveDataException("XetNghiem");
        }
    }

    @Override
    public XetNghiem findById(Integer id) {
        return XetNghiemRepo.findById(id).get();
    }

//    @Override
//    public void deleteById(Integer id) {
//        XetNghiem xetNghiem = findById(id);
//        xetNghiem.setTrangThai(false);
//        XetNghiemRepo.save(xetNghiem);
//    }
//
//    @Override
//    public void deleteAllByIds(List<Integer> ids) {
//        List<XetNghiem> xetNghiemList = XetNghiemRepo.findAllById(ids);
//        for (XetNghiem xetNghiem : xetNghiemList) {
//            xetNghiem.setTrangThai(false);
//        }
//        XetNghiemRepo.saveAll(xetNghiemList);
//    }

    @Override
    public void deleteById(Integer id) {
        XetNghiem xetNghiem = findById(id);
        xetNghiem.setTrangThai(false);  // Ẩn bản ghi thay vì xóa hoàn toàn
        XetNghiemRepo.save(xetNghiem);  // Lưu lại sự thay đổi
    }

    @Override
    public void deleteAllByIds(List<Integer> ids) {
        List<XetNghiem> xetNghiemList = XetNghiemRepo.findAllById(ids);
        for (XetNghiem xetNghiem : xetNghiemList) {
            xetNghiem.setTrangThai(false);  // Ẩn bản ghi thay vì xóa
        }
        XetNghiemRepo.saveAll(xetNghiemList);  // Lưu lại sự thay đổi
    }

}
