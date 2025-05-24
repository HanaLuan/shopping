package com.quanweng.shopping.pojo.DTO;

import lombok.Data;

@Data
public class LoginInfo {
    private String phone;
    private String password;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userAdd;
}
