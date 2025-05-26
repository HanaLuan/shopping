package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.UserTrace;

import java.util.List;
import java.util.Map;

public interface UserTraceService {
    void recordTrace(UserTrace userTrace);

    List<UserTrace> getTraceByUserId(String userId);

    List<UserTrace> queryUserTrace(String userId, String action, String ip, String region, String startTime, String endTime, int page, int size);

    int countUserTrace(String userId, String action, String ip, String region, String startTime, String endTime);

    Map<String, Integer> statisticsByAction(String userId, String startTime, String endTime);
}
