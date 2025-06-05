package com.quanweng.shopping.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.quanweng.shopping.controller.user.GoodsUserController
import com.quanweng.shopping.pojo.UserTrace
import com.quanweng.shopping.pojo.UserTraceReqInfo
import com.quanweng.shopping.service.UserTraceReqInfoService
import io.jsonwebtoken.Claims
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.util.*
import kotlin.text.Charsets.UTF_8


object UserTraceUtil {

    private val objectMapper = ObjectMapper()


    private val log = LoggerFactory.getLogger(GoodsUserController::class.java)

    /**
     * 建立 UserTrace 並紀錄 header 資訊到資料庫（使用 JSON + Base64 編碼）
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
            this.region = "" // 可根據 IP 擴充區域分析
            this.action = action
            this.actionData = actionData
            this.createTime = LocalDateTime.now()
        }

        val clientTracer = request.getParameter("clientTracer")
        if (!clientTracer.isNullOrEmpty()) {
            trace.requestSessionID = clientTracer

            // 將 headers 收集為 JSON 並轉成 Base64（UTF-8）
            val headersMap = mutableMapOf<String, String>()
            val headerNames = request.headerNames
            while (headerNames.hasMoreElements()) {
                val name = headerNames.nextElement()
                val value = request.getHeader(name)
                headersMap[name] = value
            }

            val jsonHeaders = objectMapper.writeValueAsString(headersMap)
            val encodedHeaders = Base64.getEncoder().encodeToString(jsonHeaders.toByteArray(UTF_8))

            val reqInfo = UserTraceReqInfo().apply {
                this.requestSessionID = clientTracer
                this.userId = userId
                this.reqHeader = encodedHeaders
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
        log.info("token $token")
        if (token.isNullOrEmpty()) {
            val authHeader = request.getHeader("Authorization")
            if (!authHeader.isNullOrEmpty() && authHeader.startsWith("Bearer ")) {
                token = authHeader.removePrefix("Bearer ").trim()
            }
        }

        if (!token.isNullOrEmpty()) {
            try {
                log.info("claim")
                val claims: Claims = JWTUtils.parseToken(token)
                log.info("claim $claims")
                val userId = claims["userId"]?.toString()
                if (!userId.isNullOrEmpty() && userId != "NO_LOGIN") return userId

                val phone = claims["phone"]?.toString()
                if (!phone.isNullOrEmpty() && phone != "NO_LOGIN") return phone

                val adminName = claims["adminName"]?.toString()
                if (!adminName.isNullOrEmpty() && adminName != "NO_LOGIN") return adminName

            } catch (e: Exception) {
                log.error("{}",e)
            }
        }

        val fallbackUserId = request.getHeader("userId")
        return if (fallbackUserId.isNullOrEmpty()) "NO_LOGIN" else fallbackUserId
    }

}
