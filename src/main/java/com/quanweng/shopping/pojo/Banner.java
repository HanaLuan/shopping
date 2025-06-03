package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Banner {
    private Long id;
    private String bannerType;
    private String bannerUrl;
    private String title;
    private String text;
    private String topText;
    private String bottomText;
    private String detailsText;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
