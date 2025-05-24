package com.quanweng.shopping.controller.admin;


import com.quanweng.shopping.pojo.AdminHaveUser;
import com.quanweng.shopping.pojo.User;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.AdminHaveUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminHaveUserController {
    @Autowired
    private AdminHaveUserService adminHaveUserService;


    @GetMapping("/findUserByAdmin/{adminId}")
    private Result findUserByAdmin(@PathVariable Long adminId){
        List<User> userList = adminHaveUserService.findUserByAdmin(adminId);
        return Result.success(userList);
    }

    @PostMapping("/UserIdToAdminId/{userId}")
    private Result findAdminIdByUserId(@PathVariable Long userId){
        Long adminId = adminHaveUserService.findAdminIdByUserId(userId);
        return Result.success(adminId);
    }


}
