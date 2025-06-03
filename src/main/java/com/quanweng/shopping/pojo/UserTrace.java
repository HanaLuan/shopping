package com.quanweng.shopping.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserTrace {
    private Long id;
    private String userId;      // 用户ID或NO_LOGIN
    private String ip;          // 访问IP
    private String region;      // 地区（预留）
    private String action;      // login/register/navigation
    private String actionData;  // 相关数据
    private String requestSessionID; // 请求唯一标识
    private LocalDateTime createTime;
}
