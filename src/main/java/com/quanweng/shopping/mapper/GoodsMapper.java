package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.Goods;
import com.quanweng.shopping.pojo.GoodsImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsMapper {
    @Select("select * from goods_table order by goods_weight desc ")
    List<Goods> getAllGoods();

    @Select("select * from goods_table where goods_type = #{category} order by goods_weight desc ")
    List<Goods> getGoodsByCategory(Long category);

    Long createGoods(Goods goods);

    void updateGoods(Goods goods);

    void deleteGoodsById(Long id);


    Goods getGoodsByGoodsId(Long goodsId);

    List<Goods> getGoodsByKeyWord(String keyWord);
}
