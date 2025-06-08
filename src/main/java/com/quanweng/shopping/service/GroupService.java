package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.Admin;
import com.quanweng.shopping.pojo.Group;

import java.util.List;

public interface GroupService {
    List<Group> getAllGroup();

    void addGroup(Group group);

    void updateGroup(Group group);

    void deleteGroup(Long id);

    void addGroupMember(Long groupId, Long adminId);

    List<Admin> getAdminByGroupId(Long groupId);

    List<Group> getAdminGroupByAdminId(Long adminId);

    void deleteGroupMember(Long groupId, Long adminId);
}
