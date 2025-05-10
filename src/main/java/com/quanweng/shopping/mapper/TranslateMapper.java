package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.Translate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TranslateMapper {
    @Select("select * from translate_table where text = #{text}")
    List<Translate> getTranslation(String text);

    void createTranslation(Translate translate);

    void updateTranslation(Translate translate);

    void deleteTranslation(String text);
}
