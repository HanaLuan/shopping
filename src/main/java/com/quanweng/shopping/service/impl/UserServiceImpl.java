package com.quanweng.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.quanweng.shopping.mapper.AdminMapper;
import com.quanweng.shopping.mapper.LoginMapper;
import com.quanweng.shopping.mapper.UserMapper;
import com.quanweng.shopping.pojo.Admin;
import com.quanweng.shopping.pojo.Login;
import com.quanweng.shopping.pojo.User;
import com.quanweng.shopping.pojo.common.WebProperties;
import com.quanweng.shopping.service.UserService;
import com.quanweng.shopping.utils.QRCodeUtils;
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
    @Autowired
    private QRCodeUtils qrCodeUtils;
    @Autowired
    private WebProperties webProperties;
    @Autowired
    private AdminMapper adminMapper;

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
        if (user.getUserPhone() != null && user.getUserPhone().contains(" ")){
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
        if (user.getUserPhone() != null && user.getUserPhone().contains(" ")){
            user.setUserPhone(user.getUserPhone().replaceAll(" ",""));
        }
        User oldUser = userMapper.getUserById(user.getId());
//        if(oldUser != null){
//            if(oldUser )
//        }
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
        User user;
        if(phone.contains("@")){
            user = userMapper.getUserByEmail(phone);
        }else {
            user = userMapper.getUserByPhone(phone);
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

    @Override
    public void clearCode() throws Exception {
        List<User> userList = userMapper.getAllUser();
        for (User user : userList) {
            String url = qrCodeUtils.generateQRCode(webProperties.getWebAddress() + "?adminId="+user.getUserFrom());
            user.setUserUrl(url);
            userMapper.updateUser(user);
        }
    }

    @Override
    public Admin theUserIsAdmin(Long userId) {
        User user = userMapper.getUserById(userId);
        String name = null;
        if(user.getUserPhone() != null && !user.getUserPhone().contains("@")){
            name = user.getUserPhone();
        }else if(user.getUserEmail() != null){
            name = user.getUserEmail();
        }
        Admin admin = adminMapper.getAdminByName(name);
        return admin;
    }
}
