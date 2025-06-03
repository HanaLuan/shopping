package com.quanweng.shopping.interceptor;

import com.alibaba.fastjson2.JSONObject;
import com.quanweng.shopping.utils.JWTUtils;
import com.quanweng.shopping.pojo.UserTraceReqInfo;
import com.quanweng.shopping.service.UserTraceReqInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private UserTraceReqInfoService userTraceReqInfoService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取 clientTracer Cookie
        String clientTracer = null;
        if (request.getCookies() != null) {
            for (jakarta.servlet.http.Cookie cookie : request.getCookies()) {
                if ("clientTracer".equals(cookie.getName())) {
                    clientTracer = cookie.getValue();
                    break;
                }
            }
        }
        String requestSessionID = null;
        if (clientTracer != null && !clientTracer.isEmpty()) {
            // 计算 SHA3-512
            try {
                java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA3-512");
                byte[] hash = digest.digest(clientTracer.getBytes(java.nio.charset.StandardCharsets.UTF_8));
                StringBuilder hexString = new StringBuilder();
                for (byte b : hash) {
                    String hex = Integer.toHexString(0xff & b);
                    if(hex.length() == 1) hexString.append('0');
                    hexString.append(hex);
                }
                requestSessionID = hexString.toString();
            } catch (Exception e) {
                // ignore
            }
        }
        // 记录请求头
        if (requestSessionID != null) {
            String userId = "NO_LOGIN";
            String token = request.getHeader("token");
            if(token == null) {
                String authHeader = request.getHeader("Authorization");
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    token = authHeader.substring(7).trim();
                }
            }
            if(token != null && !token.isEmpty()){
                try{
                    Object uid = JWTUtils.parseToken(token).get("adminName");
                    if(uid != null) userId = uid.toString();
                }catch (Exception ignore){}
            }
            // 获取所有请求头
            java.util.Enumeration<String> headerNames = request.getHeaderNames();
            JSONObject headerJson = new JSONObject();
            while(headerNames.hasMoreElements()){
                String name = headerNames.nextElement();
                headerJson.put(name, request.getHeader(name));
            }
            UserTraceReqInfo reqInfo = new UserTraceReqInfo();
            reqInfo.setRequestSessionID(requestSessionID);
            reqInfo.setUserId(userId);
            reqInfo.setReqHeader(headerJson.toString());
            try {
                userTraceReqInfoService.recordReqInfo(reqInfo);
            } catch (Exception ignore) {}
        }
        String requestURL = request.getRequestURI();

//        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//            response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
//            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//            response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
//            return true;
//        }

        if(requestURL.contains("/login") || !requestURL.contains("/admin")){
            log.info("登录放行");
            return true;
        }
        String token = request.getHeader("token");
        if(token == null) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7).trim();
            }
        }

        if(token == null || token.isEmpty()){
            log.info("令牌为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        try{
            JWTUtils.parseToken(token).get("adminName").toString();
        }catch (Exception e){
            log.info("令牌非法");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        log.info("令牌合法");
        return true;
    }
}
