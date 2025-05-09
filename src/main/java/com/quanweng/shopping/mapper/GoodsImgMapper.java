package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.GoodsImg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsImgMapper {
    @Select("select * from goods_img_table where goods_id = #{id}")
    List<GoodsImg> getGoodsImgById(Long id);

    void createGoodsImg(GoodsImg goodsImg);

    void updateGoodsImg(GoodsImg goodsImg);

    void deleteGoodsImg(Long id);
}
