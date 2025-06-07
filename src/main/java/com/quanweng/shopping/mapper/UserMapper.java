package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user_table order by create_time desc ")
    List<User> getAllUser();

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    User getUserByPhone(String phone);

    User getUserById(Long id);

    @Select("select * from user_table where user_from = #{adminId} order by create_time desc")
    List<User> getUserByAdmin(Long adminId);

    @Select("select count(*) from user_table")
    Integer getAllUserCount();

    @Select("select count(*) from user_table where user_from = #{adminId}")
    Integer getUserByAdminIdCount(Long adminId);

    @Select("select * from user_table where user_email = #{userEmail}")
    User getUserByEmail(String userEmail);
}
