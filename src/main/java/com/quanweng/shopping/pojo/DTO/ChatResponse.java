package com.quanweng.shopping.pojo.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ChatResponse {
    private String id;
    private List<Choice> choices;

    @Data
    public static class Choice {
        private Message message;

        @Data
        public static class Message {
            private String content;
        }
    }
}