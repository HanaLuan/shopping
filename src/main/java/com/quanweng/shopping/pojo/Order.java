package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    private Long id;
    private Long userId;
    private Integer status;
    private Integer delivery;
    private String orderPrice;
    private String orderAdd;
    private String userName;
    private String userPhone;
    private String userEmail;
    private String userCode;
    private String logisticsCom;
    private String logisticsId;
    private Integer logisticsStatus;
    private String orderNote;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
