package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.dto.GiaDinhDTO;
import com.nhom27.nhatkykhambenh.dto.KhamBenhDTO;
import com.nhom27.nhatkykhambenh.mapper.GiaDinhMapper;
import com.nhom27.nhatkykhambenh.mapper.KhamBenhMapper;
import com.nhom27.nhatkykhambenh.mapper.NguoiDungMapper;
import com.nhom27.nhatkykhambenh.model.GiaDinh;
import com.nhom27.nhatkykhambenh.model.KhamBenh;
import com.nhom27.nhatkykhambenh.service.interfaces.IGiaDinhService;
import com.nhom27.nhatkykhambenh.service.interfaces.IKhamBenhService;
import com.nhom27.nhatkykhambenh.service.interfaces.INguoiDungService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ThongKeController {

    @Autowired
    private IGiaDinhService giaDinhService;
    @Autowired
    private GiaDinhMapper giaDinhMapper;
    @Autowired
    private IKhamBenhService khamBenhService;
    @Autowired
    private KhamBenhMapper khamBenhMapper;
    @Autowired
    private INguoiDungService nguoiDungService;
    @Autowired
    private NguoiDungMapper nguoiDungMapper;

    @GetMapping("/admin/dashboard")
    public String Dashboard(Model model) {

        List<GiaDinhDTO> giaDinhDTOList = giaDinhMapper.toGiaDinhDtoList(giaDinhService.getAll());
        List<KhamBenhDTO> khamBenhDTOList = khamBenhMapper.toKhamBenhDtoList(khamBenhService.getAll());

        model.addAttribute("dsGiaDinh", giaDinhDTOList);
        model.addAttribute("dsKhamBenh", khamBenhDTOList);

        return "admin/thongke";
    }

    @GetMapping("/admin/dashboard/filter")
    public String filterTiemChung(
            @RequestParam(required = false) String dateFrom,
            @RequestParam(required = false) String dateTo,
            @RequestParam(required = false) String maGiaDinh,
            Model model) {

        List<GiaDinhDTO> giaDinhDTOList = giaDinhMapper.toGiaDinhDtoList(giaDinhService.getAll());
        List<KhamBenhDTO> khamBenhDTOList = khamBenhMapper.toKhamBenhDtoList(
                khamBenhService.filterKhamBenh(dateFrom, dateTo, maGiaDinh)
        );

        List<Integer> visitsData = getVisitsData(khamBenhDTOList);

        model.addAttribute("dsGiaDinh", giaDinhDTOList);
        model.addAttribute("dsKhamBenh", khamBenhDTOList);
        model.addAttribute("visitsData", visitsData);

        return "admin/thongke";
    }

    private List<Integer> getVisitsData(List<KhamBenhDTO> khamBenhDTOList) {
        // Theo tuan
        List<Integer> visitsData = new ArrayList<>(7);
        for (int i = 0; i < 7; i++) {
            visitsData.add(0);
        }

        for (KhamBenhDTO khamBenh : khamBenhDTOList) {
            int weekDay = khamBenh.getNgayKham().getDayOfWeek().getValue() - 1;
            visitsData.set(weekDay, visitsData.get(weekDay) + 1);
        }

        return visitsData;
    }
}
