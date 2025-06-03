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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private WebProperties webProperties;


    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public List<Admin> getAllAdmin(Integer pages,Integer size) {
        PageHelper.startPage(pages,size);
        List<Admin> adminList = adminMapper.getAllAdmin();
        return adminList;
    }

    @Override
    public void createAdmin(Admin admin) throws IOException, WriterException {
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
            user.setUserPhone(admin.getAdminName());
            user.setImg("");
            user.setUserFirstName("默认");
            user.setUserLastName("用户名");
            user.setUserJob("未填");
            user.setUserCom("未填");
            user.setUserEmail("未填");
            user.setUserAdd("未填");
            String url = QRCodeUtils.generateQRCode(webProperties.getWebAddress()+"?adminId="+admin.getId());

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
        login.setUpdateTime(LocalDateTime.now());
        loginMapper.updateLogin(login);

        User user = new User();
        user.setUserPhone(admin.getAdminName());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateUser(user);
    }

    @Override
    public void deleteAdmin(Long id) {
        adminMapper.deleteAdmin(id);
    }

    @Override
    public List<Admin> getAdminByAdminFrom(Long adminFrom,Integer pages,Integer size) {
        PageHelper.startPage(pages,size);
        List<Admin> adminList = adminMapper.getAdminByAdminFrom(adminFrom);
        return adminList;
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminMapper.getAdminById(id);
    }
}
