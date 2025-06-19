package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Admin {
    private Long id;
    private String adminName;
    private String adminPassword;
    private Integer adminLevel;
    private String adminRemark;
    private Long adminFrom;
    private Integer adminStatus;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
