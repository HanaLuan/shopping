package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.mapper.GoodsImgMapper;
import com.quanweng.shopping.pojo.GoodsImg;
import com.quanweng.shopping.service.GoodsImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GoodsImgServiceImpl implements GoodsImgService {
    @Autowired
    private GoodsImgMapper goodsImgMapper;

    @Override
    public List<GoodsImg> getGoodsImgById(Long id) {
        return goodsImgMapper.getGoodsImgById(id);
    }

    @Override
    public void createGoodsImg(GoodsImg goodsImg) {
        goodsImg.setUpdateTime(LocalDateTime.now());
        goodsImg.setCreateTime(LocalDateTime.now());
        goodsImgMapper.createGoodsImg(goodsImg);
    }

    @Override
    public void updateGoodsImg(GoodsImg goodsImg) {
        goodsImg.setUpdateTime(LocalDateTime.now());
        goodsImgMapper.updateGoodsImg(goodsImg);
    }

    @Override
    public void deleteGoodsImg(Long id) {
        goodsImgMapper.deleteGoodsImg(id);
    }

    @Override
    public void createGoodsImgMore(List<GoodsImg> goodsImgList) {
        for(GoodsImg goodsImg:goodsImgList){
            goodsImg.setCreateTime(LocalDateTime.now());
            goodsImg.setUpdateTime(LocalDateTime.now());
            goodsImgMapper.createGoodsImg(goodsImg);
        }
    }
}
