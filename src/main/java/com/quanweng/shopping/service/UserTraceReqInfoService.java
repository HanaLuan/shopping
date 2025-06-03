package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.UserTraceReqInfo;

public interface UserTraceReqInfoService {
    void recordReqInfo(UserTraceReqInfo reqInfo);
    UserTraceReqInfo getByRequestSessionID(String requestSessionID);
}
