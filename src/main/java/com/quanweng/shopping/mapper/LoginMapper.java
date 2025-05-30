package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.Login;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    void register(Login login);

    Login findTheLogin(Login login);

    void updateLogin(Login login);

    @Select("select * from login_table where phone = #{phone}")
    Login getLoginByPhone(String phone);
}
