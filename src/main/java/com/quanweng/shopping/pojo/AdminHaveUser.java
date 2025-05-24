package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdminHaveUser {
    private Long id;
    private Long adminId;
    private Long userId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
