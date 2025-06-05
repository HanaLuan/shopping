package com.quanweng.shopping.pojo

data class UserTraceReqInfo(
    var requestSessionID: String = "", // 請求唯一標識
    var userId: String = "NO_LOGIN",   // 使用者ID或NO_LOGIN
    var reqHeader: String = ""         // 完整請求頭(JSON or 純文字)
)
