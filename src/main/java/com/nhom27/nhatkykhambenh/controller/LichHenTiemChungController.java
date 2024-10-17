package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.dto.LichHenTiemChungDTO;
import com.nhom27.nhatkykhambenh.dto.NguoiDungDTO;
import com.nhom27.nhatkykhambenh.dto.NguoiDungTiemChungDTO;
import com.nhom27.nhatkykhambenh.model.NguoiDung;
import com.nhom27.nhatkykhambenh.model.NguoiDungTiemChung;
import com.nhom27.nhatkykhambenh.model.NguoiDungTiemChungId;
import com.nhom27.nhatkykhambenh.service.interfaces.ILichHenTiemChungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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

    @GetMapping("add")
    public String AddLichHenTiemChung(Model model) {
        LichHenTiemChungDTO lichHenTiemChungDTO = new LichHenTiemChungDTO();
        lichHenTiemChungDTO.setNguoiDungTiemChungList(new HashSet<>());
        model.addAttribute("lichHenTiemChungDTO", lichHenTiemChungDTO);
        //Lấy danh sách thành viên
        List<NguoiDungDTO> members = new ArrayList<>();
        NguoiDungDTO nguoiDung1 = new NguoiDungDTO(
                1, // maNguoiDung
                "hinh1.png", // hinhAnh
                "0909090909", // soDienThoai
                "0123456789", // cccd
                new Timestamp(System.currentTimeMillis()), // ngayThangNamSinh
                "Nam", // gioiTinh
                "Dia chi 1", // diaChi
                "email1@example.com", // email
                true, // trangThai
                101 // maGiaDinh
        );

        NguoiDungDTO nguoiDung2 = new NguoiDungDTO(
                2,
                "hinh2.png",
                "0988888888",
                "9876543210",
                new Timestamp(System.currentTimeMillis()),
                "Nu",
                "Dia chi 2",
                "email2@example.com",
                false,
                102
        );

        NguoiDungDTO nguoiDung3 = new NguoiDungDTO(
                3,
                "hinh3.png",
                "0912121212",
                "1029384756",
                new Timestamp(System.currentTimeMillis()),
                "Nam",
                "Dia chi 3",
                "email3@example.com",
                true,
                103
        );


        members.add(nguoiDung2);
        members.add(nguoiDung1);
        members.add(nguoiDung3);
        model.addAttribute("members", members);
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

}
