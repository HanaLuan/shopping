package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.GroupAndAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupAndAdminMapper {
    void addGroupAndAdmin(GroupAndAdmin groupAndAdmin);

    @Select("select admin_id from group_and_admin_table where group_id = #{groupId}")
    List<Long> getAdminIdByGroupId(Long groupId);

    @Select("select group_id from group_and_admin_table where admin_id = #{adminId}")
    List<Long> getGroupIdByAdminId(Long adminId);

    void deleteGroupAndAdmin(Long groupId, Long adminId);
}
