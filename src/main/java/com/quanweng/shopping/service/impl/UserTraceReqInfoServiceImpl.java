package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.mapper.UserTraceReqInfoMapper;
import com.quanweng.shopping.pojo.UserTraceReqInfo;
import com.quanweng.shopping.service.UserTraceReqInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTraceReqInfoServiceImpl implements UserTraceReqInfoService {
    @Autowired
    private UserTraceReqInfoMapper userTraceReqInfoMapper;

    @Override
    public void recordReqInfo(UserTraceReqInfo reqInfo) {
        userTraceReqInfoMapper.insertOrUpdateUserTraceReqInfo(reqInfo);
    }

    @Override
    public UserTraceReqInfo getByRequestSessionID(String requestSessionID) {
        return userTraceReqInfoMapper.selectByRequestSessionID(requestSessionID);
    }
}
