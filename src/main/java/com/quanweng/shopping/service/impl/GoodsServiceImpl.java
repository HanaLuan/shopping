package com.quanweng.shopping.service.impl;

import com.alibaba.excel.EasyExcel;
import com.quanweng.shopping.Listener.GoodsDataListener;
import com.quanweng.shopping.mapper.GoodsMapper;
import com.quanweng.shopping.mapper.GoodsSearchMapper;
import com.quanweng.shopping.mapper.GoodsTopMapper;
import com.quanweng.shopping.pojo.*;
import com.quanweng.shopping.service.GoodsService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsSearchMapper goodsSearchMapper;
    @Autowired
    private GoodsDataListener goodsDataListener;
    @Autowired
    private GoodsTopMapper goodsTopMapper;

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
        GoodsTop goodsTop = goodsTopMapper.findTheGoodsTop(id);
        if (goodsTop == null){
            GoodsTop createGoodsTop = new GoodsTop();
            createGoodsTop.setGoodsId(id);
            createGoodsTop.setGoodsCount(1L);
            createGoodsTop.setCreateTime(LocalDateTime.now());
            createGoodsTop.setUpdateTime(LocalDateTime.now());
            goodsTopMapper.createGoodsTop(createGoodsTop);
        }else {
            goodsTop.setGoodsCount(goodsTop.getGoodsCount() + 1);
            goodsTop.setUpdateTime(LocalDateTime.now());
            goodsTopMapper.updateGoodsTop(goodsTop);
        }
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

    @Override
    public void excelInput(String url) throws IOException {
        EasyExcel.read(url, GoodsExcel.class,goodsDataListener)
                .sheet()
                .doRead();



    }


}
