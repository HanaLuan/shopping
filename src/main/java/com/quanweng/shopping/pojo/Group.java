package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Group {
    private Long id;
    private String groupName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
