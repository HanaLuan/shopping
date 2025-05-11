package com.quanweng.shopping.pojo;

import lombok.Data;

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
    private String createTime;
    private String updateTime;
}
