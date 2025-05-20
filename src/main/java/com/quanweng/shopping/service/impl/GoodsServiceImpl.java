package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.mapper.GoodsMapper;
import com.quanweng.shopping.mapper.GoodsSearchMapper;
import com.quanweng.shopping.pojo.Goods;
import com.quanweng.shopping.pojo.GoodsImg;
import com.quanweng.shopping.pojo.GoodsSearch;
import com.quanweng.shopping.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsSearchMapper goodsSearchMapper;

    @Override
    public List<Goods> getAllGoods() {
        return goodsMapper.getAllGoods();
    }

    @Override
    public List<Goods> getGoodsByCategory(Long category) {
        return goodsMapper.getGoodsByCategory(category);
    }

    @Override
    public Goods createGoods(Goods goods) {
        goods.setCreateTime(LocalDateTime.now());
        goods.setUpdateTime(LocalDateTime.now());
        goodsMapper.createGoods(goods);
        return goods;
    }

    @Override
    public void updateGoods(Goods goods) {
        goods.setUpdateTime(LocalDateTime.now());
        goodsMapper.updateGoods(goods);
    }

    @Override
    public void deleteGoodsById(Long id) {
        goodsMapper.deleteGoodsById(id);
    }

    @Override
    public Goods getGoodsById(Long id) {
        return goodsMapper.getGoodsByGoodsId(id);
    }

    @Override
    public List<Goods> getGoodsByKeyWord(String keyWord) {
        return goodsMapper.getGoodsByKeyWord(keyWord);
    }

    @Override
    public void remarkTheKeyWord(String keyWord, Long userId) {
        GoodsSearch goodsSearch = goodsSearchMapper.findTheKeyWord(keyWord,userId);
        if(goodsSearch == null){
            GoodsSearch newGoodsSearch = new GoodsSearch();
            newGoodsSearch.setKeyWord(keyWord);
            newGoodsSearch.setUserId(userId);
            newGoodsSearch.setUpdateTime(LocalDateTime.now());
            newGoodsSearch.setCreateTime(LocalDateTime.now());
            goodsSearchMapper.createTheSearch(newGoodsSearch);
        }else {
            goodsSearch.setUpdateTime(LocalDateTime.now());
            goodsSearchMapper.updateTheSearch(goodsSearch);
        }
    }

    @Override
    public List<GoodsSearch> getGoodsKeyWordList(Long userId) {
        return goodsSearchMapper.getGoodsKeyWordList(userId);
    }


}
