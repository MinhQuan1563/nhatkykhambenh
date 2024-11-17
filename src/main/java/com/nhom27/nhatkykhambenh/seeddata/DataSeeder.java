package com.nhom27.nhatkykhambenh.seeddata;

import com.nhom27.nhatkykhambenh.enums.MoiQuanHe;
import com.nhom27.nhatkykhambenh.model.*;
import com.nhom27.nhatkykhambenh.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private IGiaDinhRepo giaDinhRepo;

    @Autowired
    private INguoiDungRepo nguoiDungRepo;

    @Autowired
    private ITaiKhoanRepo taiKhoanRepo;

    @Autowired
    private ITongQuanRepo tongQuanRepo;

    @Autowired
    private IChiSoRepo chiSoRepo;

    @Autowired
    private IChiTietChiSoRepo chiTietChiSoRepo;

    @Autowired
    private ITiemChungRepo tiemChungRepo;

    @Autowired
    private IChiTietTiemChungRepo chiTietTiemChungRepo;

    @Autowired
    private IKhamBenhRepo khamBenhRepo;

    @Autowired
    private  IChiTietKhamBenhRepo chiTietKhamBenhRepo;


    @Override
    public void run(String... args) throws Exception {
        // Seed data cho GiaDinh
        if(giaDinhRepo.count() == 0) {
            List<GiaDinh> giaDinhList = List.of(
                new GiaDinh(null, 3, true, null, new HashSet<>()),
                new GiaDinh(null, 3, true, null, new HashSet<>()),
                new GiaDinh(null, 4, true, null, new HashSet<>())
            );

            giaDinhRepo.saveAll(giaDinhList);
            System.out.println("Save GiaDinh to Database size = " + giaDinhRepo.count());
        }
        // Seed data cho NguoiDung
        if (nguoiDungRepo.count() == 0) {
            List<NguoiDung> nguoiDungList = List.of(
                new NguoiDung(null, "image1.jpg", "0912345678", "123456789", LocalDate.of(2003, 6, 15), "Nam", "123 Address St", "Nguyen Van A", "nguyenvana@gmail.com", MoiQuanHe.TOI, true, giaDinhRepo.findAll().get(0), null, null, new HashSet<>(), null, null),
                new NguoiDung(null, "image2.jpg", "0912345679", "123456780", LocalDate.of(1972, 4, 29), "Nữ", "456 Address St", "Le Thi B", "lethib@gmail.com", MoiQuanHe.ME, true, giaDinhRepo.findAll().get(0), null, null, new HashSet<>(), null, null),
                new NguoiDung(null, "image3.jpg", "0912345680", "123456781", LocalDate.of(1963, 9, 12), "Nam", "789 Address St", "Tran Van C", "tranvanc@gmail.com", MoiQuanHe.CHA, true, giaDinhRepo.findAll().get(0), null, null, new HashSet<>(), null, null)
            );

            nguoiDungRepo.saveAll(nguoiDungList);
            System.out.println("Save NguoiDung to Database size = " + nguoiDungRepo.count());
        }

        // Seed data cho TaiKhoan
        if (taiKhoanRepo.count() == 0) {
            NguoiDung nguoiDungFirst = nguoiDungRepo.findAll().stream().findFirst().orElse(null);
            GiaDinh giaDinhFirst = giaDinhRepo.findAll().stream().findFirst().orElse(null);

            TaiKhoan taiKhoan = new TaiKhoan(nguoiDungFirst.getMaNguoiDung(), "Do Minh Quan", "123", "123", true, giaDinhFirst, null, nguoiDungFirst);

            taiKhoanRepo.save(taiKhoan);
            System.out.println("Save TaiKhoan to Database size = " + taiKhoanRepo.count());
        }

        // Seed data cho TongQuan
        if (tongQuanRepo.count() == 0) {
            List<NguoiDung> dsNguoiDung = nguoiDungRepo.findAll();

            for(NguoiDung nguoiDung : dsNguoiDung) {
                if(nguoiDung.getMaNguoiDung() == 1) {
                    TongQuan tongQuan = new TongQuan();
                    tongQuan.setDuongHuyet("120/80");
                    tongQuan.setNhipTim("72");
                    tongQuan.setHuyetAp("120/80");
                    tongQuan.setNhietDo("37.5");
                    tongQuan.setChieuCao("170");
                    tongQuan.setCanNang("70");
                    tongQuan.setChiSoBMI("24.2");
                    tongQuan.setNhomMau("O+");
                    tongQuan.setNguoiDung(nguoiDung);

                    tongQuanRepo.save(tongQuan);
                    System.out.println("Seed TongQuan for user: " + nguoiDung.getMaNguoiDung());
                }
                else {
                    TongQuan tongQuan = new TongQuan();
                    tongQuan.setNguoiDung(nguoiDung);

                    tongQuanRepo.save(tongQuan);
                    System.out.println("Seed TongQuan for user: " + nguoiDung.getMaNguoiDung());
                }
            }
        }

        // Seed data cho ChiSo
        if(chiSoRepo.count() == 0) {
            List<ChiSo> chiSoList = List.of(
                new ChiSo(null, "canNang", "Cân nặng", "Kg", true),
                new ChiSo(null, "chiSoBMI", "Chỉ số BMI", "", true),
                new ChiSo(null, "chieuCao", "Chiều cao", "Cm", true),
                new ChiSo(null, "duongHuyet", "Đường huyết", "mg/dL", true),
                new ChiSo(null, "huyetAp", "Huyết áp", "mmHg", true),
                new ChiSo(null, "nhietDo", "Nhiệt độ cơ thể", "°C", true),
                new ChiSo(null, "nhipTim", "Nhịp tim", "bpm", true),
                new ChiSo(null, "nhomMau", "Nhóm máu", "", true)
            );

            chiSoRepo.saveAll(chiSoList);
            System.out.println("Save ChiSo to Database size = " + chiSoRepo.count());
        }

        // Seed data cho ChiTietChiSo
        if(chiTietChiSoRepo.count() == 0) {
            TongQuan tongQuan = tongQuanRepo.findById(1).orElse(null);
            ChiSo chiSo = chiSoRepo.findById(4).orElse(null);
            int giaTriDo = 60;

            if (tongQuan != null && chiSo != null) {
                List<ChiTietChiSo> chiTietChiSoList = new ArrayList<>();

                LocalDate today = LocalDate.now();

                for (int i = 0; i < 10; i++) {
                    LocalDate thoiGianDoLocalDate = today.minusDays(i);
                    Date thoiGianDo = java.sql.Date.valueOf(thoiGianDoLocalDate);

                    ChiTietChiSo chiTietChiSo = new ChiTietChiSo(
                            chiSo.getMaChiSo(),
                            tongQuan.getMaTongQuan(),
                            thoiGianDo,
                            String.valueOf(giaTriDo + 10*i),
                            null,
                            null
                    );

                    chiTietChiSoList.add(chiTietChiSo);
                }

                chiTietChiSoRepo.saveAll(chiTietChiSoList);
                System.out.println("Saved " + chiTietChiSoList.size() + " ChiTietChiSo records to the database.");
            }
        }

        // Seed data cho TiemChung
        if(tiemChungRepo.count() == 0) {
            List<TiemChung> tiemChungList = List.of(
                    new TiemChung(null, "Bệnh viện Đa khoa", LocalDateTime.of(2024, 12, 10, 13, 20), "Y tá A", 1, true),
                    new TiemChung(null, "Trung tâm Y tế Quận 1", LocalDateTime.of(2025, 1, 15, 3, 30), "Bác sĩ B", 2, true),
                    new TiemChung(null, "Phòng khám tư nhân", LocalDateTime.of(2024, 11, 30, 12, 0), "Y tá B", 3, true),
                    new TiemChung(null, "Bệnh viện Đa khoa", LocalDateTime.of(2024, 12, 11, 9, 0), "Bác sĩ A", 1, true),
                    new TiemChung(null, "Bệnh viện Đa khoa", LocalDateTime.of(2024, 12, 12, 10, 0), "Y tá E", 1, true),
                    new TiemChung(null, "Bệnh viện Đa khoa", LocalDateTime.of(2024, 12, 14, 15, 0), "Y tá F", 1, true),
                    new TiemChung(null, "Trung tâm Y tế Quận 1", LocalDateTime.of(2024, 12, 20, 12, 0), "Y tá C", 1, true)

            );

            tiemChungRepo.saveAll(tiemChungList);
            System.out.println("Save TiemChung to Database size = " + tiemChungRepo.count());
        }

        if(chiTietTiemChungRepo.count() == 0) {
            NguoiDung nguoiDung = nguoiDungRepo.findAll().stream().findFirst().orElse(null);
            TiemChung tiemChung = tiemChungRepo.findAll().stream().findFirst().orElse(null);

            if(nguoiDung.getMaNguoiDung() != null && tiemChung.getMaTiemChung() != null) {
                List<ChiTietTiemChung> chiTietTiemChungList = List.of(
                    new ChiTietTiemChung(tiemChung.getMaTiemChung(), nguoiDung.getMaNguoiDung(), "COVID-19 Vaccine AstraZeneca", true, null, null),
                    new ChiTietTiemChung(tiemChung.getMaTiemChung(), nguoiDung.getMaNguoiDung(), "COVID-19 Vaccine Pfizer", true, null, null),
                    new ChiTietTiemChung(tiemChung.getMaTiemChung(), nguoiDung.getMaNguoiDung(), "COVID-19 Vaccine Moderna", true, null, null),
                    new ChiTietTiemChung(2, nguoiDung.getMaNguoiDung(), "Cúm Vaccine", true, null, null),
                    new ChiTietTiemChung(7, nguoiDung.getMaNguoiDung(), "Uốn ván Vaccine", true, null, null)
                );

                chiTietTiemChungRepo.saveAll(chiTietTiemChungList);
                System.out.println("Saved " + chiTietTiemChungList.size() + " ChiTietTiemChung records to the database.");
            }

        }

        // Seed data cho KhamBenh
        if (khamBenhRepo.count() == 0) {
            List<KhamBenh> khamBenhList = List.of(
                new KhamBenh(null, "Bệnh viện Đa khoa TP.HCM", LocalDate.of(2024, 12, 10), true, nguoiDungRepo.findById(1).orElse(null), new HashSet<>(), new HashSet<>(), null),
                new KhamBenh(null, "Bệnh viện Y học Cổ truyền", LocalDate.of(2024, 12, 10), true, nguoiDungRepo.findById(2).orElse(null), new HashSet<>(), new HashSet<>(), null),
                new KhamBenh(null, "Bệnh viện Nhi Đồng", LocalDate.of(2024, 12, 10), true, nguoiDungRepo.findById(3).orElse(null), new HashSet<>(), new HashSet<>(), null)
            );

            khamBenhRepo.saveAll(khamBenhList);
            System.out.println("Saved " + khamBenhList.size() + " KhamBenh records to the database.");
        }

        if (khamBenhRepo.count() == 0) {
            List<KhamBenh> khamBenhList = List.of(
                    new KhamBenh(null, "Bệnh viện Đa khoa TP.HCM", LocalDate.of(2024, 12, 10), true, nguoiDungRepo.findById(1).orElse(null), new HashSet<>(), new HashSet<>(), null),
                    new KhamBenh(null, "Bệnh viện Y học Cổ truyền", LocalDate.of(2024, 12, 10), true, nguoiDungRepo.findById(2).orElse(null), new HashSet<>(), new HashSet<>(), null),
                    new KhamBenh(null, "Bệnh viện Nhi Đồng", LocalDate.of(2024, 12, 10), true, nguoiDungRepo.findById(3).orElse(null), new HashSet<>(), new HashSet<>(), null)
            );

            khamBenhRepo.saveAll(khamBenhList);
        }

        // Seed ChiTietKhamBenh
        if (chiTietKhamBenhRepo.count() == 0) {
            KhamBenh khamBenh = khamBenhRepo.findAll().stream().findFirst().orElse(null);

            List<ChiTietKhamBenh> chiTietKhamBenhList = List.of(
                    new ChiTietKhamBenh(null, "Khoa Nội", "BS. Nguyễn Văn A", "Xét nghiệm máu", "Cảm cúm", "O", true, khamBenh, new HashSet<>(), new HashSet<>()),
                    new ChiTietKhamBenh(null, "Khoa Nhi", "BS. Trần Văn B", "Siêu âm bụng", "Viêm ruột", "A", true, khamBenh, new HashSet<>(), new HashSet<>()),
                    new ChiTietKhamBenh(null, "Khoa Ngoại", "BS. Lê Thị C", "CT Scan", "Chấn thương đầu", "B", true, khamBenh, new HashSet<>(), new HashSet<>())
            );

            chiTietKhamBenhRepo.saveAll(chiTietKhamBenhList);
            System.out.println("Saved " + chiTietKhamBenhList.size() + " ChiTietKhamBenh records to the database.");
        }

    }
}
