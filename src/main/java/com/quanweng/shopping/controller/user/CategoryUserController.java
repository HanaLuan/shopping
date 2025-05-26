package com.quanweng.shopping.controller.user;

import com.quanweng.shopping.pojo.Category;
import com.quanweng.shopping.pojo.UserTrace;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.CategoryService;
import com.quanweng.shopping.service.UserTraceService;
import com.quanweng.shopping.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CategoryUserController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserTraceService userTraceService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/category")
    private Result getAllCategory(){
        List<Category> categoryList = categoryService.getAllCategory();
        log.info("查询全部分类:{}",categoryList);
        // 跟踪
        UserTrace trace = new UserTrace();
        trace.setUserId(getUserIdFromHeader());
        trace.setIp(request.getRemoteAddr());
        trace.setRegion("");
        trace.setAction("category_all");
        trace.setActionData("");
        trace.setCreateTime(java.time.LocalDateTime.now());
        userTraceService.recordTrace(trace);
        return Result.success(categoryList);
    }

    @GetMapping("/categoryDetail/{id}")
    private Result getCategoryById(@PathVariable Long id){
        Category category = categoryService.getCategoryById(id);
        log.info("根据id查询单个分类:{}",category);
        // 跟踪
        UserTrace trace = new UserTrace();
        trace.setUserId(getUserIdFromHeader());
        trace.setIp(request.getRemoteAddr());
        trace.setRegion("");
        trace.setAction("category_detail");
        trace.setActionData("categoryId:" + id);
        trace.setCreateTime(java.time.LocalDateTime.now());
        userTraceService.recordTrace(trace);
        return Result.success(category);
    }

    @GetMapping("/categoryChild/{categoryFatherId}")
    private Result getCategoryByFatherId(@PathVariable Long categoryFatherId){
        List<Category> categoryList = categoryService.getCategoryByFatherId(categoryFatherId);
        log.info("根据父id{}查询子分类:{}",categoryFatherId,categoryList);
        // 跟踪
        UserTrace trace = new UserTrace();
        trace.setUserId(getUserIdFromHeader());
        trace.setIp(request.getRemoteAddr());
        trace.setRegion("");
        trace.setAction("category_child");
        trace.setActionData("fatherId:" + categoryFatherId);
        trace.setCreateTime(java.time.LocalDateTime.now());
        userTraceService.recordTrace(trace);
        return Result.success(categoryList);
    }

    @GetMapping("/categoryByLevel/{categoryLevel}")
    private Result getCategoryByLevel(@PathVariable Long categoryLevel){
        List<Category> categoryList = categoryService.getCategoryByLevel(categoryLevel);
        log.info("根据等级{}查询分类:{}",categoryLevel,categoryList);
        // 跟踪
        UserTrace trace = new UserTrace();
        trace.setUserId(getUserIdFromHeader());
        trace.setIp(request.getRemoteAddr());
        trace.setRegion("");
        trace.setAction("category_level");
        trace.setActionData("level:" + categoryLevel);
        trace.setCreateTime(java.time.LocalDateTime.now());
        userTraceService.recordTrace(trace);
        return Result.success(categoryList);
    }

    // 工具方法：优先从token(JWT)获取userId，没有则header，没有则NO_LOGIN
    private String getUserIdFromHeader() {
        String token = request.getHeader("token");
        if (token == null) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7).trim();
            }
        }
        if (token != null && !token.isEmpty()) {
            try {
                Claims claims = JWTUtils.parseToken(token);
                // 优先取userId
                Object userIdObj = claims.get("userId");
                if (userIdObj != null && !"NO_LOGIN".equals(userIdObj.toString())) {
                    return userIdObj.toString();
                }
                // 兼容部分token只存phone
                Object phoneObj = claims.get("phone");
                if (phoneObj != null && !"NO_LOGIN".equals(phoneObj.toString())) {
                    // 这里可以根据业务需要返回phone或NO_LOGIN
                    return phoneObj.toString();
                }
                // 兼容部分token只存adminName
                Object adminNameObj = claims.get("adminName");
                if (adminNameObj != null && !"NO_LOGIN".equals(adminNameObj.toString())) {
                    return adminNameObj.toString();
                }
            } catch (Exception ignored) {}
        }
        String userId = request.getHeader("userId");
        return (userId == null || userId.isEmpty()) ? "NO_LOGIN" : userId;
    }
}
