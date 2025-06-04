package com.quanweng.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.quanweng.shopping.mapper.CategoryMapper;
import com.quanweng.shopping.pojo.Category;
import com.quanweng.shopping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategory(Integer pages,Integer size) {
        PageHelper.startPage(pages,size);
        List<Category> categoryList = categoryMapper.getAllCategory();
        return categoryList;
    }

    @Override
    public void createCategory(Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.createCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.updateCategory(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        List<Category> categoryChildList = categoryMapper.getCategoryByFatherId(id);
        if(!categoryChildList.isEmpty()){
            for (Category category:categoryChildList){
                categoryMapper.deleteCategoryById(category.getId());
            }
        }
        categoryMapper.deleteCategoryById(id);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryMapper.getCategoryById(id);
    }

    @Override
    public List<Category> getCategoryByFatherId(Long categoryFatherId) {
        return categoryMapper.getCategoryByFatherId(categoryFatherId);
    }

    @Override
    public List<Category> getCategoryByLevel(Long categoryLevel) {
        return categoryMapper.getCategoryByLevel(categoryLevel);
    }
}
