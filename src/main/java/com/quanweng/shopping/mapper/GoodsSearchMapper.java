package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.GoodsSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsSearchMapper {
    @Select("select * from goods_search_table where key_word = #{keyWord} and user_id = #{userId}")
    GoodsSearch findTheKeyWord(String keyWord, Long userId);

    void createTheSearch(GoodsSearch newGoodsSearch);

    void updateTheSearch(GoodsSearch goodsSearch);

    List<GoodsSearch> getGoodsKeyWordList(Long userId);
}
