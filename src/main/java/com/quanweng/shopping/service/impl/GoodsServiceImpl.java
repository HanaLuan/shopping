package com.quanweng.shopping.service.impl;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageHelper;
import com.google.zxing.WriterException;
import com.quanweng.shopping.Listener.GoodsDataListener;
import com.quanweng.shopping.mapper.*;
import com.quanweng.shopping.pojo.*;
import com.quanweng.shopping.service.GoodsService;
import com.quanweng.shopping.utils.BarcodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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
    @Autowired
    private TranslateMapper translateMapper;
    @Autowired
    private NameTopMapper nameTopMapper;
    @Autowired
    private KeyTopMapper keyTopMapper;


    @Override
    public List<Goods> getAllGoods(Integer pages,Integer size) {
        if (pages != null && size != null) {
            PageHelper.startPage(pages, size);
        }
        List<Goods> goodsList = goodsMapper.getAllGoods();
        return goodsList;
    }

    @Override
    public List<Goods> getGoodsByCategory(String category,Integer pages,Integer size) {
        if (pages != null && size != null) {
            PageHelper.startPage(pages, size);
        }
        List<Goods> goodsList = goodsMapper.getGoodsByCategory(category);
        return goodsList;
    }

    @Override
    public Goods createGoods(Goods goods) throws IOException, WriterException {
        goods.setCreateTime(LocalDateTime.now());
        goods.setUpdateTime(LocalDateTime.now());
        goodsMapper.createGoods(goods);

        goods.setGoodsBarCode(BarcodeUtils.generateCode128Barcode(goods.getId().toString()));
        goodsMapper.addGoodsBarCode(goods);
        return goods;
    }

    @Override
    public void updateGoods(Goods goods) throws IOException, WriterException {
        goods.setUpdateTime(LocalDateTime.now());
        if(goods.getGoodsBarCode() == null || goods.getGoodsBarCode().isEmpty()){
            goods.setGoodsBarCode(BarcodeUtils.generateCode128Barcode(goods.getId().toString()));
            goodsMapper.addGoodsBarCode(goods);
        }
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
    public List<Goods> getGoodsByKeyWord(String keyWord) throws IOException {
        List<Translate> translateList = translateMapper.getOriginalTranslate(keyWord);
        List<String> keyWordList = new ArrayList<>();
        keyWordList.add(keyWord);
        if(translateList != null && !translateList.isEmpty()) {
            for (Translate translate : translateList) {
                keyWordList.add(translate.getText());
            }
        }
        List<Goods> goodsList = new ArrayList<>();
        for (String key:keyWordList){
            List<Goods> goods = goodsMapper.getGoodsByKeyWord(key);
            if (!goods.isEmpty()) {
                goodsList.addAll(goods);
            }
        }

        NameTop nameTop = nameTopMapper.findTheNameTop(keyWord);
        if(nameTop == null){
            NameTop createNameTop = new NameTop();
            createNameTop.setName(keyWord);
            createNameTop.setNameCount(1L);
            createNameTop.setCreateTime(LocalDateTime.now());
            createNameTop.setUpdateTime(LocalDateTime.now());
            nameTopMapper.createNameTop(createNameTop);
        }else {
            nameTop.setNameCount(nameTop.getNameCount() + 1);
            nameTop.setUpdateTime(LocalDateTime.now());
            nameTopMapper.updateNameTop(nameTop);
        }

        IKSegmenter segmenter = new IKSegmenter(new StringReader(keyWord),true);
        Lexeme next;
        log.info("分词器:{}",keyWord);
        while ((next = segmenter.next()) != null){
            KeyTop keyTop = keyTopMapper.findTheKeyTop(next.getLexemeText());
            if (keyTop == null){
                KeyTop createKeyTop = new KeyTop();
                createKeyTop.setKeyWord(next.getLexemeText());
                createKeyTop.setKeyCount(1L);
                createKeyTop.setCreateTime(LocalDateTime.now());
                createKeyTop.setUpdateTime(LocalDateTime.now());
                keyTopMapper.createKeyTop(createKeyTop);
            }else {
                keyTop.setKeyCount(keyTop.getKeyCount() + 1);
                keyTop.setUpdateTime(LocalDateTime.now());
                keyTopMapper.updateKeyTop(keyTop);
            }
        }


        return goodsList;
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

    @Override
    public List<Goods> getAllGoodsByNoTip(Integer pages,Integer size) {
        if (pages != null && size != null) {
            PageHelper.startPage(pages, size);
        }
        List<Goods> goodsList = goodsMapper.getAllGoodsByNoTip();
        return goodsList;
    }

    @Override
    public Integer getAllGoodsCount() {
        return goodsMapper.getAllGoodsCount();
    }

    @Override
    public Integer getGoodsByCategoryCount(String category) {
        return goodsMapper.getGoodsByCategoryCount(category);
    }

    @Override
    public Integer getAllGoodsByNoTipCount() {
        return goodsMapper.getAllGoodsByNoTipCount();
    }


}
