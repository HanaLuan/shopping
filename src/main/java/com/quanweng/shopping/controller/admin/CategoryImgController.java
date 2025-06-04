package com.quanweng.shopping.controller.admin;

import com.quanweng.shopping.pojo.CategoryImg;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.CategoryImgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class CategoryImgController {

    @Autowired
    private CategoryImgService categoryImgService;

    @GetMapping("/categoryImg/{categoryId}")
    private Result getCategoryImgByCategoryId(@PathVariable Long categoryId){
        List<CategoryImg> categoryImgList = categoryImgService.getCategoryImgByCategoryId(categoryId);
        return Result.success(categoryImgList);
    }

    @PostMapping("/categoryImg")
    private Result createCategoryImg(@RequestBody List<CategoryImg> categoryImg){
        categoryImgService.createCategoryImg(categoryImg);
        return Result.success();
    }

    @PutMapping("/categoryImg")
    private Result updateCategoryImg(@RequestBody CategoryImg categoryImg){
        categoryImgService.updateCategoryImg(categoryImg);
        return Result.success();
    }

    @DeleteMapping("/categoryImg/{id}")
    private Result deleteCategoryImg(@PathVariable Long id){
        categoryImgService.deleteCategoryImg(id);
        return Result.success();
    }

}
