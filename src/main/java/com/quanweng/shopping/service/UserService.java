package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser(Integer pages,Integer size);

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    User getUserById(Long id);

    User getUserByPhone(String phone);

    List<User> getUserByAdminId(Long adminId);
}
