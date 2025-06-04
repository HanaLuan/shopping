package com.quanweng.shopping.pojo

import java.time.LocalDateTime

data class UserTrace(
    var id: Long? = null,
    var userId: String? = null,          // 用户ID或NO_LOGIN
    var ip: String? = null,              // 访问IP
    var region: String? = null,          // 地区（预留）
    var action: String? = null,          // login/register/navigation
    var actionData: String? = null,      // 相关数据
    var requestSessionID: String? = null,// 请求唯一标识
    var createTime: LocalDateTime? = null
)
