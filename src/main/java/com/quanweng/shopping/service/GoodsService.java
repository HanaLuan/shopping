package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.Goods;
import com.quanweng.shopping.pojo.GoodsImg;

import java.util.List;

public interface GoodsService {
    List<Goods> getAllGoods();

    List<Goods> getGoodsByCategory(Long category);

    Goods createGoods(Goods goods);

    void updateGoods(Goods goods);

    void deleteGoodsById(Long id);

    Goods getGoodsById(Long id);

}
