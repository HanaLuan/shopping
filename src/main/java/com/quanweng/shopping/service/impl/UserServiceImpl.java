package com.quanweng.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.quanweng.shopping.mapper.LoginMapper;
import com.quanweng.shopping.mapper.UserMapper;
import com.quanweng.shopping.pojo.Login;
import com.quanweng.shopping.pojo.User;
import com.quanweng.shopping.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public List<User> getAllUser(Integer pages,Integer size) {
        if (pages != null && size != null) {
            PageHelper.startPage(pages, size);
        }
        List<User> userList = userMapper.getAllUser();
        return userList;
    }

    @Override
    public void createUser(User user) {
        if (user.getUserPhone().contains(" ")){
            user.setUserPhone(user.getUserPhone().replaceAll(" ",""));
        }
        if (userMapper.getUserByPhone(user.getUserPhone()) == null) {
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            userMapper.createUser(user);
        }
    }

    @Override
    public void updateUser(User user){
        if (user.getUserPhone().contains(" ")){
            user.setUserPhone(user.getUserPhone().replaceAll(" ",""));
        }
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User getUserByPhone(String phone) {
        User user = userMapper.getUserByPhone(phone);
        if (user == null){
            user = userMapper.getUserByEmail(phone);
        }
        return user;
    }

    @Override
    public List<User> getUserByAdminId(Long adminId,Integer pages,Integer size) {
        if (pages != null && size != null) {
            PageHelper.startPage(pages, size);
        }
        List<User> userList = userMapper.getUserByAdmin(adminId);
        return userList;
    }

    @Override
    public Integer getAllUserCount() {
        return userMapper.getAllUserCount();
    }

    @Override
    public Integer getUserByAdminIdCount(Long adminId) {
        return userMapper.getUserByAdminIdCount(adminId);
    }
}
