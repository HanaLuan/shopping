package com.quanweng.shopping.utils

import com.quanweng.shopping.pojo.UserTrace
import com.quanweng.shopping.pojo.UserTraceReqInfo
import com.quanweng.shopping.service.UserTraceReqInfoService
import io.jsonwebtoken.Claims
import jakarta.servlet.http.HttpServletRequest
import java.time.LocalDateTime

object UserTraceUtil {

    /**
     * 建立 UserTrace 並紀錄 header 資訊到資料庫
     */
    @JvmStatic
    fun buildAndRecordUserTrace(
        request: HttpServletRequest,
        userIdInput: String?,
        action: String,
        actionData: String,
        userTraceReqInfoService: UserTraceReqInfoService
    ): UserTrace {
        val userId = if (userIdInput.isNullOrEmpty()) "NO_LOGIN" else userIdInput

        val trace = UserTrace().apply {
            this.userId = userId
            this.ip = request.remoteAddr
            this.region = "" // 之後可擴充
            this.action = action
            this.actionData = actionData
            this.createTime = LocalDateTime.now()
        }

        val clientTracer = request.getParameter("clientTracer")
        if (!clientTracer.isNullOrEmpty()) {
            trace.requestSessionID = clientTracer

            // 拼接 headers
            val headersText = buildString {
                val headerNames = request.headerNames
                while (headerNames.hasMoreElements()) {
                    val name = headerNames.nextElement()
                    val value = request.getHeader(name)
                    append("$name: $value\n")
                }
            }.trim()

            val reqInfo = UserTraceReqInfo().apply {
                this.requestSessionID = clientTracer
                this.userId = userId
                this.reqHeader = headersText
            }

            userTraceReqInfoService.recordReqInfo(reqInfo)
        }

        return trace
    }

    /**
     * 從 JWT 或 header 中取得 userId（優先順序：userId → phone → adminName → header）
     */
    @JvmStatic
    fun getUserIdFromHeader(request: HttpServletRequest): String {
        var token: String? = request.getHeader("token")

        if (token.isNullOrEmpty()) {
            val authHeader = request.getHeader("Authorization")
            if (!authHeader.isNullOrEmpty() && authHeader.startsWith("Bearer ")) {
                token = authHeader.removePrefix("Bearer ").trim()
            }
        }

        if (!token.isNullOrEmpty()) {
            try {
                val claims: Claims = JWTUtils.parseToken(token)
                val userId = claims["userId"]?.toString()
                if (!userId.isNullOrEmpty() && userId != "NO_LOGIN") return userId

                val phone = claims["phone"]?.toString()
                if (!phone.isNullOrEmpty() && phone != "NO_LOGIN") return phone

                val adminName = claims["adminName"]?.toString()
                if (!adminName.isNullOrEmpty() && adminName != "NO_LOGIN") return adminName

            } catch (e: Exception) {
                // ignored
            }
        }

        val fallbackUserId = request.getHeader("userId")
        return if (fallbackUserId.isNullOrEmpty()) "NO_LOGIN" else fallbackUserId
    }

}
