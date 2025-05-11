package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.mapper.AdminMapper;
import com.quanweng.shopping.pojo.Admin;
import com.quanweng.shopping.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> getAllAdmin() {
        return adminMapper.getAllAdmin();
    }

    @Override
    public void createAdmin(Admin admin) {
        admin.setCreateTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());
        adminMapper.createAdmin(admin);
    }

    @Override
    public void updateAdmin(Admin admin) {
        admin.setUpdateTime(LocalDateTime.now());
        adminMapper.updateAdmin(admin);
    }

    @Override
    public void deleteAdmin(Long id) {
        adminMapper.deleteAdmin(id);
    }
}
