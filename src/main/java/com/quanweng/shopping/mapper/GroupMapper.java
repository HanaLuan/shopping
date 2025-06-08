package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupMapper {
    @Select("select * from group_table")
    List<Group> getAllGroup();


    void addGroup(Group group);

    void updateGroup(Group group);

    void deleteGroup(Long id);

    @Select("select * from group_table where id = #{groupId}")
    Group getGroupByGroupId(Long groupId);
}
