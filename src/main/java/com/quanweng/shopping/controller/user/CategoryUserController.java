package com.quanweng.shopping.controller.user;

import com.quanweng.shopping.pojo.Category;
import com.quanweng.shopping.pojo.UserTrace;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.CategoryService;
import com.quanweng.shopping.service.UserTraceReqInfoService;
import com.quanweng.shopping.service.UserTraceService;
import com.quanweng.shopping.utils.UserTraceUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.quanweng.shopping.utils.UserTraceUtil.getUserIdFromHeader;

@Slf4j
@RestController
public class CategoryUserController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserTraceService userTraceService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserTraceReqInfoService userTraceReqInfoService;

    @GetMapping("/category")
    private Result getAllCategory(@RequestParam(required = false) Integer pages,
                                  @RequestParam(required = false) Integer size){
        List<Category> categoryList = categoryService.getAllCategory(pages,size);
        log.info("查询全部分类:{}",categoryList);
        // 跟踪
        UserTrace trace = UserTraceUtil.buildAndRecordUserTrace(
                request,
                getUserIdFromHeader(request),
                "query_Category",
                "categoryList:" + categoryList, userTraceReqInfoService);
        userTraceService.recordTrace(trace);
        return Result.success(categoryList);
    }

    @GetMapping("/categoryDetail/{id}")
    private Result getCategoryById(@PathVariable Long id){
        Category category = categoryService.getCategoryById(id);
        log.info("根据id查询单个分类:{}",category);
        // 跟踪
        UserTrace trace = UserTraceUtil.buildAndRecordUserTrace(
                request,
                getUserIdFromHeader(request),
                "query_CategoryDetailById",
                "categoryId:" + id, userTraceReqInfoService);
        userTraceService.recordTrace(trace);
        return Result.success(category);
    }

    @GetMapping("/categoryChild/{categoryFatherId}")
    private Result getCategoryByFatherId(@PathVariable Long categoryFatherId){
        List<Category> categoryList = categoryService.getCategoryByFatherId(categoryFatherId);
        log.info("根据父id{}查询子分类:{}",categoryFatherId,categoryList);
        // 跟踪
        UserTrace trace = UserTraceUtil.buildAndRecordUserTrace(
                request,
                getUserIdFromHeader(request),
                "query_ChildCategoryByParentId",
                "parentId:" + categoryFatherId, userTraceReqInfoService);
        userTraceService.recordTrace(trace);
        return Result.success(categoryList);
    }

    @GetMapping("/categoryByLevel/{categoryLevel}")
    private Result getCategoryByLevel(@PathVariable Long categoryLevel){
        List<Category> categoryList = categoryService.getCategoryByLevel(categoryLevel);
        log.info("根据等级{}查询分类:{}",categoryLevel,categoryList);
        // 跟踪
        UserTrace trace = UserTraceUtil.buildAndRecordUserTrace(
                request,
                getUserIdFromHeader(request),
                "query_CategoryByLevel",
                "level:" + categoryLevel, userTraceReqInfoService);
        userTraceService.recordTrace(trace);
        return Result.success(categoryList);
    }

}
