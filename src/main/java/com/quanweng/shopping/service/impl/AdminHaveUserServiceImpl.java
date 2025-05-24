package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.mapper.AdminHaveUserMapper;
import com.quanweng.shopping.mapper.UserMapper;
import com.quanweng.shopping.pojo.AdminHaveUser;
import com.quanweng.shopping.pojo.User;
import com.quanweng.shopping.service.AdminHaveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminHaveUserServiceImpl implements AdminHaveUserService {
    @Autowired
    private AdminHaveUserMapper adminHaveUserMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findUserByAdmin(Long adminId) {
        List<AdminHaveUser> adminHaveUserList = adminHaveUserMapper.findUserByAdmin(adminId);
        List<User> userList = new ArrayList<>();
        for (AdminHaveUser adminHaveUser:adminHaveUserList){
            User user = userMapper.getUserById(adminHaveUser.getUserId());
            userList.add(user);
        }
        return userList;
    }

    @Override
    public Long findAdminIdByUserId(Long userId) {
        AdminHaveUser adminHaveUser = adminHaveUserMapper.findAdminIdByUserId(userId);
        return adminHaveUser.getAdminId();
    }
}
