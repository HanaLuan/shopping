package com.quanweng.shopping.pojo.VO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GoodsTopVO {
    private Long id;
    private Long goodsId;
    private Long goodsCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer total;
}
