package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Goods {
    private Long id;
    private String goodsName;
    private String goodsPrice;
    private String goodsSize;
    private String goodsDetail;
    private Long goodsType;
    private Integer goodsWeight;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
