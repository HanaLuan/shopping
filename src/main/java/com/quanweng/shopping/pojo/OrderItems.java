package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class OrderItems {
    private Long id;
    private Long orderId;
    private Long itemId;
    private Integer itemQty;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
