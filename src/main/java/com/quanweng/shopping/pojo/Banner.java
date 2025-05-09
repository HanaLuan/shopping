package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Banner {
    private Long id;
    private Integer bannerType;
    private String bannerUrl;
    private String title;
    private String text;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
