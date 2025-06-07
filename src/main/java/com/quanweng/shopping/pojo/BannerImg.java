package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BannerImg {
    private Long id;
    private Long bannerId;
    private String bannerUrl;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
