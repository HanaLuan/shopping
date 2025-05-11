package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> getAllAdmin();

    void createAdmin(Admin admin);

    void updateAdmin(Admin admin);

    void deleteAdmin(Long id);
}
