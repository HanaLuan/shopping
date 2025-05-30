package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NameTop {
    private Long id;
    private String name;
    private Long nameCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
