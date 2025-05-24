package com.quanweng.shopping.pojo.VO;


import lombok.Data;

@Data
public class LoginVo {
    private Long userId;
    private String phone;
    private String token;
}
