package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.GoodsTop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsTopMapper {

    @Select("select * from goods_top_table where goods_id = #{id}")
    GoodsTop findTheGoodsTop(Long id);

    void createGoodsTop(GoodsTop createGoodsTop);

    void updateGoodsTop(GoodsTop goodsTop);

    @Select("select * from goods_top_table order by goods_count desc limit 1,100")
    List<GoodsTop> getGoodsTop();
}
