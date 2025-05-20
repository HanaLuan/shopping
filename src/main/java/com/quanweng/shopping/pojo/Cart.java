package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Cart {
    private Long id;
    private Long userId;
    private Long goodsId;
    private Integer number;
    private String goodsName;
    private String goodsUrl;
    private LocalDateTime CreateTime;
    private LocalDateTime updateTime;
}
