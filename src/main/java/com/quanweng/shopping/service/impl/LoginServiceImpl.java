package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.mapper.LoginMapper;
import com.quanweng.shopping.mapper.UserMapper;
import com.quanweng.shopping.pojo.Login;
import com.quanweng.shopping.pojo.User;
import com.quanweng.shopping.service.LoginService;
import com.quanweng.shopping.utils.JWTUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private UserMapper userMapper;


    @Override
    public void register(Login login) {

        login.setPassword(DigestUtils.md5DigestAsHex(login.getPassword().getBytes()));
        login.setCreateTime(LocalDateTime.now());
        login.setUpdateTime(LocalDateTime.now());
        loginMapper.register(login);

        User user = new User();
        user.setUserPhone(login.getPhone());
        user.setImg("");
        user.setUserFirstName("");
        user.setUserLastName("");
        user.setUserJob("");
        user.setUserCom("");
        user.setUserEmail("");
        user.setUserAdd("");
        user.setUserUrl("");
        user.setUpdateTime(LocalDateTime.now());
        user.setCreateTime(LocalDateTime.now());
        userMapper.createUser(user);


    }

    @Override
    public String login(Login login) {
        login.setPassword(DigestUtils.md5DigestAsHex(login.getPassword().getBytes()));

        Login logi = loginMapper.findTheLogin(login);
        log.info("登录成功{}",logi);
        if (logi != null){
            Map<String,Object> claims = new HashMap<>();
            claims.put("phone",logi.getPhone());
            String jwt = JWTUtils.generateToken(claims);


            return jwt;
        }

        return "登录失败";
    }
}
