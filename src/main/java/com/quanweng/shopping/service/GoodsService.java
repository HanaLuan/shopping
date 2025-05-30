package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.Goods;
import com.quanweng.shopping.pojo.GoodsImg;
import com.quanweng.shopping.pojo.GoodsSearch;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface GoodsService {
    List<Goods> getAllGoods();

    List<Goods> getGoodsByCategory(Long category);

    Goods createGoods(Goods goods);

    void updateGoods(Goods goods);

    void deleteGoodsById(Long id);

    Goods getGoodsById(Long id);

    List<Goods> getGoodsByKeyWord(String keyWord) throws IOException;

    void remarkTheKeyWord(String keyWord, Long userId);

    List<GoodsSearch> getGoodsKeyWordList(Long userId);

    void excelInput(String url) throws IOException;
}
