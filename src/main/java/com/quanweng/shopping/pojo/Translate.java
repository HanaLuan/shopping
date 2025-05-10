package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Translate {
    private Long id;
    private String text;
    private String lang;
    private String translateText;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
