package com.quanweng.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.zxing.WriterException;
import com.quanweng.shopping.mapper.AdminMapper;
import com.quanweng.shopping.mapper.LoginMapper;
import com.quanweng.shopping.mapper.UserMapper;
import com.quanweng.shopping.pojo.Admin;
import com.quanweng.shopping.pojo.Login;
import com.quanweng.shopping.pojo.User;
import com.quanweng.shopping.pojo.common.WebProperties;
import com.quanweng.shopping.service.AdminService;
import com.quanweng.shopping.utils.QRCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private WebProperties webProperties;
    @Autowired
    private QRCodeUtils qrCodeUtils;


    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public List<Admin> getAllAdmin(Integer pages,Integer size) {
        if (pages != null && size != null) {
            PageHelper.startPage(pages, size);
        }
        List<Admin> adminList = adminMapper.getAllAdmin();
        return adminList;
    }

    @Override
    public void createAdmin(Admin admin) throws Exception {
        if (admin.getAdminName().contains(" ")){
            admin.setAdminName(admin.getAdminName().replaceAll(" ",""));
        }
        if(adminMapper.getAdminByName(admin.getAdminName()) == null) {
            admin.setAdminPassword(DigestUtils.md5DigestAsHex(admin.getAdminPassword().getBytes()));
            admin.setCreateTime(LocalDateTime.now());
            admin.setUpdateTime(LocalDateTime.now());
            adminMapper.createAdmin(admin);

            //同时新建用户
            Login login = new Login();
            login.setPhone(admin.getAdminName());
            login.setPassword(admin.getAdminPassword());
            login.setAdminId(admin.getId());
            login.setUpdateTime(LocalDateTime.now());
            login.setCreateTime(LocalDateTime.now());
            loginMapper.register(login);

            User user = new User();
            if(!admin.getAdminName().contains("@")) {
                user.setUserPhone(admin.getAdminName());
            }else {
//                user.setUserPhone("未填");
                user.setUserEmail(admin.getAdminName());
            }
            user.setImg("");
            user.setUserFirstName("");
            user.setUserLastName("");
            user.setUserJob("");
            user.setUserCom("");
//            if(admin.getAdminName().contains("@")) {
//
//            }else {
////                user.setUserEmail("未填");
//            }
            user.setUserAdd("");
            String url = qrCodeUtils.generateQRCode(webProperties.getWebAddress()+"?adminId="+admin.getId());

            user.setUserUrl(url);
            user.setUserFrom(admin.getId());
            user.setUpdateTime(LocalDateTime.now());
            user.setCreateTime(LocalDateTime.now());
            userMapper.createUser(user);
        }
    }

    @Override
    public void updateAdmin(Admin admin) {
        if(admin.getAdminPassword() != null) {
            admin.setAdminPassword(DigestUtils.md5DigestAsHex(admin.getAdminPassword().getBytes()));
        }
        admin.setUpdateTime(LocalDateTime.now());
        adminMapper.updateAdmin(admin);

        Login login = new Login();
        login.setPhone(admin.getAdminName());
        login.setPassword(admin.getAdminPassword());
        login.setAdminId(admin.getId());
        login.setUpdateTime(LocalDateTime.now());
        loginMapper.updateLogin(login);

        User user = new User();
        user.setUserPhone(admin.getAdminName());
        user.setUserFrom(admin.getId());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateUserByAdminId(user);
    }

    @Override
    public void deleteAdmin(Long id) {
        Admin admin = adminMapper.getAdminById(id);
        User user = new User();
        if(admin.getAdminName().contains("@")) {
            user.setUserEmail(admin.getAdminName());
        }else {
            user.setUserPhone(admin.getAdminName());
        }
        user.setUserFrom(admin.getId());
        userMapper.deleteByNameAndUserFrom(user);

        Login login = new Login();
        login.setAdminId(admin.getId());
        login.setPhone(admin.getAdminName());
        loginMapper.deleteByAdminId(login);



        adminMapper.deleteAdmin(id);
    }

    @Override
    public List<Admin> getAdminByAdminFrom(Long adminFrom,Integer pages,Integer size) {
        if (pages != null && size != null) {
            PageHelper.startPage(pages, size);
        }
        List<Admin> adminList = adminMapper.getAdminByAdminFrom(adminFrom);
        return adminList;
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminMapper.getAdminById(id);
    }

    @Override
    public Integer getAllAdminCount() {
        return adminMapper.getAllAdminCount();
    }

    @Override
    public Integer getAdminByAdminFromCount(Long adminFrom) {
        return adminMapper.getAdminByAdminFromCount(adminFrom);
    }

    @Override
    public void banTheAdmin(Long id) {
        Admin admin = adminMapper.getAdminById(id);
        if(admin != null) {
            log.info("{}",admin);
            User user = new User();
            if(admin.getAdminName().contains("@")) {
                user = userMapper.getUserByEmail(admin.getAdminName());
            }else {
                user = userMapper.getUserByPhone(admin.getAdminName());
            }
            log.info("{}",user);
            if(admin.getAdminStatus() == 0){
                admin.setAdminStatus(1);
                user.setUserStatus(1);
            }else {
                admin.setAdminStatus(0);
                user.setUserStatus(0);
            }
            user.setUpdateTime(LocalDateTime.now());
            userMapper.updateUser(user);
            admin.setUpdateTime(LocalDateTime.now());
            adminMapper.updateAdmin(admin);
        }
    }
}
