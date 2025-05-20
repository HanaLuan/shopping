package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.mapper.GoodsMapper;
import com.quanweng.shopping.pojo.Goods;
import com.quanweng.shopping.pojo.GoodsImg;
import com.quanweng.shopping.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

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


}
