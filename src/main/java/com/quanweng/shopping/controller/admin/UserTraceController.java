package com.quanweng.shopping.controller.admin;

import com.quanweng.shopping.pojo.UserTrace;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.UserTraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class UserTraceController {
    @Autowired
    private UserTraceService userTraceService;

    @GetMapping("/userTrace/{userId}")
    public Result getTraceByUserId(@PathVariable String userId) {
        List<UserTrace> traces = userTraceService.getTraceByUserId(userId);
        return Result.success(traces);
    }

    // 分页+条件查询
    @GetMapping("/userTrace/query")
    public Result queryUserTrace(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String action,
            @RequestParam(required = false) String ip,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        List<UserTrace> traces = userTraceService.queryUserTrace(userId, action, ip, region, startTime, endTime, page, size);
        int total = userTraceService.countUserTrace(userId, action, ip, region, startTime, endTime);
        return Result.success(Map.of("total", total, "list", traces));
    }

    // 行为统计
    @GetMapping("/userTrace/statistics")
    public Result traceStatistics(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime
    ) {
        Map<String, Integer> stat = userTraceService.statisticsByAction(userId, startTime, endTime);
        return Result.success(stat);
    }
}
