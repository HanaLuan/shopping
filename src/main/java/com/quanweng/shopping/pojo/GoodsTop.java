package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GoodsTop {
    private Long id;
    private Long goodsId;
    private Long goodsCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
