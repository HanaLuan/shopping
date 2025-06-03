package com.quanweng.shopping.pojo;

import lombok.Data;

@Data
public class UserTraceReqInfo {
    private String requestSessionID; // 请求唯一标识
    private String userId;           // 用户ID或NO_LOGIN
    private String reqHeader;        // 完整请求头(JSON字符串)
}
