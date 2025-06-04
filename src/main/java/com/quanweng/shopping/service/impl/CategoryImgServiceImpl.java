package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.mapper.CategoryImgMapper;
import com.quanweng.shopping.pojo.CategoryImg;
import com.quanweng.shopping.service.CategoryImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryImgServiceImpl implements CategoryImgService {
    @Autowired
    private CategoryImgMapper categoryImgMapper;


    @Override
    public List<CategoryImg> getCategoryImgByCategoryId(Long categoryId) {
        return categoryImgMapper.getCategoryImgByCategoryId(categoryId);
    }

    @Override
    public void createCategoryImg(List<CategoryImg> categoryImgList) {
        for(CategoryImg categoryImg:categoryImgList) {
            categoryImg.setCreateTime(LocalDateTime.now());
            categoryImg.setUpdateTime(LocalDateTime.now());
            categoryImgMapper.createCategoryImg(categoryImg);
        }
    }

    @Override
    public void updateCategoryImg(CategoryImg categoryImg) {
        categoryImg.setUpdateTime(LocalDateTime.now());
        categoryImgMapper.updateCategoryImg(categoryImg);
    }

    @Override
    public void deleteCategoryImg(Long id) {
        categoryImgMapper.deleteCategoryImg(id);
    }
}
