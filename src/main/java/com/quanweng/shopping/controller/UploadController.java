package com.quanweng.shopping.controller;

import com.quanweng.shopping.pojo.UserTrace;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.pojo.common.UploadProperties;
import com.quanweng.shopping.utils.AliyunOssOperator;
import com.quanweng.shopping.utils.QiniuOssOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    private AliyunOssOperator aliyunOssOperator;
    @Autowired
    private UploadProperties uploadProperties;
    @Autowired
    private QiniuOssOperator qiniuOssOperator;
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


//    @PostMapping(value = "/uploadAliyun",produces = MediaType.APPLICATION_JSON_VALUE)
//    public Result upload(MultipartFile file) throws Exception {
//        log.info("文件上传{}",file.getOriginalFilename());
//        String url = aliyunOssOperator.upload(file.getBytes(),file.getOriginalFilename());
//        log.info(url);
//        return Result.success(url);
//    }

    @PostMapping(value = "/uploadAliyun", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result uploadToQiniu(MultipartFile file) throws Exception {
        log.info("文件上传至七牛云: {}", file.getOriginalFilename());

        // 生成唯一文件名
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID() + extension;

        // 上传至七牛云
        String url = qiniuOssOperator.upload(file.getBytes(), fileName);
        log.info("七牛云文件访问URL: {}", url);

        return Result.success(url);
    }
}
