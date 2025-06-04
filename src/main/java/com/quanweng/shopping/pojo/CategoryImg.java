package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryImg {
    private Long id;
    private Long categoryId;
    private String categoryImg;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
