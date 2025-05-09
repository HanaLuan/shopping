package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.GoodsImg;

import java.util.List;

public interface GoodsImgService {
    List<GoodsImg> getGoodsImgById(Long id);

    void createGoodsImg(GoodsImg goodsImg);

    void updateGoodsImg(GoodsImg goodsImg);

    void deleteGoodsImg(Long id);

    void createGoodsImgMore(List<GoodsImg> goodsImgList);
}
