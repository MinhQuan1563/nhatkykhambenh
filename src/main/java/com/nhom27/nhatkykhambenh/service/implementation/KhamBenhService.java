package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.dto.KhamBenhDTO;
import com.nhom27.nhatkykhambenh.dto.NguoiDungDTO;
import com.nhom27.nhatkykhambenh.dto.TiemChungDTO;
import com.nhom27.nhatkykhambenh.exception.SaveDataException;
import com.nhom27.nhatkykhambenh.mapper.KhamBenhMapper;
import com.nhom27.nhatkykhambenh.mapper.NguoiDungMapper;
import com.nhom27.nhatkykhambenh.model.KhamBenh;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import com.nhom27.nhatkykhambenh.model.TiemChung;
import com.nhom27.nhatkykhambenh.repository.IKhamBenhRepo;
import com.nhom27.nhatkykhambenh.service.interfaces.IKhamBenhService;
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
public class KhamBenhService implements IKhamBenhService {
    @Autowired
    private IKhamBenhRepo khamBenhRepo;

    @Autowired
    private KhamBenhMapper khamBenhMapper;

    @Autowired
    private NguoiDungMapper nguoiDungMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<KhamBenhDTO> getDSKhamBenh(Pageable pageable, String query) {
        String searchTerm = "%" + query + "%";

        String columnQuery = "SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS " +
                "WHERE TABLE_NAME = 'kham_benh' AND TABLE_SCHEMA = 'nhatkykhambenh'";
        Query columnNativeQuery = entityManager.createNativeQuery(columnQuery);
        String columns = (String) columnNativeQuery.getSingleResult();

        // Tạo truy vấn động với LIMIT và OFFSET
        String sql = "SELECT * FROM kham_benh WHERE trang_thai=1 AND CONCAT(" + columns + ") LIKE :searchTerm ";
        Query nativeQuery = entityManager.createNativeQuery(sql, KhamBenh.class);
        nativeQuery.setParameter("searchTerm", searchTerm);

        List<KhamBenh> results = nativeQuery.getResultList();

        long totalElements = results.size(); // Tổng số phần tử
        return new PageImpl<>(khamBenhMapper.toKhamBenhDtoList(results), pageable, totalElements);
    }

    @Override
    public void saveKhamBenh(KhamBenhDTO khamBenhDTO) {
        KhamBenh khamBenh = khamBenhMapper.toKhamBenh(khamBenhDTO);
        khamBenh.setTrangThai(true);
        try {
            khamBenhRepo.save(khamBenh);
        } catch (Exception e) {
            throw new SaveDataException(KhamBenh.OBJ_NAME);
        }
    }

    @Override
    public KhamBenhDTO findById(Integer id) {
        KhamBenh khamBenh = khamBenhRepo.findById(id).get();
        return khamBenhMapper.toKhamBenhDTO(khamBenh);
    }

    @Override
    public void deleteById(Integer id) {
        KhamBenh khamBenh = khamBenhRepo.findById(id).orElseThrow(() -> new SaveDataException(KhamBenh.OBJ_NAME));
        khamBenh.setTrangThai(false);
        khamBenhRepo.save(khamBenh);
    }

    public void deleteAllByIds(List<Integer> ids) {
        List<KhamBenh> khamBenhList = khamBenhRepo.findAllById(ids);
        for (KhamBenh khamBenh : khamBenhList) {
            khamBenh.setTrangThai(false);
        }
        khamBenhRepo.saveAll(khamBenhList);
    }
}
