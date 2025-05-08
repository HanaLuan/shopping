package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("select * from category_table order by category_weight desc ")
    List<Category> getAllCategory();

    void createCategory(Category category);

    void updateCategory(Category category);

    @Select("select * from category_table where id = #{id}")
    Category getCategoryById(Long id);

    @Select("select * from category_table where category_father_id = #{categoryFatherId} order by category_weight desc")
    List<Category> getCategoryByFatherId(Long categoryFatherId);

    void deleteCategoryById(Long id);

    @Select("select * from category_table where category_level = #{categoryLevel}")
    List<Category> getCategoryByLevel(Long categoryLevel);
}
