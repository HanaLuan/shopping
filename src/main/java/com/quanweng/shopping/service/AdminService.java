package com.quanweng.shopping.service;

import com.google.zxing.WriterException;
import com.quanweng.shopping.pojo.Admin;

import java.io.IOException;
import java.util.List;

public interface AdminService {
    List<Admin> getAllAdmin(Integer pages,Integer size);

    void createAdmin(Admin admin) throws IOException, WriterException;

    void updateAdmin(Admin admin);

    void deleteAdmin(Long id);

    List<Admin> getAdminByAdminFrom(Long adminFrom,Integer pages,Integer size);

    Admin getAdminById(Long id);
}
