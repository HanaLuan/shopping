package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class KeyTop {
    private Long id;
    private String keyWord;
    private Long keyCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
