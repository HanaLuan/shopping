package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Login {
    private Long id;
    private String phone;
    private String password;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
