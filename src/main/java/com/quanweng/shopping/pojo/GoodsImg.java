package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GoodsImg {
    private Long id;
    private Long goodsId;
    private String goodsImg;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
