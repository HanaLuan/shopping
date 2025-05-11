package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);
}
