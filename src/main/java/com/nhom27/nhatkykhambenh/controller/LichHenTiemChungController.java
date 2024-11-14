package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.dto.LichHenTiemChungDTO;
import com.nhom27.nhatkykhambenh.dto.NguoiDungDTO;
import com.nhom27.nhatkykhambenh.dto.NguoiDungTiemChungDTO;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import com.nhom27.nhatkykhambenh.model.NguoiDungTiemChung;
import com.nhom27.nhatkykhambenh.model.NguoiDungTiemChungId;
import com.nhom27.nhatkykhambenh.service.interfaces.ILichHenTiemChungService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin/lichhentiemchung")
public class LichHenTiemChungController {

    @Autowired
    private ILichHenTiemChungService lichHenTiemChungService;
    @Autowired
    private DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;

    private List<NguoiDung> getMembers(){
        List<NguoiDung> members = new ArrayList<>();

        NguoiDung user1 = NguoiDung.builder()
                .maNguoiDung(1)
                .hinhAnh("image1.png")
                .soDienThoai("0123456789")
                .cccd("123456789012")
                .ngayThangNamSinh(Timestamp.valueOf("1990-01-01 00:00:00"))
                .gioiTinh("Nam")
                .diaChi("123 Đường ABC")
                .tenNguoiDung("Nguyen Van A")
                .email("user1@example.com")
                .matKhau("password1")
                .trangThai(true)
                .build();

        NguoiDung user2 = NguoiDung.builder()
                .maNguoiDung(2)
                .hinhAnh("image2.png")
                .soDienThoai("0987654321")
                .cccd("987654321098")
                .ngayThangNamSinh(Timestamp.valueOf("1992-05-10 00:00:00"))
                .gioiTinh("Nu")
                .diaChi("456 Đường XYZ")
                .tenNguoiDung("Tran Thi B")
                .email("user2@example.com")
                .matKhau("password2")
                .trangThai(true)
                .build();

        NguoiDung user3 = NguoiDung.builder()
                .maNguoiDung(3)
                .hinhAnh("image3.png")
                .soDienThoai("0912345678")
                .cccd("111122223333")
                .ngayThangNamSinh(Timestamp.valueOf("1995-07-20 00:00:00"))
                .gioiTinh("Nam")
                .diaChi("789 Đường QWE")
                .tenNguoiDung("Le Van C")
                .email("user3@example.com")
                .matKhau("password3")
                .trangThai(false)
                .build();

        members.add(user1);
        members.add(user2);
        members.add(user3);
        return members;
    }

    @GetMapping("add")
    public String AddLichHenTiemChung(Model model) {
        LichHenTiemChungDTO lichHenTiemChungDTO = new LichHenTiemChungDTO();
        lichHenTiemChungDTO.setNguoiDungTiemChungList(new HashSet<>());
        model.addAttribute("lichHenTiemChungDTO", lichHenTiemChungDTO);

        model.addAttribute("members", this.getMembers());
        return "/admin/lichhentiemchung/addLichHenTiemChung";
    }

    @PostMapping("/save")
    public String CreateLichHenTiemChung(@ModelAttribute("lichhentiemchung") LichHenTiemChungDTO lichHenTiemChungDTO, @RequestParam("memberIds") List<Integer> memberIds, @RequestParam("tenVaccins") List<String> tenVaccins) {
        Set<NguoiDungTiemChung> nguoiDungTiemChungSet = new HashSet<>();
        for (int i = 0; i < memberIds.size(); i++) {
            nguoiDungTiemChungSet.add(NguoiDungTiemChung.builder()
                    .tenVaccin(tenVaccins.get(i))
                    .nguoiDung(NguoiDung.builder().maNguoiDung(memberIds.get(i)).build())
                    .build());
        }
        this.lichHenTiemChungService.CreateLichHenTiemChung(lichHenTiemChungDTO, nguoiDungTiemChungSet);
        return "redirect:/admin/lichhentiemchung";
    }

    @PostMapping("delete")
    public String DeleteLichHenTiemChung(@RequestParam("deletedIds") List<Integer> deletedIds) {
        this.lichHenTiemChungService.DeleteLichHenTiemChungList(deletedIds);

        return "redirect:/admin/lichhentiemchung";
    }

    @GetMapping("")
    public String ListLichHenTiemChung(Model model) {
        List<LichHenTiemChungDTO> listLichHenTiemChungDTO = this.lichHenTiemChungService.GetAllLichHenTiemChung();
        model.addAttribute("listLichHenTiemChung", listLichHenTiemChungDTO);
        return "/admin/lichhentiemchung/listLichHenTiemChung";
    }

    @GetMapping("{id}")
    public String GetLichHenTiemChungById(@PathVariable("id") Integer id, Model model) {
        LichHenTiemChungDTO lichHenTiemChungDTO = this.lichHenTiemChungService.GetLichHenTiemChungById(id);
        System.out.println(lichHenTiemChungDTO.getNgayHenTiem());
        model.addAttribute("lichHenTiemChung",lichHenTiemChungDTO);
        model.addAttribute("members", this.getMembers());
        System.out.println(lichHenTiemChungDTO.getNgayHenTiem());
        return "/admin/lichhentiemchung/updateLichHenTiemChung";
    }

    @PostMapping("/update")
    public String UpdateLichHenTiemChungById(@ModelAttribute("lichHenTiemChung") LichHenTiemChungDTO lichHenTiemChungDTO, @RequestParam("memberIds") List<Integer> memberIds, @RequestParam("tenVaccins") List<String> tenVaccins){
        Set<NguoiDungTiemChung> nguoiDungTiemChungSet = new HashSet<>();
        for (int i = 0; i < memberIds.size(); i++) {
            nguoiDungTiemChungSet.add(NguoiDungTiemChung.builder()
                    .tenVaccin(tenVaccins.get(i))
                    .nguoiDung(NguoiDung.builder().maNguoiDung(memberIds.get(i)).build())
                    .build());
        }
        this.lichHenTiemChungService.UpdateLichHenTiemChung(lichHenTiemChungDTO, nguoiDungTiemChungSet);
        return "redirect:/admin/lichhentiemchung";
    }
}