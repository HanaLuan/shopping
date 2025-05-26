package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.mapper.UserTraceMapper;
import com.quanweng.shopping.pojo.UserTrace;
import com.quanweng.shopping.service.UserTraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserTraceServiceImpl implements UserTraceService {
    @Autowired
    private UserTraceMapper userTraceMapper;

    @Override
    public void recordTrace(UserTrace userTrace) {
        userTraceMapper.insertUserTrace(userTrace);
    }

    @Override
    public List<UserTrace> getTraceByUserId(String userId) {
        return userTraceMapper.queryUserTraceByUserId(userId);
    }

    @Override
    public List<UserTrace> queryUserTrace(String userId, String action, String ip, String region, String startTime, String endTime, int page, int size) {
        int offset = (page - 1) * size;
        return userTraceMapper.queryUserTrace(userId, action, ip, region, startTime, endTime, offset, size);
    }

    @Override
    public int countUserTrace(String userId, String action, String ip, String region, String startTime, String endTime) {
        return userTraceMapper.countUserTrace(userId, action, ip, region, startTime, endTime);
    }

    @Override
    public Map<String, Integer> statisticsByAction(String userId, String startTime, String endTime) {
        return userTraceMapper.statisticsByAction(userId, startTime, endTime);
    }
}
