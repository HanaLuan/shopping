package com.quanweng.shopping.controller.admin;

import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.pojo.User;
import com.quanweng.shopping.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/customer")
    private Result getAllUser(){
        List<User> userList = userService.getAllUser();
        log.info("查询用户{}",userList);
        return Result.success(userList);
    }

    @PostMapping("/customer")
    private Result createUser(@RequestBody User user){
        userService.createUser(user);
        log.info("新增用户{}",user);
        return Result.success();
    }

    @PutMapping("/customer")
    private Result updateUser(@RequestBody User user){
        userService.updateUser(user);
        log.info("更新用户{}",user);
        return Result.success();
    }

    @DeleteMapping("/customer/{id}")
    private Result deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        log.info("删除用户{}",id);
        return Result.success();
    }


}
