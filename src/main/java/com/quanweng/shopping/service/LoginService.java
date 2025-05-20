package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.Login;

public interface LoginService {
    void register(Login login);

    String login(Login login);
}
