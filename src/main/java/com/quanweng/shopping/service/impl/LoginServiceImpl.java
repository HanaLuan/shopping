package com.quanweng.shopping.service.impl;

import com.google.zxing.WriterException;
import com.quanweng.shopping.mapper.AdminMapper;
import com.quanweng.shopping.mapper.LoginMapper;
import com.quanweng.shopping.mapper.UserMapper;
import com.quanweng.shopping.pojo.Admin;
import com.quanweng.shopping.pojo.DTO.LoginInfo;
import com.quanweng.shopping.pojo.Login;
import com.quanweng.shopping.pojo.User;
import com.quanweng.shopping.pojo.VO.LoginAdminVo;
import com.quanweng.shopping.pojo.VO.LoginVo;
import com.quanweng.shopping.pojo.common.WebProperties;
import com.quanweng.shopping.service.LoginService;
import com.quanweng.shopping.utils.JWTUtils;
import com.quanweng.shopping.utils.QRCodeUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private WebProperties webProperties;
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdminMapper adminMapper;


    @Override
    public void register(LoginInfo loginInfo) throws IOException, WriterException {
        Login login = new Login();
        if(userMapper.getUserByPhone(loginInfo.getPhone()) == null && userMapper.getUserByEmail(loginInfo.getUserEmail()) == null){
            login.setPassword(DigestUtils.md5DigestAsHex(loginInfo.getPassword().getBytes()));
            if (loginInfo.getPhone() != null) {
                login.setPhone(loginInfo.getPhone());
            }else {
                login.setPhone(loginInfo.getUserEmail());
            }
            if(loginInfo.getAdminId() != null){
                login.setAdminId(loginInfo.getAdminId());
            }
            login.setCreateTime(LocalDateTime.now());
            login.setUpdateTime(LocalDateTime.now());
            if(loginMapper.getLoginByPhone(loginInfo.getPhone()) == null) {
                loginMapper.register(login);
            }

            User user = new User();
            if (loginInfo.getPhone() != null) {
                user.setUserPhone(loginInfo.getPhone());
            }else {
                user.setUserPhone("未填");
            }
            user.setImg("");
            user.setUserFirstName(loginInfo.getUserFirstName());
            user.setUserLastName(loginInfo.getUserLastName());
            user.setUserJob("未填");
            user.setUserCom("未填");
            user.setUserEmail(loginInfo.getUserEmail());
            user.setUserAdd(loginInfo.getUserAdd());
            if (loginInfo.getAdminId() == null) {
                String url = QRCodeUtils.generateQRCode(webProperties.getWebAddress() + "?adminId=");
                user.setUserUrl(url);
                user.setUserFrom(0L);
            }else {
                String url = QRCodeUtils.generateQRCode(webProperties.getWebAddress() + "?adminId="+loginInfo.getAdminId());
                user.setUserUrl(url);
                user.setUserFrom(loginInfo.getAdminId());
            }
            user.setUpdateTime(LocalDateTime.now());
            user.setCreateTime(LocalDateTime.now());
            userMapper.createUser(user);
        }

    }

    @Override
    public LoginVo login(Login login) {
        login.setPassword(DigestUtils.md5DigestAsHex(login.getPassword().getBytes()));
        LoginVo loginVo = new LoginVo();
        Login logi = loginMapper.findTheLogin(login);
        if (logi != null){
            log.info("登录成功{}",logi);
            Map<String,Object> claims = new HashMap<>();
            claims.put("phone",logi.getPhone());
            String jwt = JWTUtils.generateToken(claims);
            loginVo.setToken(jwt);
            User user = userMapper.getUserByPhone(logi.getPhone());
            if(login.getPhone().contains("@")) {
                user = userMapper.getUserByEmail(logi.getPhone());
            }
            log.info("{}",logi);
            log.info("{}",user);
            loginVo.setUserId(user.getId());
            loginVo.setPhone(logi.getPhone());

            if (login.getAdminId() != null){
                if(user.getUserFrom() == null){
                    user.setUserFrom(login.getAdminId());
                    user.setUpdateTime(LocalDateTime.now());
                    userMapper.updateUser(user);
                }
            }

            return loginVo;
        }
        loginVo.setToken("登录失败");
        return loginVo;
    }

    @Override
    public LoginAdminVo loginAdmin(String adminName, String adminPassword) {
        adminPassword = DigestUtils.md5DigestAsHex(adminPassword.getBytes());
        LoginAdminVo loginAdminVo = new LoginAdminVo();
        Admin admin = adminMapper.findTheLogin(adminName,adminPassword);
        if(admin != null){
            log.info("管理登录成功{}",admin);
            Map<String,Object> claims = new HashMap<>();
            claims.put("adminName",admin.getAdminName());
            claims.put("adminLevel",admin.getAdminLevel());
            String jwt = JWTUtils.generateToken(claims);
            loginAdminVo.setToken(jwt);

            loginAdminVo.setId(admin.getId());
            loginAdminVo.setAdminName(admin.getAdminName());
            loginAdminVo.setAdminLevel(admin.getAdminLevel());

        }
        return loginAdminVo;
    }
}
