package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user_table")
    List<User> getAllUser();

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    User getUserByPhone(String phone);

    User getUserById(Long id);

    @Select("select * from user_table where user_from = #{adminId}")
    List<User> getUserByAdmin(Long adminId);
}
