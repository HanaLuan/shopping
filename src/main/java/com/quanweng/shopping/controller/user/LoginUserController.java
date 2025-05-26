package com.quanweng.shopping.controller.user;


import com.google.zxing.WriterException;
import com.quanweng.shopping.pojo.DTO.LoginInfo;
import com.quanweng.shopping.pojo.Login;
import com.quanweng.shopping.pojo.VO.LoginVo;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.pojo.UserTrace;
import com.quanweng.shopping.service.LoginService;
import com.quanweng.shopping.service.UserTraceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
public class LoginUserController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserTraceService userTraceService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/register")
    private Result register(@RequestBody LoginInfo loginInfo) throws IOException, WriterException {
        loginService.register(loginInfo);
        // 记录注册痕迹
        UserTrace trace = new UserTrace();
        trace.setUserId("NO_LOGIN");
        trace.setIp(request.getRemoteAddr());
        trace.setRegion(""); // 地区预留
        trace.setAction("register");
        trace.setActionData(loginInfo.getPhone());
        userTraceService.recordTrace(trace);
        return Result.success();
    }

    @PostMapping("/login")
    private Result login(@RequestBody Login login){
        LoginVo loginVo = loginService.login(login);
        // 记录登录痕迹
        UserTrace trace = new UserTrace();
        trace.setUserId(loginVo.getUserId() != null ? loginVo.getUserId().toString() : "NO_LOGIN");
        trace.setIp(request.getRemoteAddr());
        trace.setRegion(""); // 地区预留
        trace.setAction("login");
        trace.setActionData(login.getPhone());
        userTraceService.recordTrace(trace);
        return Result.success(loginVo);
    }




}
