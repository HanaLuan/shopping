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
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/account")
    private Result getAllAdmin(@RequestParam(required = false) Integer pages,
                               @RequestParam(required = false) Integer size){
        List<Admin> adminList = adminService.getAllAdmin(pages,size);
        log.info("查询全部管理员用户{}",adminList);
        Integer total = adminService.getAllAdminCount();
        return Result.success(Map.of("total",total,"list",adminList));
    }

    @PostMapping("/account")
    private Result createAdmin(@RequestBody Admin admin) throws Exception {
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
    private Result getAdminByAdminFrom(@PathVariable Long adminFrom,
                                       @RequestParam(required = false) Integer pages,
                                       @RequestParam(required = false) Integer size){
        List<Admin> adminList = adminService.getAdminByAdminFrom(adminFrom,pages,size);
        Integer total = adminService.getAdminByAdminFromCount(adminFrom);
        return Result.success(Map.of("total",total,"list",adminList));
    }

    @GetMapping("/accountById/{id}")
    private Result getAdminById(@PathVariable Long id){
        Admin admin = adminService.getAdminById(id);
        return Result.success(admin);
    }

    @PostMapping("/accountBan/{id}")
    private Result banTheAdmin(@PathVariable Long id){
        adminService.banTheAdmin(id);
        return Result.success();
    }

}
