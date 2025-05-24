package com.quanweng.shopping.controller.admin;

import com.quanweng.shopping.pojo.Admin;
import com.quanweng.shopping.pojo.VO.LoginAdminVo;
import com.quanweng.shopping.pojo.VO.LoginVo;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin")
public class LoginAdminController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    private Result login(@RequestBody Admin admin){
        String adminName = admin.getAdminName();
        String adminPassword = admin.getAdminPassword();
        LoginAdminVo loginAdminVo = loginService.loginAdmin(adminName,adminPassword);
        return Result.success(loginAdminVo);
    }
}
