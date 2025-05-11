package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("select * from adimn_table")
    List<Admin> getAllAdmin();

    void createAdmin(Admin admin);

    void updateAdmin(Admin admin);

    void deleteAdmin(Long id);
}
