package com.quanweng.shopping.controller.admin;

import com.quanweng.shopping.pojo.Admin;
import com.quanweng.shopping.pojo.Group;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @GetMapping("/group")
    private Result getAllGroup(){
        log.info("获取所有分组");
        List<Group> groupList = groupService.getAllGroup();
        return Result.success(groupList);
    }

    @PostMapping("/group")
    private Result addGroup(@RequestBody Group group){
        log.info("{}",group);
        groupService.addGroup(group);
        return Result.success(group);
    }

    @PutMapping("/group")
    private Result updateGroup(@RequestBody Group group){
        groupService.updateGroup(group);
        return Result.success();
    }

    @DeleteMapping("/group/{id}")
    private Result deleteGroup(@PathVariable Long id){
        groupService.deleteGroup(id);
        return Result.success();
    }

    @GetMapping("/groupMember/{groupId}")
    private Result getGroupById(@PathVariable Long groupId){
        log.info("获取分组成员");
        List<Admin> adminList = groupService.getAdminByGroupId(groupId);
        return Result.success(adminList);
    }

    @GetMapping("/adminGroup/{adminId}")
    private Result getAdminGroupByAdminId(@PathVariable Long adminId){
        log.info("获取管理的分组");
        List<Group> groupList = groupService.getAdminGroupByAdminId(adminId);
        return Result.success(groupList);
    }

    @PostMapping("/groupAddMember")
    private Result addGroupMember(@RequestParam Long groupId,@RequestParam Long adminId){
        groupService.addGroupMember(groupId,adminId);
        return Result.success();
    }

    @DeleteMapping("/groupMember")
    private Result deleteGroupMember(@RequestParam Long groupId,@RequestParam Long adminId){
        groupService.deleteGroupMember(groupId,adminId);
        return Result.success();
    }


}
