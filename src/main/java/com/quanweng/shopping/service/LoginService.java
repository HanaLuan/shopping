package com.quanweng.shopping.service;

import com.google.zxing.WriterException;
import com.quanweng.shopping.pojo.DTO.LoginInfo;
import com.quanweng.shopping.pojo.Login;
import com.quanweng.shopping.pojo.User;
import com.quanweng.shopping.pojo.VO.LoginAdminVo;
import com.quanweng.shopping.pojo.VO.LoginVo;

import java.io.IOException;

public interface LoginService {
    void register(LoginInfo loginInfo) throws Exception;

    LoginVo login(Login login);

    LoginAdminVo loginAdmin(String adminName, String adminPassword);

    User loginIsBan(String phone) throws Exception;
}
