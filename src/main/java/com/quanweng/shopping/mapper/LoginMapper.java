package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.Login;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    void register(Login login);

    Login findTheLogin(Login login);

    void updateLogin(Login login);
}
