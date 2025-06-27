package com.quanweng.shopping.controller.user;

import com.quanweng.shopping.pojo.Admin;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.pojo.User;
import com.quanweng.shopping.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class UserUserController {
    @Autowired
    private UserService userService;

    @GetMapping("/customer")
    private Result getAllUser(@RequestParam(required = false) Integer pages,
                              @RequestParam(required = false) Integer size){
        List<User> userList = userService.getAllUser(pages, size);
        log.info("查询用户{}",userList);
        return Result.success(userList);
    }

    @GetMapping("/customer/{id}")
    private Result getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        log.info("根据id查询:{}",user);
        return Result.success(user);
    }

    @PostMapping("/customerByPhone")
    private Result getUserByPhone(@RequestBody User userRequest){
        User user = userService.getUserByPhone(userRequest.getUserPhone());
        log.info("根据phone查询:{}",userRequest.getUserPhone());
        return Result.success(user);
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

    @GetMapping("/customerByAdminId/{adminId}")
    private Result getUserByAdminId(@PathVariable Long adminId,
                                    @RequestParam(required = false) Integer pages,
                                    @RequestParam(required = false) Integer size){
        List<User> userList = userService.getUserByAdminId(adminId,pages,size);
        Integer total = userService.getUserByAdminIdCount(adminId);
        return Result.success(Map.of("total",total,"list",userList));
    }

    @PostMapping("/customerIsAdmin")
    private Result customerIsAdmin(@RequestParam Long userId){
        Admin admin = userService.theUserIsAdmin(userId);
        return Result.success(admin);
    }
}
