package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory(Integer pages,Integer size);

    void createCategory(Category category);

    void updateCategory(Category category);

    void deleteCategoryById(Long id);

    Category getCategoryById(Long id);

    List<Category> getCategoryByFatherId(Long categoryFatherId);

    List<Category> getCategoryByLevel(Long categoryLevel);
}
