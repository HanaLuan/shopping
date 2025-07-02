package com.quanweng.shopping.service;

import com.google.zxing.WriterException;
import com.qiniu.common.QiniuException;
import com.quanweng.shopping.pojo.Category;
import com.quanweng.shopping.pojo.Goods;
import com.quanweng.shopping.pojo.GoodsImg;
import com.quanweng.shopping.pojo.GoodsSearch;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface GoodsService {
    List<Goods> getAllGoods(Integer pages,Integer size);

    List<Goods> getGoodsByCategory(String category,Integer pages,Integer size);

    Goods createGoods(Goods goods) throws IOException, WriterException;

    void updateGoods(Goods goods) throws IOException, WriterException;

    void deleteGoodsById(Long id);

    Goods getGoodsById(Long id);

    List<Goods> getGoodsByKeyWord(String keyWord,Integer pages,Integer size) throws IOException;

    void remarkTheKeyWord(String keyWord, Long userId);

    List<GoodsSearch> getGoodsKeyWordList(Long userId);

    void excelInput(String url) throws IOException;

    List<Goods> getAllGoodsByNoTip(Integer pages,Integer size);

    Integer getAllGoodsCount();

    Integer getGoodsByCategoryCount(String category);

    Integer getAllGoodsByNoTipCount();

    Integer getGoodsByKeyWordCount(String keyWord);

    void uploadImg() throws IOException, WriterException;

    void goodsImgUpdate(String url);
}
