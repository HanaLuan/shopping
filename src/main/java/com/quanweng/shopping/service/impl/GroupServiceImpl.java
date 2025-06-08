package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.mapper.AdminMapper;
import com.quanweng.shopping.mapper.GroupAndAdminMapper;
import com.quanweng.shopping.mapper.GroupMapper;
import com.quanweng.shopping.pojo.Admin;
import com.quanweng.shopping.pojo.Group;
import com.quanweng.shopping.pojo.GroupAndAdmin;
import com.quanweng.shopping.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private GroupAndAdminMapper groupAndAdminMapper;
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Group> getAllGroup() {
        return groupMapper.getAllGroup();
    }

    @Override
    public void addGroup(Group group) {
        group.setCreateTime(LocalDateTime.now());
        group.setUpdateTime(LocalDateTime.now());
        groupMapper.addGroup(group);
    }

    @Override
    public void updateGroup(Group group) {
        group.setUpdateTime(LocalDateTime.now());
        groupMapper.updateGroup(group);
    }

    @Override
    public void deleteGroup(Long id) {
        groupMapper.deleteGroup(id);
    }

    @Override
    public void addGroupMember(Long groupId, Long adminId) {
        GroupAndAdmin groupAndAdmin = new GroupAndAdmin();
        groupAndAdmin.setGroupId(groupId);
        groupAndAdmin.setAdminId(adminId);
        groupAndAdmin.setCreateTime(LocalDateTime.now());
        groupAndAdmin.setUpdateTime(LocalDateTime.now());
        groupAndAdminMapper.addGroupAndAdmin(groupAndAdmin);
    }

    @Override
    public List<Admin> getAdminByGroupId(Long groupId) {
        List<Long> adminIdList = groupAndAdminMapper.getAdminIdByGroupId(groupId);
        List<Admin> adminList = new ArrayList<>();
        for(Long adminId : adminIdList){
            adminList.add(adminMapper.getAdminById(adminId));
        }
        return adminList;
    }

    @Override
    public List<Group> getAdminGroupByAdminId(Long adminId) {
        List<Long> groupIdList = groupAndAdminMapper.getGroupIdByAdminId(adminId);
        List<Group> groupList = new ArrayList<>();
        for(Long groupId : groupIdList){
            groupList.add(groupMapper.getGroupByGroupId(groupId));
        }
        return groupList;
    }

    @Override
    public void deleteGroupMember(Long groupId, Long adminId) {
        groupAndAdminMapper.deleteGroupAndAdmin(groupId,adminId);
    }
}
