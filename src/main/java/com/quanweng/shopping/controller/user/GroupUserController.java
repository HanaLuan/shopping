package com.quanweng.shopping.controller.user;

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
public class GroupUserController {
    @Autowired
    private GroupService groupService;

    @GetMapping("/group")
    private Result getAllGroup(){
        log.info("获取所有分组");
        List<Group> groupList = groupService.getAllGroup();
        return Result.success(groupList);
    }


}
