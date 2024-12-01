package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.dto.TiemChungDetailDTO;
import com.nhom27.nhatkykhambenh.exception.SaveDataException;
import com.nhom27.nhatkykhambenh.model.ChiTietTiemChung;
import com.nhom27.nhatkykhambenh.model.TiemChung;
import com.nhom27.nhatkykhambenh.repository.IChiTietTiemChungRepo;
import com.nhom27.nhatkykhambenh.service.interfaces.IChiTietTiemChungService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChiTietTiemChungService implements IChiTietTiemChungService {

    @Autowired
    private IChiTietTiemChungRepo chiTietTiemChungRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<ChiTietTiemChung> getCTTiemChungByTiemChung(Pageable pageable,
                                                            String query, 
                                                            Integer maTiemChung) {
        String searchTerm = "%" + query + "%";

        String columnQuery = "SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS " +
                "WHERE TABLE_NAME = 'chi_tiet_tiem_chung' AND TABLE_SCHEMA = 'nhatkykhambenh'";
        Query columnNativeQuery = entityManager.createNativeQuery(columnQuery);
        String columns = (String) columnNativeQuery.getSingleResult();

        String sql = "SELECT * FROM chi_tiet_tiem_chung WHERE trang_thai=1 AND ma_tiem_chung= :maTiemChung AND CONCAT(" + columns + ") LIKE :searchTerm " +
                "LIMIT :limit OFFSET :offset";
        Query nativeQuery = entityManager.createNativeQuery(sql, ChiTietTiemChung.class);
        nativeQuery.setParameter("searchTerm", searchTerm);
        nativeQuery.setParameter("maTiemChung", maTiemChung);
        nativeQuery.setParameter("limit", pageable.getPageSize());
        nativeQuery.setParameter("offset", pageable.getPageNumber() * pageable.getPageSize());

        List<ChiTietTiemChung> results = nativeQuery.getResultList();

        String countQuery = "SELECT COUNT(*) FROM chi_tiet_tiem_chung WHERE CONCAT(" + columns + ") LIKE :searchTerm";
        Query countNativeQuery = entityManager.createNativeQuery(countQuery);
        countNativeQuery.setParameter("searchTerm", searchTerm);
        long totalElements = ((Number) countNativeQuery.getSingleResult()).longValue();

        return new PageImpl<>(results, pageable, totalElements);
    }

    @Override
    public void saveTiemChung(ChiTietTiemChung ctTiemChung) {
        ctTiemChung.setTrangThai(true);
        try {
            chiTietTiemChungRepo.save(ctTiemChung);
        } catch (Exception e) {
            throw new SaveDataException("ChiTietTiemChung");
        }
    }

    @Override
    public ChiTietTiemChung findByIds(Integer maTiemChung, Integer maNguoiDung) {
        return chiTietTiemChungRepo.findByMaTiemChungAndMaNguoiDung(maTiemChung, maNguoiDung).orElseThrow(() -> new SaveDataException("ChiTietTiemChung"));
    }

    @Override
    public void deleteByIds(Integer maTiemChung, Integer maNguoiDung) {
        ChiTietTiemChung chiTietTiemChung = findByIds(maTiemChung, maNguoiDung);
        chiTietTiemChung.setTrangThai(false);
        chiTietTiemChungRepo.save(chiTietTiemChung);
    }

    @Override
    public void deleteAllByIds(Integer maTiemChung, List<Integer> maNguoiDung) {
//        List<ChiTietTiemChung> chiTietTiemChungList = chiTietTiemChungRepo.findAllById(
//                maNguoiDung.stream()
//                        .map(maND -> new ChiTietTiemChung.ChiTietTiemChungId(maTiemChung, maND))
//                        .toList()
//        );
//
//        for (ChiTietTiemChung chiTietTiemChung : chiTietTiemChungList) {
//            chiTietTiemChung.setTrangThai(false);
//        }
//
//        chiTietTiemChungRepo.saveAll(chiTietTiemChungList);
    }

    @Override
    public List<ChiTietTiemChung> getAllByNguoiDung(Integer maNguoiDung) {
        return chiTietTiemChungRepo.findAllByMaNguoiDung(maNguoiDung);
    }

    @Override
    public List<TiemChungDetailDTO> getAllTiemChungDetails(Integer maNguoiDung) {
        List<ChiTietTiemChung> dsCTTiemChung = chiTietTiemChungRepo.findAllByMaNguoiDung(maNguoiDung);
        List<TiemChungDetailDTO> result = new ArrayList<>();

        for(ChiTietTiemChung chitiet : dsCTTiemChung) {
            TiemChung tiemchung = chitiet.getTiemChung();

            if(tiemchung != null) {
                TiemChungDetailDTO detailDTO = getTiemChungDetailDTO(chitiet, tiemchung);
                result.add(detailDTO);
            }
        }

        return result;
    }

    private static TiemChungDetailDTO getTiemChungDetailDTO(ChiTietTiemChung chitiet, TiemChung tiemchung) {
        TiemChungDetailDTO detailDTO = new TiemChungDetailDTO();
        detailDTO.setMaTiemChung(tiemchung.getMaTiemChung());
        detailDTO.setMaNguoiDung(chitiet.getMaNguoiDung());
        detailDTO.setNoiTiemChung(tiemchung.getNoiTiemChung());
        detailDTO.setNgayTiem(tiemchung.getNgayTiem());
        detailDTO.setNguoiTiem(tiemchung.getNguoiTiem());
        detailDTO.setSoMuiTiem(tiemchung.getSoMuiTiem());
        detailDTO.setTenVacXin(chitiet.getTenVacXin());

        return detailDTO;
    }
}
