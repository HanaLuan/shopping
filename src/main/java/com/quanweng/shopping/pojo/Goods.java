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
    private String goodsType;
    private Integer goodsWeight;
    private String goodsShowImg;
    private String goodsTip;
    private Integer goodsTipShow;
    private String goodsBarCode;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
