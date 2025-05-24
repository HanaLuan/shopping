package com.quanweng.shopping.controller.admin;

import com.google.zxing.WriterException;
import com.quanweng.shopping.pojo.Admin;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/account")
    private Result getAllAdmin(){
        List<Admin> adminList = adminService.getAllAdmin();
        log.info("查询全部管理员用户{}",adminList);
        return Result.success(adminList);
    }

    @PostMapping("/account")
    private Result createAdmin(@RequestBody Admin admin) throws IOException, WriterException {
        adminService.createAdmin(admin);
        log.info("新增管理员用户{}",admin);
        return Result.success();
    }

    @PutMapping("/account")
    private Result updateAdmin(@RequestBody Admin admin){
        adminService.updateAdmin(admin);
        log.info("更改管理员用户{}",admin);
        return Result.success();
    }

    @DeleteMapping("/account/{id}")
    private Result deleteAdmin(@PathVariable Long id){
        adminService.deleteAdmin(id);
        log.info("删除管理员{}",id);
        return Result.success();
    }

    @GetMapping("/accountByAdminFrom/{adminFrom}")
    private Result getAdminByAdminFrom(@PathVariable Long adminFrom){
        List<Admin> adminList = adminService.getAdminByAdminFrom(adminFrom);
        return Result.success(adminList);
    }

    @GetMapping("/accountById/{id}")
    private Result getAdminById(@PathVariable Long id){
        Admin admin = adminService.getAdminById(id);
        return Result.success(admin);
    }

}
