package com.quanweng.shopping.Listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.quanweng.shopping.mapper.GoodsImgMapper;
import com.quanweng.shopping.mapper.GoodsMapper;
import com.quanweng.shopping.pojo.Goods;
import com.quanweng.shopping.pojo.GoodsImg;
import com.quanweng.shopping.pojo.GoodsImgExcel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class GoodsImgDataListener extends AnalysisEventListener<GoodsImgExcel> {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsImgMapper goodsImgMapper;

    @Override
    public void invoke(GoodsImgExcel goodsImgExcel, AnalysisContext analysisContext) {
        Goods newGoods = goodsMapper.getOneGoodsByKeyWord(goodsImgExcel.getNewCode());
        Goods oldGoods = goodsMapper.getOneGoodsByKeyWord(goodsImgExcel.getOldCode());
        log.info("new{}",newGoods.getGoodsShowImg());
        log.info("old{}",oldGoods.getGoodsShowImg());
        newGoods.setGoodsShowImg(oldGoods.getGoodsShowImg());
        GoodsImg goodsImg = new GoodsImg();
        goodsImg.setGoodsImg(oldGoods.getGoodsShowImg());
        goodsImg.setGoodsId(newGoods.getId());
        goodsImg.setCreateTime(newGoods.getCreateTime());
        goodsImg.setUpdateTime(newGoods.getUpdateTime());
        goodsMapper.updateGoods(newGoods);
        goodsImgMapper.deleteGoodsImgByGoodsId(newGoods.getId());
        goodsImgMapper.createGoodsImg(goodsImg);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("全部完成");
    }
}
