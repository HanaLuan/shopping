package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.pojo.DTO.ChatRequest;
import com.quanweng.shopping.pojo.DTO.ChatResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SiliconFlowService {

    @Value("${siliconflow.api.url}")
    private String apiUrl;

    @Value("${siliconflow.api.key}")
    private String apiKey;

    @Value("${siliconflow.api.model}")
    private String apiModel;

    private String prompt = "请识别图片中的文字内容，并严格按照以下JSON格式输出数据。若文字内容无法匹配对应字段则忽略，一个词只能填一处空位：\n" +
            "{\n" +
            "  \"phone\": \"\",\n" +
            "  \"email\": \"\",\n" +
            "  \"chineseName\": \"\",\n" +
            "  \"englishName\": \"\",\n" +
            "  \"country\": \"\",\n" +
            "  \"province\": \"\",\n" +
            "  \"city\": \"\",\n" +
            "  \"address\": \"\",\n" +
            "  \"emailCode\": \"\",\n" +
            "  \"remark\": \"\"\n" +
            "}";

    private final RestTemplate restTemplate = new RestTemplate();

    public String chatWithImage(String imageUrl) {
        log.info("url:"+imageUrl);


        // 最大重试次数（首次调用 + 4次重试）
        int maxAttempts = 5;
        // 初始重试等待时间（毫秒）
        long initialDelay = 100;
        // 指数退避因子
        int backoffFactor = 2;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                // 构建请求（与原有逻辑相同）
                HttpHeaders headers = new HttpHeaders();
                headers.setBearerAuth(apiKey);
                headers.setContentType(MediaType.APPLICATION_JSON);

                ChatRequest.Content.ImageUrl imageData = new ChatRequest.Content.ImageUrl();
                imageData.setUrl(imageUrl);
                imageData.setDetail("high");

                ChatRequest.Content imageContent = new ChatRequest.Content();
                imageContent.setType("image_url");
                imageContent.setImage_url(imageData);

                ChatRequest.Content textContent = new ChatRequest.Content();
                textContent.setType("text");
                textContent.setText(prompt);

                ChatRequest.Message message = new ChatRequest.Message();
                message.setRole("user");
                message.setContent(List.of(imageContent, textContent));

                ChatRequest request = new ChatRequest();
                request.setModel(apiModel);
                request.setMessages(List.of(message));

                // 发送请求
                HttpEntity<ChatRequest> entity = new HttpEntity<>(request, headers);
                ResponseEntity<ChatResponse> response = restTemplate.exchange(
                        apiUrl,
                        HttpMethod.POST,
                        entity,
                        ChatResponse.class
                );

                // 处理成功响应
                if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                    return response.getBody().getChoices().get(0).getMessage().getContent();
                } else {
                    log.warn("API调用失败，状态码: {}，尝试次数: {}/{}",
                            response.getStatusCode(), attempt, maxAttempts);
                }
            } catch (Exception e) {
                log.error("API调用异常: {}，尝试次数: {}/{}",
                        e.getMessage(), attempt, maxAttempts, e);
            }

            // 若非最后一次尝试，执行退避等待
            if (attempt < maxAttempts) {
                long waitTime = initialDelay * (long) Math.pow(backoffFactor, attempt - 1);
                try {
                    TimeUnit.MILLISECONDS.sleep(waitTime);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("重试等待被中断", ie);
                }
            }
        }
        throw new RuntimeException("API调用失败，已达最大重试次数: " + maxAttempts);
    }
}