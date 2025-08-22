package com.quanweng.shopping.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.io.IOException;

@Service
public class SiliconFlowServiceImpl {

    @Value("${siliconflow.api.url}")
    private String apiUrl;

    @Value("${siliconflow.api.key}")
    private String apiKey;

    public String uploadImageAndChat(MultipartFile imageFile, String userMessage) throws Exception {
        // 准备请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer " + apiKey);

        // 构建多部分请求体
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", new FileSystemResource(convertMultipartFileToFile(imageFile))); // 转换文件
        body.add("text", userMessage); // 对话文本

        // 发送请求
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
            apiUrl,
            HttpMethod.POST,
            requestEntity,
            String.class
        );

        return response.getBody(); // 返回 API 响应
    }

    // 辅助方法：将 MultipartFile 转换为临时 File
    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        file.transferTo(convFile);
        return convFile;
    }
}