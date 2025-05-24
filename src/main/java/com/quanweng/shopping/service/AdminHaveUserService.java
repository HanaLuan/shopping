package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.AdminHaveUser;
import com.quanweng.shopping.pojo.User;

import java.util.List;

public interface AdminHaveUserService {
    List<User> findUserByAdmin(Long adminId);

    Long findAdminIdByUserId(Long userId);
}
