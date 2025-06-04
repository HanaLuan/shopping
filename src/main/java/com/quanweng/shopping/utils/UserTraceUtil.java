package com.quanweng.shopping.utils;

import com.quanweng.shopping.pojo.UserTrace;
import com.quanweng.shopping.pojo.UserTraceReqInfo;
import com.quanweng.shopping.service.UserTraceReqInfoService;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Enumeration;

public class UserTraceUtil {

    /**
     * 創建 UserTrace 並記錄 header 到資料庫（透過 service）
     */
    public static UserTrace buildAndRecordUserTrace(HttpServletRequest request,
                                                    String userId,
                                                    String action,
                                                    String actionData,
                                                    UserTraceReqInfoService userTraceReqInfoService) {

        if (userId == null || userId.isEmpty()) userId = "NO_LOGIN";

        UserTrace trace = new UserTrace();
        trace.setUserId(userId);
        trace.setIp(request.getRemoteAddr());
        trace.setRegion(""); // 可以之後擴展
        trace.setAction(action);
        trace.setActionData(actionData);
        trace.setCreateTime(LocalDateTime.now());

        String clientTracer = request.getParameter("clientTracer");
        if (clientTracer != null && !clientTracer.isEmpty()) {
            trace.setRequestSessionID(clientTracer);

            // 拼接 Header 為純文字
            StringBuilder headersText = new StringBuilder();
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                headersText.append(headerName).append(": ").append(headerValue).append("\n");
            }

            UserTraceReqInfo reqInfo = new UserTraceReqInfo();
            reqInfo.setRequestSessionID(clientTracer);
            reqInfo.setUserId(userId);
            reqInfo.setReqHeader(headersText.toString().trim());

            userTraceReqInfoService.recordReqInfo(reqInfo);
        }

        return trace;
    }
}
