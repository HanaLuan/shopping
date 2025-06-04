package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.CategoryImg;

import java.util.List;

public interface CategoryImgService {
    List<CategoryImg> getCategoryImgByCategoryId(Long categoryId);

    void createCategoryImg(List<CategoryImg> categoryImg);

    void updateCategoryImg(CategoryImg categoryImg);

    void deleteCategoryImg(Long id);
}
