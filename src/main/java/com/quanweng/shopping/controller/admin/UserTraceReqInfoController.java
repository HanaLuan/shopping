package com.quanweng.shopping.controller.admin;

import com.quanweng.shopping.pojo.UserTraceReqInfo;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.UserTraceReqInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin/userTraceReqInfo")
public class UserTraceReqInfoController {
    @Autowired
    private UserTraceReqInfoService userTraceReqInfoService;

    @GetMapping("/{requestSessionID}")
    public Result getByRequestSessionID(@PathVariable String requestSessionID) {
        log.info("查询请求 {} 的附属信息", requestSessionID);
        UserTraceReqInfo info = userTraceReqInfoService.getByRequestSessionID(requestSessionID);
        return Result.success(info);
    }
}
