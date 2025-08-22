package com.quanweng.shopping.pojo.DTO;

import lombok.Data;
import java.util.List;

@Data
public class ChatRequest {
    private String model;
    private List<Message> messages;

    @Data
    public static class Message {
        private String role;
        private List<Content> content;
    }

    @Data
    public static class Content {
        private String type;
        private String text;          // 文本内容
        private ImageUrl image_url;   // 图片内容

        @Data
        public static class ImageUrl {
            private String url;
            private String detail;
        }
    }
}