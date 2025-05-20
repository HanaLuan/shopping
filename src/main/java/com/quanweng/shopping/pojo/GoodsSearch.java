package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class GoodsSearch {
    private Long id;
    private Long userId;
    private String keyWord;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
