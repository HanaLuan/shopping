package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    private Long id;
    private String categoryName;
    private Long categoryFatherId;
    private Integer categoryLevel;
    private Integer categoryWeight;
    private String categoryTip;
    private Integer categoryTipShow;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
