package com.quanweng.shopping.controller;

import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.pojo.common.UploadProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private UploadProperties uploadProperties;
    @PostMapping("/upload")
    private Result uploadFile(MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();

        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + extension;

        String url = uploadProperties.getSaveUrl() + fileName;

        File uploadDir = new File(uploadProperties.getSaveUrl());

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        file.transferTo(new File(url));

        return Result.success(url);
    }
}
