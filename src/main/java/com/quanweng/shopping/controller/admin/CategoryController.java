package com.quanweng.shopping.controller.admin;

import com.quanweng.shopping.pojo.Category;
import com.quanweng.shopping.pojo.Result;
import com.quanweng.shopping.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CategoryController {

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

    @PostMapping("/category")
    private Result createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        log.info("创建分类:{}",category);
        return Result.success();
    }

    @PutMapping("/category")
    private Result updateCategory(@RequestBody Category category){
        categoryService.updateCategory(category);
        log.info("更新分类:{}",category);
        return Result.success();
    }

    @DeleteMapping("/category/{id}")
    private Result deleteCategoryById(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
        log.info("删除分类:{}",id);
        return Result.success();
    }

}
