package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoteImg {
    private Long id;
    private Long orderId;
    private String noteUrl;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
