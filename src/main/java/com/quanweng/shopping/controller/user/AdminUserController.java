package com.quanweng.shopping.controller.user;

import com.quanweng.shopping.pojo.Admin;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AdminUserController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/accountById/{id}")
    private Result getAdminById(@PathVariable Long id){
        Admin admin = adminService.getAdminById(id);
        return Result.success(admin);
    }
}
