package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.AdminHaveUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminHaveUserMapper {
    @Select("select * from admin_have_user_table where admin_id = #{adminId}")
    List<AdminHaveUser> findUserByAdmin(Long adminId);

    @Select("select * from admin_have_user_table where user_id = #{userId}")
    AdminHaveUser findAdminIdByUserId(Long userId);
}
