package com.quanweng.shopping.controller.admin;

import com.quanweng.shopping.pojo.UserTraceReqInfo;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.UserTraceReqInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/userTraceReqInfo")
public class UserTraceReqInfoController {
    @Autowired
    private UserTraceReqInfoService userTraceReqInfoService;

    @GetMapping("/{requestSessionID}")
    public Result getByRequestSessionID(@PathVariable String requestSessionID) {
        UserTraceReqInfo info = userTraceReqInfoService.getByRequestSessionID(requestSessionID);
        return Result.success(info);
    }
}
