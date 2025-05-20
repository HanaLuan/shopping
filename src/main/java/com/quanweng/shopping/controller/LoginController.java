package com.quanweng.shopping.controller;


import com.quanweng.shopping.pojo.Login;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    private Result register(@RequestBody Login login){
        loginService.register(login);
        return Result.success();
    }

    @PostMapping("/login")
    private Result login(@RequestBody Login login){
        String token = loginService.login(login);
        return Result.success(token);
    }

    


}
