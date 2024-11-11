package com.nhom27.nhatkykhambenh.controller;

import com.nhom27.nhatkykhambenh.service.implementation.CloudinaryService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String url = cloudinaryService.uploadFile(file);
        if (url != null) {
            return ResponseEntity.ok(url);
        } else {
            return ResponseEntity.status(500).body("Upload failed");
        }
    }
}
