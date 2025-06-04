package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.CategoryImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryImgMapper {
    @Select("select * from category_img_table where category_id = #{categoryId}")
    List<CategoryImg> getCategoryImgByCategoryId(Long categoryId);

    void createCategoryImg(CategoryImg categoryImg);

    void updateCategoryImg(CategoryImg categoryImg);

    void deleteCategoryImg(Long id);
}
