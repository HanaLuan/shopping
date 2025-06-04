package com.quanweng.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.quanweng.shopping.mapper.GoodsMapper;
import com.quanweng.shopping.mapper.GoodsTopMapper;
import com.quanweng.shopping.mapper.KeyTopMapper;
import com.quanweng.shopping.mapper.NameTopMapper;
import com.quanweng.shopping.pojo.GoodsTop;
import com.quanweng.shopping.pojo.KeyTop;
import com.quanweng.shopping.pojo.NameTop;
import com.quanweng.shopping.service.AnalyzerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class AnalyzerServiceImpl implements AnalyzerService {

    @Autowired
    private GoodsTopMapper goodsTopMapper;
    @Autowired
    private NameTopMapper nameTopMapper;
    @Autowired
    private KeyTopMapper keyTopMapper;

    @Override
    public List<GoodsTop> getGoodsTop() {
        return goodsTopMapper.getGoodsTop();
    }

    @Override
    public List<NameTop> getNameTop() {
        return nameTopMapper.getNameTop();
    }

    @Override
    public List<KeyTop> getKeyTop() {
        return keyTopMapper.getKeyTop();
    }

    @Override
    public List<GoodsTop> getGoodsTopLimit(Integer pages, Integer size, LocalDate startTime, LocalDate endTime) {
        PageHelper.startPage(pages, size);
        List<GoodsTop> goodsTops = goodsTopMapper.getGoodsTopLimit(startTime,endTime);
        return goodsTops;
    }

    @Override
    public List<NameTop> getNameTopLimit(Integer pages, Integer size, LocalDate startTime, LocalDate endTime) {
        PageHelper.startPage(pages, size);
        List<NameTop> nameTops = nameTopMapper.getNameTopLimit(startTime,endTime);
        return nameTops;
    }

    @Override
    public List<KeyTop> getKeyTopLimit(Integer pages, Integer size, LocalDate startTime, LocalDate endTime) {
        PageHelper.startPage(pages, size);
        List<KeyTop> keyTops = keyTopMapper.getKeyTopLimit(startTime,endTime);
        return keyTops;
    }

    @Override
    public Integer getGoodsTopCount() {
        return goodsTopMapper.getGoodsTopCount();
    }

    @Override
    public Integer getNameTopCount() {
        return nameTopMapper.getNamesTopCount();
    }

    @Override
    public Integer getKeyTopCount() {
        return keyTopMapper.getKeyTopCount();
    }
}
