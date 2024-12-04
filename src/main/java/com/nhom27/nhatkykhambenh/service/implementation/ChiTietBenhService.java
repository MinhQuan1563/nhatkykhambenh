package com.nhom27.nhatkykhambenh.service.implementation;

import com.nhom27.nhatkykhambenh.model.ChiTietBenh;
import com.nhom27.nhatkykhambenh.model.TongQuan;
import com.nhom27.nhatkykhambenh.repository.IChiTietBenhRepo;
import com.nhom27.nhatkykhambenh.service.interfaces.IChiTietBenhService;
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
public class ChiTietBenhService implements IChiTietBenhService {

    @Autowired
    private IChiTietBenhRepo chiTietBenhRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ChiTietBenh> getAllByTongQuan(TongQuan tongQuan, Boolean trangThai) {
        return chiTietBenhRepo.findByTongQuanAndTrangThaiBenhHienTai(tongQuan, trangThai);
    }

    @Override
    public List<ChiTietBenh> getAll() {
        return chiTietBenhRepo.findAll();
    }

    @Override
    public List<ChiTietBenh> filterChiTietBenh(String maGiaDinh) {
        if (maGiaDinh != null && !maGiaDinh.isEmpty()) {
            return chiTietBenhRepo.findByGiaDinh(maGiaDinh);
        } else {
            return chiTietBenhRepo.findAll();
        }
    }

    @Override
    public Page<ChiTietBenh> getDSChiTietBenh(Pageable pageable, String query, Integer maTongQuan) {
        String searchTerm = "%" + query + "%";

        String columnQuery = "SELECT GROUP_CONCAT(COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS " +
                "WHERE TABLE_NAME = 'chi_tiet_benh' AND TABLE_SCHEMA = 'nhatkykhambenh'";
        Query columnNativeQuery = entityManager.createNativeQuery(columnQuery);
        String columns = (String) columnNativeQuery.getSingleResult();

        String sql = "SELECT * FROM chi_tiet_benh WHERE trang_thai = 1 AND ma_tong_quan = :maTongQuan AND CONCAT(" + columns + ") LIKE :searchTerm " +
                "LIMIT :limit OFFSET :offset";
        Query nativeQuery = entityManager.createNativeQuery(sql, ChiTietBenh.class);
        nativeQuery.setParameter("maTongQuan", maTongQuan);
        nativeQuery.setParameter("searchTerm", searchTerm);
        nativeQuery.setParameter("limit", pageable.getPageSize());
        nativeQuery.setParameter("offset", pageable.getPageNumber() * pageable.getPageSize());

        List<ChiTietBenh> results = nativeQuery.getResultList();

        String countQuery = "SELECT COUNT(*) FROM chi_tiet_benh WHERE CONCAT(" + columns + ") LIKE :searchTerm";
        Query countNativeQuery = entityManager.createNativeQuery(countQuery);
        countNativeQuery.setParameter("searchTerm", searchTerm);
        long totalElements = ((Number) countNativeQuery.getSingleResult()).longValue();

        return new PageImpl<>(results, pageable, totalElements);
    }

    @Override
    public void suyDienBenhTuTongQuan(TongQuan tongQuan) {
        List<ChiTietBenh> danhSachBenh = new ArrayList<>();

        if (tongQuan.getDuongHuyet() != null && Double.parseDouble(tongQuan.getDuongHuyet()) > 126) {
            ChiTietBenh chiTietBenh = taoChiTietBenh("Bệnh tiểu đường", tongQuan);
            if (chiTietBenh != null) danhSachBenh.add(chiTietBenh);
        }
        else {
            updateTrangThai("Bệnh tiểu đường", tongQuan);
        }

        if (tongQuan.getNhipTim() != null && Integer.parseInt(tongQuan.getNhipTim()) > 100) {
            ChiTietBenh chiTietBenh = taoChiTietBenh("Nhịp tim nhanh", tongQuan);
            if (chiTietBenh != null) danhSachBenh.add(chiTietBenh);
        }
        else {
            updateTrangThai("Nhịp tim nhanh", tongQuan);
        }

        if (tongQuan.getHuyetAp() != null && huyetApCao(tongQuan.getHuyetAp())) {
            ChiTietBenh chiTietBenh = taoChiTietBenh("Huyết áp cao", tongQuan);
            if (chiTietBenh != null) danhSachBenh.add(chiTietBenh);
        }
        else {
            updateTrangThai("Huyết áp cao", tongQuan);
        }

        if (tongQuan.getNhietDo() != null && Double.parseDouble(tongQuan.getNhietDo()) > 38) {
            ChiTietBenh chiTietBenh = taoChiTietBenh("Sốt cao", tongQuan);
            if (chiTietBenh != null) danhSachBenh.add(chiTietBenh);
        }
        else {
            updateTrangThai("Sốt cao", tongQuan);
        }

        if (tongQuan.getChiSoBMI() != null) {
            double bmi = Double.parseDouble(tongQuan.getChiSoBMI());
            if (bmi > 25) {
                ChiTietBenh chiTietBenh = taoChiTietBenh("Thừa cân/béo phì", tongQuan);
                if (chiTietBenh != null) danhSachBenh.add(chiTietBenh);
            }
            else if (bmi < 18.5) {
                ChiTietBenh chiTietBenh = taoChiTietBenh("Thiếu cân", tongQuan);
                if (chiTietBenh != null) danhSachBenh.add(chiTietBenh);
            }
            else {
                updateTrangThai("Thiếu cân", tongQuan);
                updateTrangThai("Thừa cân/béo phì", tongQuan);
            }
        }

        chiTietBenhRepo.saveAll(danhSachBenh);
    }

    private void updateTrangThai(String tenBenh, TongQuan tongQuan) {
        ChiTietBenh chiTietBenhExist = chiTietBenhRepo.findByTongQuanAndTenBenh(tongQuan, tenBenh);

        if (chiTietBenhExist != null) {
            chiTietBenhExist.setTrangThaiBenhHienTai(false);
            chiTietBenhRepo.save(chiTietBenhExist);
        }

    }

    private ChiTietBenh taoChiTietBenh(String tenBenh, TongQuan tongQuan) {
        ChiTietBenh chiTietBenhExist = chiTietBenhRepo.findByTongQuanAndTenBenh(tongQuan, tenBenh);

        if (chiTietBenhExist != null) {
            chiTietBenhExist.setTrangThaiBenhHienTai(true);
            chiTietBenhRepo.save(chiTietBenhExist);
            return null;
        }

        ChiTietBenh chiTietBenh = new ChiTietBenh();
        chiTietBenh.setTenBenh(tenBenh);
        chiTietBenh.setTrangThaiBenhHienTai(true);
        chiTietBenh.setTrangThai(true);
        chiTietBenh.setTongQuan(tongQuan);

        return chiTietBenh;
    }

    private boolean huyetApCao(String huyetAp) {
        // Huyết áp được lưu dưới dạng "120/80"
        String[] values = huyetAp.split("/");
        if (values.length == 2) {
            int sys = Integer.parseInt(values[0]); // Huyết áp tâm thu
            int dia = Integer.parseInt(values[1]); // Huyết áp tâm trương
            return sys > 140 || dia > 90;
        }
        return false;
    }
}
