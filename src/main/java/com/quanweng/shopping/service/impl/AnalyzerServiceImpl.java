package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.mapper.GoodsMapper;
import com.quanweng.shopping.mapper.GoodsTopMapper;
import com.quanweng.shopping.pojo.GoodsTop;
import com.quanweng.shopping.service.AnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyzerServiceImpl implements AnalyzerService {

    @Autowired
    private GoodsTopMapper goodsTopMapper;

    @Override
    public List<GoodsTop> getGoodsTop() {
        return goodsTopMapper.getGoodsTop();
    }
}
