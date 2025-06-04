package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.UserTraceReqInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTraceReqInfoMapper {
    void insertUserTraceReqInfo(UserTraceReqInfo reqInfo);
    void insertOrUpdateUserTraceReqInfo(UserTraceReqInfo reqInfo);
    UserTraceReqInfo selectByRequestSessionID(String requestSessionID);
}
