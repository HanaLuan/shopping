package com.quanweng.shopping.controller.user;

import com.quanweng.shopping.pojo.Category;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.CategoryService;
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

    @GetMapping("/category")
    private Result getAllCategory(){
        List<Category> categoryList = categoryService.getAllCategory();
        log.info("查询全部分类:{}",categoryList);
        return Result.success(categoryList);
    }

    @GetMapping("/categoryDetail/{id}")
    private Result getCategoryById(@PathVariable Long id){
        Category category = categoryService.getCategoryById(id);
        log.info("根据id查询单个分类:{}",category);
        return Result.success(category);
    }

    @GetMapping("/categoryChild/{categoryFatherId}")
    private Result getCategoryByFatherId(@PathVariable Long categoryFatherId){
        List<Category> categoryList = categoryService.getCategoryByFatherId(categoryFatherId);
        log.info("根据父id{}查询子分类:{}",categoryFatherId,categoryList);
        return Result.success(categoryList);
    }

    @GetMapping("/categoryByLevel/{categoryLevel}")
    private Result getCategoryByLevel(@PathVariable Long categoryLevel){
        List<Category> categoryList = categoryService.getCategoryByLevel(categoryLevel);
        log.info("根据等级{}查询分类:{}",categoryLevel,categoryList);
        return Result.success(categoryList);
    }

}
