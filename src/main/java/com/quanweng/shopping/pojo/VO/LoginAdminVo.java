package com.quanweng.shopping.pojo.VO;

import lombok.Data;

@Data
public class LoginAdminVo {
    private Long Id;
    private String adminName;
    private Integer adminLevel;
    private String token;
}
