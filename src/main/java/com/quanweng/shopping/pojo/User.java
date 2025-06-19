package com.quanweng.shopping.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String img;
    private String userFirstName;
    private String userLastName;
    private String userJob;
    private String userCom;
    private String userEmail;
    private String userPhone;
    private String userAdd;
    private String userUrl;
    private Long userFrom;
    private Integer userStatus;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
